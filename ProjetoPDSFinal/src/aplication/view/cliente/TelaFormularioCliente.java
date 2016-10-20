/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.cliente;

import aplication.dao.ClienteDAO;
import aplication.dao.TelefoneDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import aplication.modelo.Cliente;
import aplication.modelo.Telefone;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


public class TelaFormularioCliente extends javax.swing.JFrame {

    private List<Cliente> clientes;
    private Cliente cliente;
    private String opcao;
    private List<JTextField> telefones = new ArrayList<JTextField>();
    private List<Telefone> listTelefones = new ArrayList<>();
    private int cont = 0;
    private int somaDesce;
    private Long idTelefone;
    
    //Tela cadastrar
    public TelaFormularioCliente(List<Cliente> clientes){
        initComponents();
        this.clientes = clientes;
        this.opcao = "Cadastrar";
        
        preparaNomes();
        getRootPane().setDefaultButton(botaoCadastrar);       
        
        construiTextFieldDinamico();
    }

    //Tela Alterar
    public TelaFormularioCliente(Cliente cliente){
        initComponents();
        this.cliente = cliente;
        
        this.opcao = "Alterar";
        preparaNomes();
        
        setarValores(cliente);
    }    
    
    private void construiTextFieldDinamico(){
        if (cont < 3){
            somaDesce = somaDesce + 50;
            JTextField jTextField = null;
            try {
                jTextField = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
            } catch (ParseException ex) {
                Logger.getLogger(TelaFormularioCliente.class.getName()).log(Level.SEVERE, null, ex);
            }        
            telefones.add(jTextField);

            jPanel1.add(telefones.get(cont));
            telefones.get(cont).setBounds(18, 201 + somaDesce, 201, 28);

            cont++;
        }        
    }
    
    //prepara os nomes pro título da tela e o nome do botão
    private void preparaNomes(){
        setTitle(opcao + " Clientes");
        botaoCadastrar.setText(opcao);
    }
    
    private void setarValores(Cliente cliente){
        TelefoneDAO telefoneDAO = new TelefoneDAO();
        int i = 0;
        
        for (Telefone telefone : telefoneDAO.pesquisarTelefoneCliente(cliente.getId())) { 
            System.out.println(somaDesce);
            construiTextFieldDinamico();
            
            String telefoneLimpo = telefone.getNumero().replace("()", "").replace("-", "");
            telefones.get(i).setText(telefoneLimpo);
            i++;
            idTelefone = telefone.getId();
        }
        String cpfLimpo = cliente.getCpf().replace("-", "").replace(".", "");
        //String telefoneLimpo = this.cliente.getClifone().replace("()", "").replace("-", "");
        
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyy");
	String dataLimpa = s.format(cliente.getDataDasc().getTime());
        
        campoNome.setText(cliente.getNome());
        campoCpf.setText(cpfLimpo);
        campoHabilitacao.setText(cliente.getNumHabilitacao());
        //campoTelefone.setText(telefoneLimpo);
        campoDtNascimento.setText(dataLimpa);

        if (cliente.getSexo() == 'm'){
            radioMasculino.setSelected(true);
        }else{
            radioFeminino.setSelected(true);
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

        grupoSexo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        campoHabilitacao = new javax.swing.JTextField();
        botaoCancelar = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        radioMasculino = new javax.swing.JRadioButton();
        radioFeminino = new javax.swing.JRadioButton();
        campoCpf = new javax.swing.JFormattedTextField();
        campoDtNascimento = new javax.swing.JFormattedTextField();
        botaoAdicionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menu de opções:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, null, new java.awt.Color(51, 51, 51)));
        jPanel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Nome:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Cpf:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Telefone:");

        campoNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Data de Nascimento:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Número de Habilitação:");

        campoHabilitacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        botaoCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoCadastrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Sexo:");

        grupoSexo.add(radioMasculino);
        radioMasculino.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        radioMasculino.setSelected(true);
        radioMasculino.setText("Masculino");

        grupoSexo.add(radioFeminino);
        radioFeminino.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        radioFeminino.setText("Feminino");

        try {
            campoCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            campoDtNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        botaoAdicionar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoAdicionar.setText("Adicionar");
        botaoAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(campoHabilitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(campoNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(botaoAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10))
                                .addComponent(botaoCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(campoCpf, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(radioMasculino)
                            .addGap(18, 18, 18)
                            .addComponent(radioFeminino)))
                    .addComponent(campoDtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioMasculino)
                    .addComponent(radioFeminino))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(campoCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoDtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoHabilitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99))
                            .addComponent(botaoCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        Telefone telefone = null;
        
        Cliente cliente = new Cliente();        
        char sexo;
        
        cliente.setNome(campoNome.getText());
        
        cliente.setCpf(campoCpf.getText());
        cliente.setNumHabilitacao(campoHabilitacao.getText());
        
        //prepara o sexo para ir ou banco como 'm' ou 'f' 
        if (radioMasculino.isSelected()){
            sexo = 'm';
        }else{
            sexo = 'f';
        }
        
        cliente.setSexo(sexo);
        
        //prepara campoDtNascimento para ir ao banco como Calendar
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataConvertida = Calendar.getInstance();     
        try {
            dataConvertida.setTime(sdf.parse(campoDtNascimento.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(TelaFormularioCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cliente.setDataDasc(dataConvertida);       
        TelefoneDAO telefoneDAO = null;
        if (opcao.equals("Cadastrar")){
            cadastrar(cliente);
            
            for (JTextField jt : telefones) {
                telefone = new Telefone();
                telefone.setNumero(jt.getText());
                telefone.setCliente(cliente);
                telefone.setFuncionario(null);
                
                telefoneDAO = new TelefoneDAO();
                telefoneDAO.inserir(telefone);
            }
        }else{
            alterar(cliente);
            int i = 0;
            telefone = null;
            for (JTextField jt : telefones) {
                telefone = new Telefone();
                telefone.setId(idTelefone);
                telefone.setNumero(jt.getText());
                telefone.setCliente(cliente);
                telefone.setFuncionario(null);
                
                telefoneDAO = new TelefoneDAO();
                telefoneDAO.alterar(telefone);
                i++;
            }
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarActionPerformed
        
        construiTextFieldDinamico();
        System.out.println(somaDesce);
    }//GEN-LAST:event_botaoAdicionarActionPerformed
    
    private void cadastrar(Cliente cliente){
        ClienteDAO dao = new ClienteDAO();
        try {
            dao.inserir(cliente);
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!!");
            limpar();
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void alterar(Cliente cliente){
        ClienteDAO dao = new ClienteDAO();
        cliente.setId(this.cliente.getId());
        try {
            dao.alterar(cliente);
            
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!!");
            limpar();
            dispose();
        } catch (Exception e) {
        }
    }
    
    private void limpar(){
        this.campoNome.setText("");
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JFormattedTextField campoCpf;
    private javax.swing.JFormattedTextField campoDtNascimento;
    private javax.swing.JTextField campoHabilitacao;
    private javax.swing.JTextField campoNome;
    private javax.swing.ButtonGroup grupoSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton radioFeminino;
    private javax.swing.JRadioButton radioMasculino;
    // End of variables declaration//GEN-END:variables
}
