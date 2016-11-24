/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.relatorio;

import aplication.controlle.AluguelController;
import aplication.dao.AluguelDAO;
import aplication.dao.PedidoDAO;
import aplication.modelo.Aluguel;
import aplication.relatorio.RelatorioAluguelCliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class AluguelCliente extends javax.swing.JFrame {

    public List<Aluguel> r= new ArrayList<>();
    
    public AluguelCliente() {
        initComponents();
        setLocationRelativeTo(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    private void carregarTabela(){
        pesquisar();
        TableRelatorioAluguel.setModel(new AluguelClienteTableModel(this.r));
    }
    private void pesquisar(){
        
        PedidoDAO  dao = new PedidoDAO();
        Aluguel l = new Aluguel();
        l.setId(Long.parseLong(campoCodigo.getText()));
        
        if (!campoCodigo.getText().equals("")){
                 this.r=dao.pesquisarRelatorio(l);
         }else{
        
            JOptionPane.showMessageDialog(null,"Campo esta vazio");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPesquisa = new javax.swing.JPanel();
        campoCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        botaoPesquisar = new javax.swing.JButton();
        jPanelVisaoDados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableRelatorioAluguel = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        botaoSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Codigo");

        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPesquisaLayout = new javax.swing.GroupLayout(panelPesquisa);
        panelPesquisa.setLayout(panelPesquisaLayout);
        panelPesquisaLayout.setHorizontalGroup(
            panelPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPesquisaLayout.createSequentialGroup()
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(botaoPesquisar))
                    .addComponent(jLabel1))
                .addContainerGap(401, Short.MAX_VALUE))
        );
        panelPesquisaLayout.setVerticalGroup(
            panelPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPesquisaLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelPesquisaLayout.createSequentialGroup()
                        .addComponent(botaoPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addGap(20, 20, 20))
        );

        getContentPane().add(panelPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 630, -1));

        jPanelVisaoDados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableRelatorioAluguel.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableRelatorioAluguel);

        jPanelVisaoDados.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 630, 270));

        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelVisaoDados.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 288, -1, 40));

        botaoSair.setText("Sair");
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairActionPerformed(evt);
            }
        });
        jPanelVisaoDados.add(botaoSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 290, 71, 40));

        getContentPane().add(jPanelVisaoDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 650, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
       carregarTabela();
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairActionPerformed
       dispose();
    }//GEN-LAST:event_botaoSairActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!campoCodigo.getText().equals("")){
            AluguelController con = new AluguelController();
 
              con.gerarPorCodigo(Integer.parseInt(campoCodigo.getText()));
            
        }else{
            JOptionPane.showMessageDialog(null,"Campo esta vazio");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableRelatorioAluguel;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoSair;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelVisaoDados;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelPesquisa;
    // End of variables declaration//GEN-END:variables
}
