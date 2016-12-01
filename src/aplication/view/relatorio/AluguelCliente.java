/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.relatorio;

import aplication.controlle.AluguelController;
import aplication.dao.ClienteDAO;
import aplication.dao.PedidoDAO;
import aplication.modelo.Aluguel;
import aplication.modelo.Cliente;
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
        preencherCompoCliente();
        
        //carregarTabela();
    }

    private void carregarTabela(){
     
        pesquisar();
        TableRelatorioAluguel.setModel(new AluguelClienteTableModel(this.r));
    }
    private void  preencherCompoCliente(){
            ClienteDAO dao= new ClienteDAO();
            Cliente c= new Cliente();
            
            c.setNome("");
            comboCliente.removeAllItems();
            comboCliente.addItem("");
            for (Cliente cliente : dao.pesquisar(c)) {
                    
                
                    comboCliente.addItem(cliente);
             }
            
    }
    
    private void pesquisar(){
        
        PedidoDAO  dao = new PedidoDAO();
        
        Cliente cliente= new Cliente();
        
        
        cliente= (Cliente) comboCliente.getSelectedItem();


        this.r=dao.pesquisarRelatorio(cliente.getId());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelVisaoDados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableRelatorioAluguel = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        botaoSair = new javax.swing.JButton();
        botaoGrafico = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox();
        botaoPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanelVisaoDados.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 80, 40));

        botaoSair.setText("Sair");
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairActionPerformed(evt);
            }
        });
        jPanelVisaoDados.add(botaoSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 290, 71, 40));

        botaoGrafico.setText("Exibir Grafico");
        botaoGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGraficoActionPerformed(evt);
            }
        });
        jPanelVisaoDados.add(botaoGrafico, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 110, 40));

        getContentPane().add(jPanelVisaoDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 650, 330));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Selecionar Cliente");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        comboCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(comboCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 350, 30));

        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairActionPerformed
       dispose();
    }//GEN-LAST:event_botaoSairActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            AluguelController controlle = new AluguelController();
            Cliente cliente= new Cliente();
            cliente= (Cliente) comboCliente.getSelectedItem();
            controlle.gerarPorCodigo(Integer.parseInt(cliente.getId()+""));
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
         carregarTabela();
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void botaoGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGraficoActionPerformed
            AluguelController controlle = new AluguelController();
           controlle.gerarGrafico1();
    }//GEN-LAST:event_botaoGraficoActionPerformed

    public static void main(String[] args) {
        new AluguelCliente().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableRelatorioAluguel;
    private javax.swing.JButton botaoGrafico;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoSair;
    private javax.swing.JComboBox comboCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelVisaoDados;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
