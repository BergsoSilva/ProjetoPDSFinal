
package aplication.view.pedido;

import aplication.dao.AluguelDAO;
import aplication.dao.PedidoDAO;
import aplication.modelo.Aluguel;
import aplication.modelo.Cliente;
import aplication.modelo.Status;
import aplication.regraDeNegocio.ThretdTempoPedido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class TelaPesquisaPedido extends javax.swing.JFrame {

   private List<Aluguel> aluugueis = new ArrayList<>();
   private Aluguel aluguel = new Aluguel();
   
   private ThretdTempoPedido thered;
   
   
    public TelaPesquisaPedido(){
        initComponents();      
        
        Status status = new Status();
        status.setId(Aluguel.PEDIDO);
        this.aluguel.setStatus(status);
        
        this.thered=thered;
        carregarTabela();
        carregarMenuFlutuante();     
        preecherComboMarcacao();
    }
    
    private void preecherComboMarcacao(){
        comboMarcacao.removeAllItems();
        comboMarcacao.addItem("");
        comboMarcacao.addItem("Marca Todos");
        comboMarcacao.addItem("Marca Nenhum");
    }
    
    private void pesquisar(){        
        preencherCliente();
        try {            
            PedidoDAO pedidoDAO = new PedidoDAO();
            this.aluugueis = pedidoDAO.pesquisar(aluguel);
        }catch(Exception e){
            e.printStackTrace();
        }                   
    }
    
    @SuppressWarnings("unchecked")
    
    private void carregarTabela(){
        pesquisar();
        tabelaPedido.setModel(new TabelaModeloPedido(this.aluugueis));
        
        //tabelaPedido.getColumnModel().getColumn(4).setCellRenderer(checkBoxRederer);
    }
    
    private void carregarMenuFlutuante(){
        JMenuItem menuItem[] = {new JMenuItem("Ver detalhes"), new JMenuItem("Devolucao")};
        
        menuItem[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               verDetalhes();
            }
        });
        
        menuItem[1].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    alterar();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        for (JMenuItem menuItem1 : menuItem) {
            jPopupMenu1.add(menuItem1);
        }
    }
    
    private void verDetalhes(){
        TelaVerDetalhesPedido telaVerDetalhesPedido = new TelaVerDetalhesPedido(aluguel,thered);
        telaVerDetalhesPedido.setVisible(true);
    }
    
    private void alterar(){
        if (radioPedido.isSelected()){
            JOptionPane.showMessageDialog(this, "Selecione a lista De 'Alugados'");
        }else{
            TelaDevolucao telaDevolucao = new TelaDevolucao(aluguel);
            telaDevolucao.setVisible(true);
        }        
    }
    
    private void preencherCliente(){
        String cpf = campoCpf.getText();
        
        if (cpf.equals("   .   .   -  ")){
            cpf = "";
        }
        
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        this.aluguel.setCliente(cliente);
    }
    
    private void selecionarCliente(MouseEvent evt){
        int linha = tabelaPedido.rowAtPoint(evt.getPoint());
        
        if(linha >= 0){
            tabelaPedido.setRowSelectionInterval(linha, linha);
            linha = tabelaPedido.getSelectedRow();
            
            this.aluguel = aluugueis.get(linha);            
        }
    }
    
    private void realizarAcao(MouseEvent evt){
        if (evt.getButton() == evt.BUTTON1){
            
            if(evt.getClickCount() > 1){
                verDetalhes();
            }
        }else{
            if(evt.getButton() == evt.BUTTON3){
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }
   
    private void aplicaRadioStatus(){
        if (radioPedido.isSelected()){
            this.aluguel.getStatus().setId((Aluguel.PEDIDO));
        }else if (radioAlugado.isSelected()){
            this.aluguel.getStatus().setId((Aluguel.ALUGADO));
        }else if (radioFinalizado.isSelected()){
            this.aluguel.getStatus().setId((Aluguel.FINALIZADO));
        } else if (radioFinalComPendencia.isSelected()){
            this.aluguel.getStatus().setId((Aluguel.FINALIZADO_COM_PENDENCIA));
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        grupoRadio = new javax.swing.ButtonGroup();
        botaoPesquisar = new javax.swing.JButton();
        botaoAlugarMarcados = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPedido = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        comboMarcacao = new javax.swing.JComboBox<>();
        radioPedido = new javax.swing.JRadioButton();
        radioAlugado = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        campoCpf = new javax.swing.JFormattedTextField();
        radioFinalizado = new javax.swing.JRadioButton();
        radioFinalComPendencia = new javax.swing.JRadioButton();
        campoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Pedido");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        botaoPesquisar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        botaoAlugarMarcados.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoAlugarMarcados.setText("Alugar Marcados");
        botaoAlugarMarcados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlugarMarcadosActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados Encontrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, null, new java.awt.Color(51, 51, 51)));
        jPanel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        tabelaPedido.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabelaPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelaPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaPedidoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaPedidoMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaPedido);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Selecionar:");

        comboMarcacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboMarcacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboMarcacaoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboMarcacaoMouseReleased(evt);
            }
        });
        comboMarcacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMarcacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboMarcacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboMarcacao, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        grupoRadio.add(radioPedido);
        radioPedido.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        radioPedido.setSelected(true);
        radioPedido.setText("Pedido(s)");
        radioPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPedidoActionPerformed(evt);
            }
        });

        grupoRadio.add(radioAlugado);
        radioAlugado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        radioAlugado.setText("Alugado(s)");
        radioAlugado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioAlugadoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("CPF:");

        try {
            campoCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCpfActionPerformed(evt);
            }
        });

        grupoRadio.add(radioFinalizado);
        radioFinalizado.setText("Finalizado");
        radioFinalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFinalizadoActionPerformed(evt);
            }
        });

        grupoRadio.add(radioFinalComPendencia);
        radioFinalComPendencia.setText("Finalizado com Pendencia");
        radioFinalComPendencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFinalComPendenciaActionPerformed(evt);
            }
        });

        campoCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campoCancelar.setText("Cancelar");
        campoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campoCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(302, 302, 302)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(radioPedido)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radioAlugado)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioFinalizado)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioFinalComPendencia)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoAlugarMarcados)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(campoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(382, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioFinalizado)
                        .addComponent(radioFinalComPendencia))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioPedido)
                        .addComponent(radioAlugado)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoAlugarMarcados, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(407, Short.MAX_VALUE)
                    .addComponent(campoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
       aplicaRadioStatus();
       carregarTabela(); 
        switchMarcacaoDesmacacaoCheck();
    }//GEN-LAST:event_formWindowGainedFocus

    private void switchMarcacaoDesmacacaoCheck(){
        switch(comboMarcacao.getSelectedIndex()){
            case 1:
                marcaDesmarcarChecks(true);
                break;
            case 2:
                marcaDesmarcarChecks(false);
                break;
       }
    }
    
    private void botaoAlugarMarcadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlugarMarcadosActionPerformed
                       
        List<Aluguel> alugueisMarcados = alugueisMarcados();
        if (!alugueisMarcados.isEmpty()){
            TelaFormPagmento telaFormPagmento = new TelaFormPagmento(alugueisMarcados);
            telaFormPagmento.setVisible(true);
        }            
           
    }//GEN-LAST:event_botaoAlugarMarcadosActionPerformed

    private List<Aluguel> alugueisMarcados(){         
        List<Aluguel> alugueisMarcados = new ArrayList<Aluguel>();
        AluguelDAO aluguelDAO = new AluguelDAO();
        int verifaItens = 0;
        
        for (int i = 0; i < tabelaPedido.getRowCount(); i++) {
            Boolean marcado = (Boolean) tabelaPedido.getModel().getValueAt(i, 0);
            if (marcado != null){
                Long id = (Long) tabelaPedido.getModel().getValueAt(i, 1);
                
                Aluguel aluguel = aluguelDAO.consultarPorId(id);
                alugueisMarcados.add(aluguel);   
                verifaItens = 1;
            }
        } 
        
        if (verifaItens == 0){
            JOptionPane.showMessageDialog(this, "Nenhum produto foi selecionado!");
        }
        
        return alugueisMarcados;
    }
    
    private void tabelaPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaPedidoMouseClicked
        
    }//GEN-LAST:event_tabelaPedidoMouseClicked

    private void tabelaPedidoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaPedidoMouseReleased
        selecionarCliente(evt);
        realizarAcao(evt);
    }//GEN-LAST:event_tabelaPedidoMouseReleased

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        aplicaRadioStatus();
        carregarTabela();
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void radioPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPedidoActionPerformed
        this.aluguel.getStatus().setId(Aluguel.PEDIDO);
        carregarTabela();
    }//GEN-LAST:event_radioPedidoActionPerformed

    private void radioAlugadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioAlugadoActionPerformed
        this.aluguel.getStatus().setId(Aluguel.ALUGADO);
        carregarTabela();
    }//GEN-LAST:event_radioAlugadoActionPerformed

    private void campoCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCpfActionPerformed

    private void campoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_campoCancelarActionPerformed

    private void comboMarcacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboMarcacaoMouseClicked
        
    }//GEN-LAST:event_comboMarcacaoMouseClicked

    private void comboMarcacaoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboMarcacaoMouseReleased

    }//GEN-LAST:event_comboMarcacaoMouseReleased

    private void comboMarcacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMarcacaoActionPerformed
        switchMarcacaoDesmacacaoCheck();
    }//GEN-LAST:event_comboMarcacaoActionPerformed

    private void marcaDesmarcarChecks(Boolean value){
        for (int i = 0; i < tabelaPedido.getRowCount(); i++) {
            tabelaPedido.getModel().setValueAt(value, i, 0);
            tabelaPedido.repaint();
        }
    }
    
    private void radioFinalizadoActionPerformed(java.awt.event.ActionEvent evt) {                                                
        this.aluguel.getStatus().setId(Aluguel.FINALIZADO);
        carregarTabela();
    }                                               

    private void radioFinalComPendenciaActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        this.aluguel.getStatus().setId(Aluguel.FINALIZADO_COM_PENDENCIA);
        carregarTabela();
    }                                                      

    public static void main(String[] args) {
        TelaPesquisaPedido telaPesquisaCliente = new TelaPesquisaPedido();
        telaPesquisaCliente.setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlugarMarcados;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton campoCancelar;
    private javax.swing.JFormattedTextField campoCpf;
    private javax.swing.JComboBox<String> comboMarcacao;
    private javax.swing.ButtonGroup grupoRadio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioAlugado;
    private javax.swing.JRadioButton radioFinalComPendencia;
    private javax.swing.JRadioButton radioFinalizado;
    private javax.swing.JRadioButton radioPedido;
    private javax.swing.JTable tabelaPedido;
    // End of variables declaration//GEN-END:variables
}