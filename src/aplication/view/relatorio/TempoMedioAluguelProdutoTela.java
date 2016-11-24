/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.relatorio;

import aplication.Exceptions.BDException;
import aplication.relatorio.TempoMedioAluguelProdutoRelatorio;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author flavio
 */
public class TempoMedioAluguelProdutoTela extends javax.swing.JFrame {
    
    private Calendar dataInicial;
    private Calendar dataFinal;
    private Calendar dataMinima;
    
    /**
     * Creates new form tempoMedioAluguelProduto
     */
    public TempoMedioAluguelProdutoTela() {
        initComponents();
        this.dataInicial = Calendar.getInstance();
        this.dataFinal = Calendar.getInstance();
        this.dataMinima = Calendar.getInstance();
        dataMinima.set(2000, 01, 01);
    }
    
    private void gerarRelatorio() throws BDException, JRException, SQLException, ParseException{
        if (dataInicialEValida() && dataFinalEValida()){
            if ( ! dataInicial.after(dataFinal)){
                TempoMedioAluguelProdutoRelatorio relatorio = new TempoMedioAluguelProdutoRelatorio();
                relatorio.gerarRelatorio(dataInicial, dataFinal);
            } else {
                JOptionPane.showMessageDialog(null, "Data inicial não pode ser maior que data final!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean dataInicialEValida(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data;
        
        if (campoDataInicial.getText().equals("  /  /    ")){
            dataInicial.set(2000, 01, 01);
            return true;
        }
        
        try {
            data = sdf.parse(campoDataInicial.getText());
            this.dataInicial.setTime(data);
            
            if (dataInicial.before(dataMinima)){
                JOptionPane.showMessageDialog(null, "Data inicial inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data inicial inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private boolean dataFinalEValida(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data;
        
        try {
            data = sdf.parse(campoDataFinal.getText());
            this.dataFinal.setTime(data);
            
            if (dataFinal.before(dataMinima)){
                JOptionPane.showMessageDialog(null, "Data final inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            return true;
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data final inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoDataInicial = new javax.swing.JFormattedTextField();
        campoDataFinal = new javax.swing.JFormattedTextField();
        botaoGerarRelatorio = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório: Tempo Médio de Alguel por Produto");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Data Inicial");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Data Final");

        try {
            campoDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoDataInicial.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        try {
            campoDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoDataFinal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        botaoGerarRelatorio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botaoGerarRelatorio.setText("Gerar Relatório");
        botaoGerarRelatorio.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botaoGerarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarRelatorioActionPerformed(evt);
            }
        });

        botaoCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(botaoGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoDataInicial)
                            .addComponent(campoDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botaoGerarRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(botaoCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoGerarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarRelatorioActionPerformed
        try {
            gerarRelatorio();
        } catch (BDException | JRException | SQLException | ParseException ex) {}
    }//GEN-LAST:event_botaoGerarRelatorioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TempoMedioAluguelProdutoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TempoMedioAluguelProdutoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TempoMedioAluguelProdutoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TempoMedioAluguelProdutoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TempoMedioAluguelProdutoTela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoGerarRelatorio;
    private javax.swing.JFormattedTextField campoDataFinal;
    private javax.swing.JFormattedTextField campoDataInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
