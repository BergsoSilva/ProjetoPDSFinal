/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.multa;

import aplication.Exceptions.BDException;
import aplication.dao.AluguelDAO;
import aplication.dao.MultaDAO;
import aplication.modelo.Aluguel;
import aplication.modelo.Multa;
import aplication.modelo.Status;
import aplication.modelo.StatusMulta;
import aplication.regraDeNegocio.DevolucaoControl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author flavio
 */
public class TelaPesquisarMulta extends javax.swing.JFrame {
    private Aluguel aluguel;
    private List<Multa> multas = new ArrayList();
    private Double valorTotal = 0.0;
    private Double valorDivida = 0.0;
    private Double troco = 0.0;
    private static final int ACAO_PESQUISAR = 1;
    private static final int ACAO_MANTER = 2;
    private Integer acao;

    //Construtor para geração e consulta de multas relacionadas a um único aluguel
    public TelaPesquisarMulta( Aluguel aluguel) throws BDException {
        initComponents();
     
        this.aluguel = aluguel;
        this.acao = ACAO_MANTER;
        
        setaCampos();
        setaBotoes();
        carregarTabela();
    }

    //Construtor para consulta de multas somente
    public TelaPesquisarMulta() throws BDException {
        initComponents();
     
        this.acao = ACAO_PESQUISAR;
        
        //cria um objeto aluguel para ser passado para o método setaBotoes
        this.aluguel = new Aluguel();
        Status status = new Status();
        status.setId(Aluguel.FINALIZADO);
        aluguel.setStatus(status);
        
        setaCampos();
        setaBotoes();
        carregarTabela();
    }
    
    private void setaCampos(){
        campoTroco.setVisible(false);
        labelTroco.setVisible(false);
        campoDivida.setVisible(acao.equals(ACAO_MANTER));
        labelDivida.setVisible(acao.equals(ACAO_MANTER));
        campoValorTotal.setVisible(acao.equals(ACAO_MANTER));
        labelValorTotal.setVisible(acao.equals(ACAO_MANTER));
    }
        
    //Antigo verificaStatusAluguel
    private void setaBotoes(){
        Long status = aluguel.getStatus().getId();
        
        if (status.equals(Aluguel.FINALIZADO) || status.equals(Aluguel.FINALIZADO_COM_PENDENCIA)){
            botaoAdicionar.setVisible(false);
            botaoAlterar.setVisible(false);
            botaoRemover.setVisible(false);
            botaoFinalizar.setText("Voltar");
            
            if (status.equals(Aluguel.FINALIZADO)){
                botaoEfetuarPagamento.setVisible(false);
            }
        }
    }
    
    private void calcularTotal(){
        valorTotal = 0.0;
        
        for(Multa multa: multas){
            valorTotal += multa.getValor();
        }
        
        campoValorTotal.setText(valorTotal.toString());
    }
    
    private void calculaDivida(){
        valorDivida = 0.0;
        
        for(Multa multa: multas){
            Long statusMultaId = multa.getStatusMulta().getId();
            
            if (statusMultaId.equals(StatusMulta.PAGO)){
                continue;
            }else if(statusMultaId.equals(StatusMulta.NAO_PAGO)){
                this.valorDivida += multa.getValor();
            }else{
                this.valorDivida += multa.getValor() - multa.getValorPago();
            }
        }
        
        campoDivida.setText(valorDivida.toString());
    }
    
    protected void carregarTabela() throws BDException{
        pesquisar();
        tabelaPesquisarMulta.setModel( new TabelaModeloMulta(multas));
        
        if (this.acao == ACAO_MANTER){
            calcularTotal();
            calculaDivida();
        }
    }
    
    private void pesquisar() throws BDException{
        MultaDAO multaDAO = new MultaDAO();
        
        multas = multaDAO.pesquisar(aluguel);
    }
    
    private void adicionarMulta(){
        String acao = "adicionar";
        new TelaFormularioMulta(aluguel, this).setVisible(true);
    }
    
    private void alterarMulta(){
        String acao = "alterar";
        int linhaSelecionada = tabelaPesquisarMulta.getSelectedRow();
        Multa multaSelecionada = multas.get(linhaSelecionada);
        
        if (linhaSelecionada >= 0){
            new TelaFormularioMulta(aluguel, multaSelecionada, this).setVisible(true);
        }
    }
    
