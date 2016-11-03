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
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class IndentificacaoUser extends javax.swing.JFrame {
    private Cliente cliente;
    private List<Cliente> clientes = new ArrayList<>();
    private List<Carrinho> carrinhos;
    
    public IndentificacaoUser(List<Carrinho> carrinhos) {
         
        super ("Identificação Clinte");
        initComponents();
       
        setResizable(false);
        setLocationRelativeTo(this);
        this.carrinhos=carrinhos;
        labelCadCliente.setVisible(false);
         labelNome.setVisible(false);
         camoNome.setVisible(false);
        carregarCleinte();
    }

    private boolean getUsuario(String cpf){
         
        ClienteDAO dao = new ClienteDAO();
        boolean i=false ;
            
        if (!clientes.isEmpty()){    
            
            for (Cliente c : clientes) {
                   if (c.getCpf().equals(cpf)){
                         this.cliente=dao.clienteCPF(c.getCpf());
                    i= true;
                    break;
                }
            }
           
        }
        return i;
    }
    
    private void carregarCleinte(){
        
        ClienteDAO dao = new ClienteDAO();
        Cliente c = new Cliente();
        c.setNome("");
        this.clientes=dao.pesquisar(c);
    }
    
    private Double realizaCalculos(){
               
   
        Double valorApagar=0.0;
        
        for (Carrinho p : this.carrinhos) {
            valorApagar=valorApagar+p.getSubtotal();
        }
        
      return valorApagar;   
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoCPF = new javax.swing.JFormattedTextField();
        butaoAutorizar = new javax.swing.JButton();
        butaoCancelar = new javax.swing.JButton();
        labelCadCliente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        camoNome = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        try {
            campoCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCPF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        campoCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCPFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoCPFKeyReleased(evt);
            }
        });
        getContentPane().add(campoCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 284, 31));

        butaoAutorizar.setText("Finalizar");
        butaoAutorizar.setEnabled(false);
        butaoAutorizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butaoAutorizarActionPerformed(evt);
            }
        });
        getContentPane().add(butaoAutorizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 229, 108, 38));

        butaoCancelar.setText("Cancelar");
        butaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butaoCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(butaoCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 110, 40));

        labelCadCliente.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        labelCadCliente.setForeground(new java.awt.Color(0, 204, 204));
        labelCadCliente.setText("Clik aqui para se cadastrar");
        labelCadCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCadClienteMouseClicked(evt);
            }
        });
        getContentPane().add(labelCadCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 180, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Digite seu CPF:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 134, -1));

        labelNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelNome.setText("Nome:");
        getContentPane().add(labelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 280, -1));

        camoNome.setEditable(false);
        camoNome.setEnabled(false);
        getContentPane().add(camoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 280, 30));

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
    
      //  if (getUsuario(campoCPF.getText())){
           AluguelDAO daoaluguel= new AluguelDAO();
           PedidoDAO itemdao= new PedidoDAO();
           ProdutoDAO prodao = new ProdutoDAO();
           
           /* Setando status*/
           Status status =new Status();
           status.setId(Long.parseLong("1"));
           
           Aluguel aluguel= new Aluguel();// cabeçalho alugel
           ItemAluguel item;
           
           aluguel.setCliente(this.cliente);
           /* Pega a data do sistema */
           Calendar cal=Calendar.getInstance();
           aluguel.setDtAluguel(cal);
           /* Forma de pagamento padrão dinheiro*/
           FormPagamento pgto= new FormPagamento();
           pgto.setId(Long.parseLong("1"));
           aluguel.setFormPagamento(pgto);
           /*funcionario default */
           Funcionario f= new Funcionario();
           f.setId(Long.parseLong("1"));
           aluguel.setFuncionario(f);
           
           try {
               /* Inseri o aluguel e retorna a entity com o Id gerado*/
               daoaluguel.inserir(aluguel);
               
               /*Lista de itens adicionado no carrinho */
               for (Carrinho carrinho : carrinhos) {
                      item= new ItemAluguel();// cria um novo item aluguel
                      item.setAluguel(aluguel); // id aluguel no item
                      item.setProduto(prodao.produtoFind(carrinho.getCodReferncia()));// id produto
                      item.setQuantidade(carrinho.getQntde());// qntde solicitada 
                      item.setStatus(status);// status do item
                      item.setTempo(12.0);// empo default 12 horas
                      
                      itemdao.inserir(item);
               }
               JOptionPane.showMessageDialog(null,"<html>"
                                                + "<h2><b><u>Pedido realizado com Sucesso.</u> </b></h2><br>"
                                                + "<b> Cliene : </b>"+aluguel.getCliente().getNome()+"<br> "
                                                + "<h3><b>CFP : "+aluguel.getCliente().getCpf()+"</b></h3>"
                                                + "<h3><b> Valor Pedido R$ -"+realizaCalculos()+"</b></h3>"
                                                + " <br><br>"
                                                + "<b>Obrigado e volte sempre!!<b><br>"
                                                + "<b><u> Digira-se ao balcão para finalizar a locação</u><b></html>");
                cacelar();
           } catch (Exception ex) {
               Logger.getLogger(IndentificacaoUser.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           
           
     //  }else {
          //  JOptionPane.showMessageDialog(null, "Sorry, client not found.");
           // labelCadCliente.setVisible(true);
            
       // }
    }//GEN-LAST:event_butaoAutorizarActionPerformed

    private void labelCadClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCadClienteMouseClicked
        TelaPesquisaCliente c = new TelaPesquisaCliente();
        c.setVisible(true);
        labelCadCliente.setVisible(false);
    }//GEN-LAST:event_labelCadClienteMouseClicked

    private void campoCPFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCPFKeyReleased
      
    }//GEN-LAST:event_campoCPFKeyReleased

    private void campoCPFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCPFKeyPressed
      if (evt.getKeyCode()== KeyEvent.VK_ENTER){
          if (getUsuario(campoCPF.getText())){
              labelNome.setVisible(true);
              camoNome.setVisible(true);
              camoNome.setText(cliente.getNome());
              camoNome.setEnabled(true);
              
              butaoAutorizar.setEnabled(true);
          }else{
             JOptionPane.showMessageDialog(null, "Sorry, client not found.");
             labelCadCliente.setVisible(true); 
             labelNome.setVisible(false);
             camoNome.setVisible(false);
             butaoAutorizar.setEnabled(false);
             
          }
      }
    }//GEN-LAST:event_campoCPFKeyPressed
    
    private void cacelar(){
        campoCPF.setText("");
        camoNome.setText("");
    }
    
    public static void main(String[] args) {
        //new IndentificacaoUser();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butaoAutorizar;
    private javax.swing.JButton butaoCancelar;
    private javax.swing.JTextField camoNome;
    private javax.swing.JFormattedTextField campoCPF;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelCadCliente;
    private javax.swing.JLabel labelNome;
    // End of variables declaration//GEN-END:variables
}
