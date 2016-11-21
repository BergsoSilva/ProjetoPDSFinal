/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.pedido;

import aplication.dao.FormaPagamentoDAO;
import aplication.dao.PedidoDAO;
import aplication.dao.ProdutoDAO;
import aplication.modelo.Aluguel;
import aplication.modelo.FormPagamento;
import aplication.modelo.Produto;
import aplication.modelo.Status;
import aplication.regraDeNegocio.SingletonBiblioteca;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Erick
 */
public class TelaFormPagmento extends javax.swing.JFrame {

    private List<Aluguel> alugueisMarcados;
    private int verificaErro = 0;
    
    public TelaFormPagmento(List<Aluguel> alugueisMarcados) {
        initComponents();
        this.alugueisMarcados = alugueisMarcados;
        
        preencherComboFormaPagamento();
    }
    
    private void preencherComboFormaPagamento(){
        
        comboFormaPagamento.removeAllItems();
        comboFormaPagamento.addItem("");
        
        FormaPagamentoDAO dao = new FormaPagamentoDAO();
        
        FormPagamento formPagamento = new FormPagamento();
        formPagamento.setId(Long.parseLong("0"));
        formPagamento.setDescricao("");
        
        for (FormPagamento f : dao.pesquisar(formPagamento)) {
            comboFormaPagamento.addItem(f);
        }
    }
    private void alugar(Aluguel aluguel){
        Produto produto = aluguel.getProduto();
        Double contaSaldo = produto.getSaldo() - aluguel.getQuantidade(); 
        
        if (contaSaldo > 0){            
            Status status = aluguel.getStatus();
            status.setId(Long.parseLong("2"));

            produto.setSaldo(contaSaldo);
            
            aluguel.setStatus(status);
            aluguel.setFormPagamento((FormPagamento)comboFormaPagamento.getSelectedItem());

            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.finalizaPedido(aluguel);
            
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.alteraStatus(produto);
            verificaErro = 1;
            //SingletonBiblioteca.paraThred(thered);
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

        labelTitulo = new javax.swing.JLabel();
        comboFormaPagamento = new javax.swing.JComboBox<>();
        botaoCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelTitulo.setText("Escolha a Forma de Pagamento");

        comboFormaPagamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comboFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new Object[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        botaoCadastrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoCadastrar.setText("Confirmar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(labelTitulo)
                        .addGap(0, 113, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(comboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        int mensagem = JOptionPane.showConfirmDialog(null, "Deseja confirma essa operação? ",
                         null, JOptionPane.YES_NO_OPTION);
        if(mensagem == JOptionPane.YES_OPTION){
            for (Aluguel alugueisMarcado : alugueisMarcados) {
                alugar(alugueisMarcado);
            }
        
            if (verificaErro == 1){            
                JOptionPane.showMessageDialog(this, "Produto Alugado!!");
                dispose();
            }else{
               JOptionPane.showMessageDialog(null," Saldo menor que 0"); 
            }
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JComboBox<Object> comboFormaPagamento;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
}