    private void removerMulta() throws BDException{
        int linhaSelecionada = tabelaPesquisarMulta.getSelectedRow();
        
        if (linhaSelecionada >= 0){
            Multa multaSelecionada = multas.get(linhaSelecionada);
            
            int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir multa?", "Confimação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
            if (opcao == JOptionPane.YES_NO_OPTION){
                MultaDAO multaDAO = new MultaDAO();
                multaDAO.remover(multaSelecionada);

                carregarTabela();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma Multa!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void atualizarAluguel(){
        AluguelDAO aluguelDAO = new AluguelDAO();
        
        aluguelDAO.alterar(aluguel);
    }
    
    private void  atualizarMulta(Multa multa) throws BDException{
        MultaDAO multaDAO = new MultaDAO();
        
        multaDAO.alterar(multa);
    }
    
    private void efetuarPagamento() throws BDException, JRException, SQLException{
        
        Double valorEntregue;
        String valorEntregueString;
        
        if (valorTotal > 0.0){
            valorEntregueString = JOptionPane.showInputDialog("Insira a quantia entregue pelo cliente");
            valorEntregue = valorInseridoEValido(valorEntregueString);
            
            if( valorEntregue > 0.0 ){
                distribuirValorEntreMultas(valorEntregue);
                campoTroco.setText( troco.toString() );
                campoTroco.setVisible(true);
                labelTroco.setVisible(true);
                campoDivida.setVisible(true);
                labelDivida.setVisible(true);
                
                DevolucaoControl devolucaoControl = new DevolucaoControl(aluguel);
                devolucaoControl.updateAluguel();
                carregarTabela();
                setaBotoes();
                devolucaoControl.gerarComprovanteDevolucao();
            } else{
                JOptionPane.showMessageDialog(null, "Valor inserido é inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Adicione Multas!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void distribuirValorEntreMultas(Double valorEntregue) throws BDException, JRException, SQLException{
        Double troco = valorEntregue;
        Double saldoDevedor = valorTotal;
        
        for (Multa multa: multas){
            Double valorMulta = multa.getValor();
            Long statusMulta = multa.getStatusMulta().getId();
            
            if ( statusMulta.equals(StatusMulta.PAGO) ){
                saldoDevedor = saldoDevedor - valorMulta;
                continue;
            }else if (statusMulta.equals(StatusMulta.PAGO_PARCIALMENTE)){
                Double valorJaPago = multa.getValorPago();
                if (troco >= (valorMulta - valorJaPago)){
                    multa.getStatusMulta().setId(StatusMulta.PAGO);
                    multa.setValorPago(valorMulta);
                    troco = troco - (valorMulta - valorJaPago);
                    atualizarMulta(multa);
                    saldoDevedor = saldoDevedor - valorMulta;
                } else if (troco > 0 && troco < (valorMulta - valorJaPago)){
                        multa.getStatusMulta().setId(StatusMulta.PAGO_PARCIALMENTE);
                        multa.setValorPago(troco + valorJaPago);
                        troco = 0.0;
                        atualizarMulta(multa);
                        saldoDevedor = saldoDevedor - multa.getValorPago();
                } else {
                    multa.getStatusMulta().setId(StatusMulta.NAO_PAGO);
                    multa.setValorPago(0.0);
                }
            } else {
                if (troco >= valorMulta){
                    multa.getStatusMulta().setId(StatusMulta.PAGO);
                    multa.setValorPago(valorMulta);
                    troco = troco - valorMulta;
                    atualizarMulta(multa);
                    saldoDevedor = saldoDevedor - valorMulta;
                } else if (troco > 0 && troco < valorMulta){
                        multa.getStatusMulta().setId(StatusMulta.PAGO_PARCIALMENTE);
                        multa.setValorPago(troco);
                        troco = 0.0;
                        atualizarMulta(multa);
                        saldoDevedor = saldoDevedor - multa.getValorPago();
                } else {
                    multa.getStatusMulta().setId(StatusMulta.NAO_PAGO);
                    multa.setValorPago(0.0);
                }
            }
            
            this.troco = troco;
        }
        
        if(!aluguel.getStatus().getId().equals(Aluguel.FINALIZADO_COM_PENDENCIA)){
            DevolucaoControl devolucaoControl = new DevolucaoControl(aluguel);
            devolucaoControl.updateSaldoProduto();
        }
        
        if ( saldoDevedor > 0 ){
            campoDivida.setText(saldoDevedor.toString());
            aluguel.getStatus().setId(Aluguel.FINALIZADO_COM_PENDENCIA);
        } else{
            campoDivida.setText("0,00");
            aluguel.getStatus().setId(Aluguel.FINALIZADO);
        }
    }
    
    private Double valorInseridoEValido(String valorEntregueString){
        Double valorEntregue;
        
        //Valor padrão de retorno, caso seja inválido
        final Double invalido = 0.0;
        
        try{
            valorEntregue = Double.parseDouble(valorEntregueString);
        } catch(NumberFormatException nfe){
            return invalido;
        }
        
        if (valorEntregue <= 0.0){
            return invalido;
        }
        
        return valorEntregue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPesquisarMulta = new javax.swing.JTable();
        labelValorTotal = new javax.swing.JLabel();
        botaoAdicionar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoFinalizar = new javax.swing.JButton();
        campoValorTotal = new javax.swing.JFormattedTextField();
        botaoEfetuarPagamento = new javax.swing.JButton();
        labelTroco = new javax.swing.JLabel();
        campoTroco = new javax.swing.JFormattedTextField();
        labelDivida = new javax.swing.JLabel();
        campoDivida = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Multas");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        tabelaPesquisarMulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaPesquisarMulta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelaPesquisarMulta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tabelaPesquisarMulta);

        labelValorTotal.setText("Total");

        botaoAdicionar.setText("Adicionar");
        botaoAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarActionPerformed(evt);
            }
        });

        botaoRemover.setText("Remover");
        botaoRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverActionPerformed(evt);
            }
        });

        botaoAlterar.setText("Alterar");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        botaoFinalizar.setText("Finalizar");
        botaoFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizarActionPerformed(evt);
            }
        });

        campoValorTotal.setEditable(false);
        campoValorTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        botaoEfetuarPagamento.setText("Efetuar Pagamento");
        botaoEfetuarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEfetuarPagamentoActionPerformed(evt);
            }
        });

        labelTroco.setText("Troco");

        campoTroco.setEditable(false);
        campoTroco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        labelDivida.setText("Dívida");

        campoDivida.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelValorTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelTroco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(labelDivida)
                        .addGap(18, 18, 18)
                        .addComponent(campoDivida, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(botaoEfetuarPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botaoAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(botaoFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(botaoAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelDivida)
                        .addComponent(campoDivida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoValorTotal)
                    .addComponent(botaoFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoEfetuarPagamento)
                        .addComponent(labelValorTotal)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTroco))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>                        

    private void botaoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {                                               
        adicionarMulta();
    }                                              

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        alterarMulta();
    }                                            

    private void botaoRemoverActionPerformed(java.awt.event.ActionEvent evt) {                                             
        try {
            removerMulta();
        } catch (BDException ex) {}
    }                                            

    private void botaoFinalizarActionPerformed(java.awt.event.ActionEvent evt) {                                               
        dispose();
    }                                              

    private void botaoEfetuarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        try {
            efetuarPagamento();
        } catch (BDException | JRException | SQLException ex) {}
    }                                                     

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {                                       
        try {
            carregarTabela();
        } catch (BDException ex) {}
    }                                      

    // Variables declaration - do not modify                     
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoEfetuarPagamento;
    private javax.swing.JButton botaoFinalizar;
    private javax.swing.JButton botaoRemover;
    private javax.swing.JFormattedTextField campoDivida;
    private javax.swing.JFormattedTextField campoTroco;
    private javax.swing.JFormattedTextField campoValorTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDivida;
    private javax.swing.JLabel labelTroco;
    private javax.swing.JLabel labelValorTotal;
    private javax.swing.JTable tabelaPesquisarMulta;
    // End of variables declaration                   
}
