
package aplication.view.produto;

import aplication.dao.ProdutoDAO;
import aplication.modelo.Produto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;


public class TelaPesquisaProduto extends javax.swing.JFrame {

   private List<Produto> produtos = new ArrayList<>();
   private Produto produto = new Produto();
    
    public TelaPesquisaProduto(){
        initComponents();      
        
        carregarTabela();
        carregarMenuFlutuante();       
        
    }
    
    private void pesquisar(){        
        preencherProduto();
        try {            
            ProdutoDAO dao = new ProdutoDAO();
            this.produtos = (List<Produto>) dao.pesquisar(produto);
        }catch(Exception e){
            e.printStackTrace();
        }           
        
    }
    
    @SuppressWarnings("unchecked")
    
    private void carregarTabela(){
        pesquisar();
        tabelaProduto.setModel(new TabelaModeloProduto(produtos));
    }
    
    private void carregarMenuFlutuante(){
        JMenuItem menuItem[] = {new JMenuItem("Ver detalhes"), new JMenuItem("Alterar")};
        
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
        TelaVerDetalhesProduto telaVerDetalhesProduto = new TelaVerDetalhesProduto(produto);
        telaVerDetalhesProduto.setVisible(true);
    }
    
    private void alterar(){
        TelaFormularioProduto telaFormularioProduto = new TelaFormularioProduto(produto);
        telaFormularioProduto.setVisible(true);
    }
    
    private void preencherProduto(){
        this.produto.setNome(campoNome.getText());
    }
    
    private void selecionarProduto(MouseEvent evt){
        int linha = tabelaProduto.rowAtPoint(evt.getPoint());
        
        if(linha >= 0){
            tabelaProduto.setRowSelectionInterval(linha, linha);
            linha = tabelaProduto.getSelectedRow();
            
            this.produto = produtos.get(linha);            
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
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jLabel1 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JButton();
        campoCadastrar = new javax.swing.JButton();
        campoCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProduto = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Grupo Produto");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Nome:");

        campoNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        botaoPesquisar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        campoCadastrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campoCadastrar.setText("Cadastrar");
        campoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCadastrarActionPerformed(evt);
            }
        });

        campoCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campoCancelar.setText("Cancelar");
        campoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCancelarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados Encontrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, null, new java.awt.Color(51, 51, 51)));
        jPanel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        tabelaProduto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabelaProduto.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProdutoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaProdutoMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaProduto);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(campoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void campoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCadastrarActionPerformed
        TelaFormularioProduto telaFormularioProduto = new TelaFormularioProduto(produtos);
        telaFormularioProduto.setVisible(true);
    }//GEN-LAST:event_campoCadastrarActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        carregarTabela();
    }//GEN-LAST:event_formWindowGainedFocus

    private void campoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_campoCancelarActionPerformed

    private void tabelaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdutoMouseClicked
        
    }//GEN-LAST:event_tabelaProdutoMouseClicked

    private void tabelaProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdutoMouseReleased
        selecionarProduto(evt);
        realizarAcao(evt);
    }//GEN-LAST:event_tabelaProdutoMouseReleased

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        pesquisar();
        carregarTabela();
    }//GEN-LAST:event_botaoPesquisarActionPerformed

 /*   public static void main(String[] args) {
        TelaPesquisaProduto telaPesquisaGrupoProduto = new TelaPesquisaProduto();
        telaPesquisaGrupoProduto.setVisible(true);
    }*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton campoCadastrar;
    private javax.swing.JButton campoCancelar;
    private javax.swing.JTextField campoNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaProduto;
    // End of variables declaration//GEN-END:variables
}
