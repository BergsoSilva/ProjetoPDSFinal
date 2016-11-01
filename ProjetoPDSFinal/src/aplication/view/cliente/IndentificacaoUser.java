/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.cliente;

import aplication.dao.AluguelDAO;
import aplication.dao.ClienteDAO;
import aplication.dao.ItemAluguelDAO;
import aplication.dao.PedidoDAO;
import aplication.dao.ProdutoDAO;
import aplication.modelo.Aluguel;
import aplication.modelo.Carrinho;
import aplication.modelo.Cliente;
import aplication.modelo.FormPagamento;
import aplication.modelo.Funcionario;
import aplication.modelo.ItemAluguel;
import aplication.modelo.Status;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class IndentificacaoUser extends javax.swing.JFrame {
    private Cliente cliente;
    private List<Carrinho> carrinhos;
    public IndentificacaoUser(List<Carrinho> carrinhos) {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(this);
        this.carrinhos=carrinhos;
    }

    private boolean getUsuario(String cpf){
        ClienteDAO dao = new ClienteDAO();
        if (dao.clienteCPF(cpf)!= null){
            this.cliente=dao.clienteCPF(cpf);
            return true;
        }else{
            return false;
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoCPF = new javax.swing.JFormattedTextField();
        butaoAutorizar = new javax.swing.JButton();
        butaoCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        try {
            campoCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCPF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        butaoAutorizar.setText("Autorizar");
        butaoAutorizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butaoAutorizarActionPerformed(evt);
            }
        });

        butaoCancelar.setText("Cancelar");
        butaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butaoCancelarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Digite seu CPF");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(butaoAutorizar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(butaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(butaoAutorizar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(butaoCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butaoCancelarActionPerformed
       dispose();
    }//GEN-LAST:event_butaoCancelarActionPerformed
    private String dataHora(Calendar cal){
        Date data = new Date(System.currentTimeMillis());  
        SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        return formatarDate.format(data);
    }
    
    
        
        
    private void butaoAutorizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butaoAutorizarActionPerformed
    
        if (getUsuario(campoCPF.getText())){
           AluguelDAO daoaluguel= new AluguelDAO();
           PedidoDAO itemdao= new PedidoDAO();
           ProdutoDAO prodao = new ProdutoDAO();
           
           /* Setando status*/
           Status status =new Status();
           status.setId(Long.parseLong("1"));
           
           Aluguel aluguel= new Aluguel();// cabeçalho alugel
           ItemAluguel item;
           
           aluguel.setCliente(this.cliente);
           Calendar cal=Calendar.getInstance();
           aluguel.setDtAluguel(cal);
           FormPagamento pgto= new FormPagamento();
           pgto.setId(Long.parseLong("1"));
           aluguel.setFormPagamento(pgto);
           Funcionario f= new Funcionario();
           f.setId(Long.parseLong("1"));
           
        
           aluguel.setFuncionario(f);
           
           try {
               daoaluguel.inserir(aluguel);
               
               for (Carrinho carrinho : carrinhos) {
                      item= new ItemAluguel();
                      item.setAluguel(aluguel);
                      item.setProduto(prodao.produtoFind(carrinho.getCodReferncia()));
                      item.setQuantidade(carrinho.getQntde());
                      item.setStatus(status);
                      item.setTempo(12.0);
                      
                      itemdao.inserir(item);
               }
               JOptionPane.showMessageDialog(null,"Pedido efetuado co sucesso. Cliente ");
               cacelar();
           } catch (Exception ex) {
               Logger.getLogger(IndentificacaoUser.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           
           
       }
    }//GEN-LAST:event_butaoAutorizarActionPerformed
    
    private void cacelar(){
        campoCPF.setText("");
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butaoAutorizar;
    private javax.swing.JButton butaoCancelar;
    private javax.swing.JFormattedTextField campoCPF;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
