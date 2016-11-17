package aplication.view.multa;

import aplication.Exceptions.BDException;
import aplication.dao.MultaDAO;
import aplication.modelo.Aluguel;
import aplication.modelo.Multa;
import aplication.modelo.StatusMulta;
import aplication.view.multa.TelaPesquisarMulta;
import java.awt.HeadlessException;

import javax.swing.JOptionPane;

/**
 *
 * @author flavio
 */
public class TelaFormularioMulta extends javax.swing.JFrame {
    
    private Aluguel aluguel;
    private Multa multa;
    private TelaPesquisarMulta telaPesquisarMulta;
    private String acao;

    //Construtor para ação adicionar
    public TelaFormularioMulta(Aluguel aluguel, TelaPesquisarMulta telaPesquisarMulta) {
        this.aluguel = aluguel;
        this.telaPesquisarMulta = telaPesquisarMulta;
        this.acao = "adicionar";
        
        initComponents();
    }
    
    public TelaFormularioMulta(Aluguel aluguel, TelaPesquisarMulta telaPesquisarMulta, int tempoDeAtraso) throws BDException {
        initComponents();
        
        this.aluguel = aluguel;
        this.telaPesquisarMulta = telaPesquisarMulta;
        this.acao = "adicionar";
        
        geraMultaAtraso(tempoDeAtraso);
    }
    
    //Construtor para ação alterar
    public TelaFormularioMulta(Aluguel alguel, Multa multa, TelaPesquisarMulta telaPesquisarMulta){
        initComponents();
        
        campoDescricao.setText(multa.getDescricao());
        campoValor.setText(multa.getValor().toString());
        this.multa = multa;
        this.acao = "alterar";
        this.aluguel = alguel;
        this.telaPesquisarMulta = telaPesquisarMulta;
    }
    
    private void geraMultaAtraso(int tempoDeAtraso) throws BDException{
        campoDescricao.setText("Multa por Atraso");
        Double valor;
        Double precoProduto = aluguel.getProduto().getPrecoAluguel();
        Multa multa = new Multa();
        
        valor = precoProduto * tempoDeAtraso;
        valor = valor +  valor * 0.1 ;
        campoValor.setText(valor.toString());
        
        if ( preencheMulta() ){
            salvarMulta();
        }
    }
    
    private void salvarMulta() throws BDException{
        MultaDAO multaDAO = new MultaDAO();
        if( acao.equals("adicionar")){
            multaDAO.inserir(multa);
        }else{
            System.out.println("id tpm: " + multa.getId());
            multaDAO.alterar(multa);
        }
        telaPesquisarMulta.carregarTabela();
        
        dispose();
    }
    
    //Preenche um objeto Multa, se valores forem válidos
    private boolean preencheMulta(){
        Double valorMulta;
        String descricao;
        
        if( campoValorEValido() > 0.0 ){
            valorMulta = campoValorEValido();
        } else{
            JOptionPane.showMessageDialog(null, "Valor inserido é inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if( !campoDescricaoEValido().isEmpty() ){
                descricao = campoDescricaoEValido();
        } else{
            JOptionPane.showMessageDialog(null, "Descrição inserida é inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (acao.equals("adicionar")){
            System.out.println("add");
            multa = new Multa();
            multa.setAluguel(aluguel);
            StatusMulta statusMulta = new StatusMulta();
            statusMulta.setId(StatusMulta.NAO_PAGO);
            multa.setStatusMulta(statusMulta);
        }
        
        multa.setValor(valorMulta);
        multa.setDescricao(descricao);

        return true;
    }
    
    //Valida o campo de descrição
    private String campoDescricaoEValido(){
        String descricao = campoDescricao.getText();
        
        //Valor padrão de retorno, caso seja inválido
        String invalido = "";
        
        //Tamanhos mínimo e máximo da descrição
        //máx = 203, permite 3 quebras de linha
        int tamanhoMinimo = 15;
        int tamanhoMaximo = 203;
        
        if (descricao.length() < tamanhoMinimo || descricao.length() > tamanhoMaximo){
            return invalido;
        }
        
        return descricao;
    }
    
    //Valida o valor da multa inserido
    private Double campoValorEValido(){
        Double valorMulta;
        
        //Valor padrão de retorno, caso seja inválido
        final Double invalido = 0.0;
        
        try{
            valorMulta = Double.parseDouble(campoValor.getText());
        } catch(NumberFormatException nfe){
            return invalido;
        }
        
        if (valorMulta <= 0.0){
            return invalido;
        }
        
        return valorMulta;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoDescricao = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        botaoSalvar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        campoValor = new javax.swing.JFormattedTextField();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Multa");

        jLabel1.setText("Descrição");

        campoDescricao.setColumns(20);
        campoDescricao.setRows(5);
        jScrollPane1.setViewportView(campoDescricao);

        jLabel2.setText("Valor");

        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("(Mínimo 15 e máximo 200 caracteres)");

        campoValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addComponent(campoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar)
                    .addComponent(botaoCancelar))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        if ( preencheMulta() ){
            try{ 
                salvarMulta(); 
            }catch(BDException bde){}
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JTextArea campoDescricao;
    private javax.swing.JFormattedTextField campoValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
