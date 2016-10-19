/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.funcionario;

import aplication.Exceptions.BDMensagensPadrao;
import aplication.view.cliente.*;
import aplication.dao.ClienteDAO;
import aplication.dao.FuncionarioDAO;
import aplication.modelo.Cidade;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import aplication.modelo.Cliente;
import aplication.modelo.EstadoCivil;
import aplication.modelo.Funcao;
import aplication.modelo.Funcionario;
import javax.print.attribute.standard.JobOriginatingUserName;


public class TelaFormularioFuncionario extends javax.swing.JFrame {

    private List<Funcionario> funcionarios;
    private Funcionario funcionario;
    private String opcao;
    
    /*  listas paca combo */
    
 
    
    //Tela cadastrar
    public TelaFormularioFuncionario(List<Funcionario> funcionarios){
        initComponents();
        this.funcionario = funcionario;
        this.opcao = "Cadastrar";
        preencherCombos();
        preparaNomes();
        getRootPane().setDefaultButton(botaoCadastrar);
    }

    //Tela Alterar
    public TelaFormularioFuncionario(Funcionario funcionario){
        initComponents();
        this.funcionario = funcionario;
        
        this.opcao = "Alterar";
        preparaNomes();
        preencherCombos();
        setarValores(funcionario);
    } 
    
    // prencher combos
    
    
    private  void preencherCombos(){
        FuncionarioDAO  dao = new FuncionarioDAO();
        /* remover itens default */
        comboCidade.removeAllItems();
        comboEstadoCivil.removeAllItems();
        comboFuncao.removeAllItems();
        
        /****Opção vazia*****/
        comboCidade.addItem("");
        comboEstadoCivil.addItem("");
        comboFuncao.addItem("");
        
        /* adicionando objetos no combo*/
        
        for (Cidade cid : dao.listarCidades()) {
            comboCidade.addItem(cid);
        }
        for (Funcao fun : dao.listarFuncao()) {
            comboFuncao.addItem(fun);
        }
        
        for (EstadoCivil ec : dao.listarEstadoCivil()) {
            comboEstadoCivil.addItem(ec);
        }
               
    }
    
    
    //prepara os nomes pro título da tela e o nome do botão
    private void preparaNomes(){
        setTitle(opcao + " Funcionarios");
        botaoCadastrar.setText(opcao);
    }
    
    private void setarValores(Funcionario funcionario){
        FuncionarioDAO  dao = new FuncionarioDAO();
       /// String cpfLimpo = funcionario.getCpf().replace("-", "").replace(".", "");
        String telefoneLimpo = funcionario.getTelefone().replace("()", "").replace("-", "");
               
        SimpleDateFormat datadissao = new SimpleDateFormat("ddMMyyyy");
	String dataLimpa = datadissao.format(funcionario.getDataadimissao().getTime());
        
         SimpleDateFormat datademissao = new SimpleDateFormat("ddMMyyyy");
         String data = datademissao.format(funcionario.getDataadimissao().getTime());

        
        campoNome.setText(funcionario.getNome());
        campoDtAdminissao.setText(dataLimpa);
        campoDtDemissao.setText(data);
        campoSalario1.setText(funcionario.getSalario()+"");
        campoLogin.setText(funcionario.getLogin());
        campoSenha.setText(funcionario.getSenha());
        campoTelefone.setText(telefoneLimpo);
        String idfuncao= funcionario.getFuncao().getId()+"";
        comboFuncao.setSelectedIndex(Integer.parseInt(idfuncao));
        String idestcivil= funcionario.getEstadocivil().getId()+"";
        comboEstadoCivil.setSelectedIndex(Integer.parseInt(idestcivil));
        
        String idCidade= funcionario.getCidade().getId()+"";
        comboCidade.setSelectedIndex(Integer.parseInt(idCidade));
        
       
    }
    
