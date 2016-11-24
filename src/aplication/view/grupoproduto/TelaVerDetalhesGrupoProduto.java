
package aplication.view.grupoproduto;

import aplication.dao.GrupoProdutoDAO;
import aplication.modelo.GrupoProduto;
import javax.swing.JOptionPane;

/**
 *
 * @author Erick
 */
public class TelaVerDetalhesGrupoProduto extends javax.swing.JFrame {

    private GrupoProduto grupoProduto;
    
    public TelaVerDetalhesGrupoProduto(GrupoProduto grupoProduto) {
        initComponents();
        
        this.grupoProduto = grupoProduto;
        
        setar();
    }

    private void setar(){        
        labelDescricao.setText(grupoProduto.getDescricao());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelDescricao = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaAlterar = new javax.swing.JButton();
        botaoCancelar3 = new javax.swing.JButton();
        boraExcluir = new javax.swing.JButton();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Descrição");

        labelDescricao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menus de Opções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        botaAlterar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaAlterar.setText("Alterar");
        botaAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaAlterarActionPerformed(evt);
            }
        });

        botaoCancelar3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoCancelar3.setText("Cancelar");
        botaoCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelar3ActionPerformed(evt);
            }
        });

        boraExcluir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        boraExcluir.setText("Excluir");
        boraExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boraExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boraExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(botaoCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boraExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(223, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaAlterarActionPerformed
        TelaFormularioGrupoProduto telaFormularioGrupoProduto = new TelaFormularioGrupoProduto(grupoProduto);
        telaFormularioGrupoProduto.setVisible(true);
        dispose();
    }//GEN-LAST:event_botaAlterarActionPerformed

    private void botaoCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelar3ActionPerformed
        dispose();
    }//GEN-LAST:event_botaoCancelar3ActionPerformed

    private void boraExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boraExcluirActionPerformed
        int mensagem = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir " +
                       grupoProduto.getDescricao()+ "?", null, JOptionPane.YES_NO_OPTION);
        if(mensagem == JOptionPane.YES_OPTION){
            excluir();  
        }
    }//GEN-LAST:event_boraExcluirActionPerformed

    private void excluir(){
               GrupoProdutoDAO dao = new GrupoProdutoDAO();
        try {
            dao.deletar(grupoProduto.getId());
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (Exception e) {
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boraExcluir;
    private javax.swing.JButton botaAlterar;
    private javax.swing.JButton botaoCancelar3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelDescricao;
    // End of variables declaration//GEN-END:variables
}
