/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.relatorio;

import aplication.Exceptions.BDException;
import aplication.dao.GrupoProdutoDAO;
import aplication.dao.ProdutoDAO;
import aplication.modelo.GrupoProduto;
import aplication.modelo.Produto;
import aplication.relatorio.AluguelRelatorio;
import aplication.view.grupoproduto.TabelaModeloGrupoProduto;
import aplication.view.produto.TabelaModeloProduto;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author flavio
 */
public class AluguelRelatorioTela extends javax.swing.JFrame {
    
    private Calendar dataInicial;
    private Calendar dataFinal;
    private List<Produto> produtos;
    private List<GrupoProduto> grupoProdutos;
    private int radioFiltroEscolhido;
    private int radioTipoEscolhido;
    private static final int RADIO_TODOS = 1;
    private static final int RADIO_PRODUTO = 2;
    private static final int RADIO_CATEGORIA = 3;
    private static final int RADIO_DATA = 4;
    private static final int RADIO_DIA_DA_SEMANA = 5;
    
    /**
     * Creates new form tempoMedioAluguelProduto
     */
    public AluguelRelatorioTela() {
        initComponents();
        this.dataInicial = Calendar.getInstance();
        this.dataFinal = Calendar.getInstance();
        this.radioTipoEscolhido = RADIO_DATA;
        radioFiltroEscolhido = RADIO_TODOS;
    }
    