    private void validadacoCampos(){
        
        if (campoNome.getText().equals("")){
             
             JOptionPane.showMessageDialog(null, "Campo vazio");
             campoNome.setFocusable(true);
            return ;
        }
        if (campoDtAdminissao.getText().equals(" /  /  ")){
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoSexo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        botaoCancelar = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();
        campoDtAdminissao = new javax.swing.JFormattedTextField();
        campoDtDemissao = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboFuncao = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        comboEstadoCivil = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        comboCidade = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        campoLogin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        campoSenha = new javax.swing.JPasswordField();
        campoSalario1 = new javax.swing.JTextField();
        campoTelefone = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menu de opções:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, null, new java.awt.Color(51, 51, 51)));
        jPanel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Senha:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, -1, -1));

        campoNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel1.add(campoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 48, 342, 28));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Data de Admissão:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        botaoCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(botaoCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 540, 104, 35));

        botaoCadastrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoCadastrar.setText("Salvar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });
        jPanel1.add(botaoCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 104, 35));

        try {
            campoDtAdminissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(campoDtAdminissao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 160, 28));

        try {
            campoDtDemissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(campoDtDemissao, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 160, 28));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Data de  Demissão:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Nome:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 27, -1, -1));

        comboFuncao.setModel(new javax.swing.DefaultComboBoxModel<>(new Object[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 230, 40));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Telefone:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        comboEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new Object[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboEstadoCivil, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 230, 40));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Funcão:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        comboCidade.setModel(new javax.swing.DefaultComboBoxModel<>(new Object[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 230, 40));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Estado Civil:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Salario");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));
        jPanel1.add(campoLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 150, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Cidade:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Login");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        campoSenha.setText("jPasswordField1");
        jPanel1.add(campoSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, 150, 30));

        campoSalario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoSalario1ActionPerformed(evt);
            }
        });
        jPanel1.add(campoSalario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 110, 30));

        try {
            campoTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoTelefone.setText("(  )     -    ");
        jPanel1.add(campoTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 230, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed
    private Calendar formatarData(String data ){
        SimpleDateFormat stringData = new SimpleDateFormat("dd/MM/yyyy");
        
        Calendar calendar = Calendar.getInstance();
        
        try {
            calendar.setTime(stringData.parse(data));
            
            return  calendar;
        } catch (ParseException ex) {
            Logger.getLogger(TelaFormularioFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
     return  null;
    }
    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        validadacoCampos();
        Funcionario funcioario = new Funcionario();        
    
        
        funcioario.setNome(campoNome.getText());
        
      
        
        funcioario.setDataadimissao(formatarData(campoDtAdminissao.getText() ));
        funcioario.setDatademissao(formatarData(campoDtDemissao.getText() ));
        funcioario.setSalario(Double.parseDouble(campoSalario1.getText()));
        funcioario.setCidade((Cidade) comboCidade.getSelectedItem());
        funcioario.setFuncao((Funcao) comboFuncao.getSelectedItem());
        funcioario.setTelefone(campoTelefone.getText());
        funcioario.setEstadocivil((EstadoCivil) comboEstadoCivil.getSelectedItem());
        funcioario.setLogin(campoLogin.getText());
        funcioario.setSenha(campoSenha.getText());
        
        
        
        
        if (opcao.equals("Cadastrar")){
            cadastrar(funcioario);
        }else{
            alterar(funcioario);
        } 
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void campoSalario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSalario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoSalario1ActionPerformed
    
    private void cadastrar(Funcionario funcionario){
      
        FuncionarioDAO dao = new FuncionarioDAO();
        
        try {
           validadacoCampos();
            dao.inserir(funcionario);
            limpar();
     
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void alterar(Funcionario funcionario){
        validadacoCampos();
        FuncionarioDAO dao = new FuncionarioDAO();
        
        funcionario.setId(this.funcionario.getId());
        
        try {
            dao.alterar(funcionario);
            limpar();
            dispose();
        } catch (Exception e) {
        }
    }
    
    private void limpar(){
        this.campoNome.setText("");
        this.campoSenha.setText("");
        this.campoLogin.setText("");
        this.campoSenha.setText("");
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JFormattedTextField campoDtAdminissao;
    private javax.swing.JFormattedTextField campoDtDemissao;
    private javax.swing.JTextField campoLogin;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoSalario1;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JFormattedTextField campoTelefone;
    private javax.swing.JComboBox<Object> comboCidade;
    private javax.swing.JComboBox<Object> comboEstadoCivil;
    private javax.swing.JComboBox<Object> comboFuncao;
    private javax.swing.ButtonGroup grupoSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