    private void gerarRelatorio() throws BDException, JRException, SQLException, ParseException{
        if (dataInicialEValida() && dataFinalEValida()){
            if ( ! dataInicial.after(dataFinal)){
                int itemSelecionado = tabelaPesquisa.getSelectedRow();
                AluguelRelatorio relatorio = new AluguelRelatorio(this.dataInicial, this.dataFinal);

                switch (this.radioFiltroEscolhido) {
                    case RADIO_TODOS: {
                        if (this.radioTipoEscolhido == RADIO_DATA){
                            relatorio.alugueisPorData();
                        }else{
                            relatorio.alugueisPorDiaDaSemana();
                        }
                        break;
                    }
                    case RADIO_PRODUTO: {
                        if (itemSelecionado >= 0){
                            Long idProdutoSelecionado;
                            idProdutoSelecionado = this.produtos.get(itemSelecionado).getId();

                            if (radioTipoEscolhido == RADIO_DATA){
                                relatorio.alugueisPorDataEProduto(idProdutoSelecionado);
                            } else{
                                relatorio.alugueisPorDiaDaSemanaEProduto(idProdutoSelecionado);
                            }
                        }else {
                            JOptionPane.showMessageDialog(null, "Selecione um item da tabela!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
                    case RADIO_CATEGORIA: {
                        if (itemSelecionado >= 0){
                            Long idGrupoProdutoSelecionado;
                            idGrupoProdutoSelecionado = this.grupoProdutos.get(itemSelecionado).getId();

                            if (radioTipoEscolhido == RADIO_DATA){
                                relatorio.alugueisPorDataECategoria(idGrupoProdutoSelecionado);
                            } else{
                                relatorio.alugueisPorDiaDaSemanaECategoria(idGrupoProdutoSelecionado);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Selecione um item da tabela!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data inicial não pode ser maior que data final!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean dataInicialEValida(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data;
        
        try {
            data = sdf.parse(campoDataInicial.getText());
            this.dataInicial.setTime(data);
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
            return true;
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data final inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private void pesquisar(){
        if (radioFiltroEscolhido == RADIO_PRODUTO){
            pesquisarProduto();
            tabelaPesquisa.setModel(new TabelaModeloProduto(produtos));
        }else {
            pesquisarCategoria();
            tabelaPesquisa.setModel(new TabelaModeloGrupoProduto(grupoProdutos));
        }
        
        tabelaPesquisa.setEnabled(true);
    }
    
    private void pesquisarProduto(){
        Produto produto =  new Produto();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        produto.setNome(campoPesquisa.getText());
        this.produtos = produtoDAO.pesquisar(produto);
    }
    
    private void pesquisarCategoria(){
        GrupoProduto grupoProduto = new GrupoProduto();
        GrupoProdutoDAO grupoProdutoDAO = new GrupoProdutoDAO();
        
        grupoProduto.setDescricao(campoPesquisa.getText());
        this.grupoProdutos = grupoProdutoDAO.pesquisar(grupoProduto);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoRadio = new javax.swing.ButtonGroup();
        grupoRadioData = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoDataInicial = new javax.swing.JFormattedTextField();
        campoDataFinal = new javax.swing.JFormattedTextField();
        botaoGerarRelatorio = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        radioTodos = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        radioProduto = new javax.swing.JRadioButton();
        radioCategoria = new javax.swing.JRadioButton();
        radioData = new javax.swing.JRadioButton();
        radioDiaDaSemana = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPesquisa = new javax.swing.JTable();
        campoPesquisa = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório: Tempo Médio de Alguel");

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

        grupoRadio.add(radioTodos);
        radioTodos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radioTodos.setSelected(true);
        radioTodos.setText("Todos");
        radioTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTodosActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Aluguéis por:");

        grupoRadio.add(radioProduto);
        radioProduto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radioProduto.setText("Produto");
        radioProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioProdutoActionPerformed(evt);
            }
        });

        grupoRadio.add(radioCategoria);
        radioCategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radioCategoria.setText("Categoria");
        radioCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCategoriaActionPerformed(evt);
            }
        });

        grupoRadioData.add(radioData);
        radioData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radioData.setSelected(true);
        radioData.setText("Data");
        radioData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDataActionPerformed(evt);
            }
        });

        grupoRadioData.add(radioDiaDaSemana);
        radioDiaDaSemana.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radioDiaDaSemana.setText("Dia da Semana");
        radioDiaDaSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDiaDaSemanaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Filtrar por:");

        tabelaPesquisa.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaPesquisa.setEnabled(false);
        tabelaPesquisa.setRowSelectionAllowed(true);
        tabelaPesquisa.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tabelaPesquisa);

        campoPesquisa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        campoPesquisa.setEnabled(false);

        botaoPesquisar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botaoPesquisar.setEnabled(false);
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(botaoGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioData)
                            .addComponent(jLabel3)
                            .addComponent(radioTodos))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radioProduto)
                                .addGap(42, 42, 42)
                                .addComponent(radioCategoria))
                            .addComponent(radioDiaDaSemana))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(campoDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioData)
                    .addComponent(radioDiaDaSemana))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioTodos)
                    .addComponent(radioProduto)
                    .addComponent(radioCategoria))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoGerarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarRelatorioActionPerformed
        try {
            gerarRelatorio();
        } catch (BDException | JRException | SQLException | ParseException ex) {}
    }//GEN-LAST:event_botaoGerarRelatorioActionPerformed

    private void radioTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTodosActionPerformed
        radioFiltroEscolhido = RADIO_TODOS;
        campoPesquisa.setEnabled(false);
        botaoPesquisar.setEnabled(false);
        tabelaPesquisa.setEnabled(false);
    }//GEN-LAST:event_radioTodosActionPerformed

    private void radioProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioProdutoActionPerformed
        radioFiltroEscolhido = RADIO_PRODUTO;
        campoPesquisa.setEnabled(true);
        botaoPesquisar.setEnabled(true);
        pesquisar();
    }//GEN-LAST:event_radioProdutoActionPerformed

    private void radioCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCategoriaActionPerformed
        radioFiltroEscolhido = RADIO_CATEGORIA;
        campoPesquisa.setEnabled(true);
        botaoPesquisar.setEnabled(true);
        pesquisar();
    }//GEN-LAST:event_radioCategoriaActionPerformed

    private void radioDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDataActionPerformed
        radioTipoEscolhido = RADIO_DATA;
    }//GEN-LAST:event_radioDataActionPerformed

    private void radioDiaDaSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDiaDaSemanaActionPerformed
        radioTipoEscolhido = RADIO_DIA_DA_SEMANA;
    }//GEN-LAST:event_radioDiaDaSemanaActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        pesquisar();
    }//GEN-LAST:event_botaoPesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(AluguelRelatorioTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AluguelRelatorioTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AluguelRelatorioTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AluguelRelatorioTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AluguelRelatorioTela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoGerarRelatorio;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JFormattedTextField campoDataFinal;
    private javax.swing.JFormattedTextField campoDataInicial;
    private javax.swing.JTextField campoPesquisa;
    private javax.swing.ButtonGroup grupoRadio;
    private javax.swing.ButtonGroup grupoRadioData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioCategoria;
    private javax.swing.JRadioButton radioData;
    private javax.swing.JRadioButton radioDiaDaSemana;
    private javax.swing.JRadioButton radioProduto;
    private javax.swing.JRadioButton radioTodos;
    private javax.swing.JTable tabelaPesquisa;
    // End of variables declaration//GEN-END:variables
}
