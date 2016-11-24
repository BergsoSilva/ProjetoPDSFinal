/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.produto;

import aplication.dao.GrupoProdutoDAO;
import aplication.dao.ProdutoDAO;
import java.util.List;
import java.io.IOException;
import aplication.modelo.GrupoProduto;
import aplication.modelo.Produto;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;


public class TelaFormularioProduto extends javax.swing.JFrame {

    private List<Produto> produtos;
    private Produto produto;
    private String opcao;
    private JFileChooser jFileChooser;
    private File arquivo;
    private GrupoProduto grupoProduto = null;
    
    //Tela cadastrar
    public TelaFormularioProduto(List<Produto> produtos){
        initComponents();
        this.produtos = produtos;
        this.opcao = "Cadastrar";
        
        preparaNomes();
        getRootPane().setDefaultButton(botaoCadastrar);
        preencherComboGrupoProduto();
        
    }

    //Tela Alterar
    public TelaFormularioProduto(Produto produto){
        initComponents();
        this.produto = produto;
        
        this.opcao = "Alterar";
        preparaNomes();
        setarValores();
        
    }    
    
    
    
    
    //prepara os nomes pro título da tela e o nome do botão
    private void preparaNomes(){
        setTitle(opcao + " Produtos");
        botaoCadastrar.setText(opcao);
    }
    
    private void setarValores(){
        preencherComboGrupoProduto();
    
        campoNome.setText(produto.getNome());
        campoPrecoAluguel.setText(produto.getPrecoAluguel()+"");
        comboGrupoProduto.getModel().setSelectedItem(produto.getGrupoProduto());
        spinnerQtde.setModel(new SpinnerNumberModel(produto.getQtde(), 1, 1000, 1));
        checkAtivo.setSelected(produto.isAtivo());
        
        File file = new File("");
        String caminho = file.getAbsolutePath() + "/src/" + produto.getImagem();
        String nomeImagem = produto.getImagem().replaceAll("img/", "");
        carregaImagem(caminho, nomeImagem);
    }
    
    private void preencherComboGrupoProduto(){
        
        comboGrupoProduto.removeAllItems();
        comboGrupoProduto.addItem("");
        
        GrupoProdutoDAO dao = new GrupoProdutoDAO();        
        
        grupoProduto = new GrupoProduto();
        grupoProduto.setId(Long.parseLong("0"));
        grupoProduto.setDescricao("");
        
        for (GrupoProduto g : dao.pesquisar(grupoProduto)) {
            comboGrupoProduto.addItem(g);
        }
    }
    
    private String salvarImagem() throws IOException{
        if (arquivo != null){
            arquivo = jFileChooser.getSelectedFile();
            String nomeImagem = arquivo.getName();
            File caminhoPasta = new File("");

            FileInputStream fisDe;

            String caminhoImagem = caminhoPasta.getAbsoluteFile() + "/" + "src/img/" + nomeImagem;

            try {
                fisDe = new FileInputStream(arquivo.getAbsolutePath());
                FileOutputStream fisPara = new FileOutputStream(caminhoImagem);

                FileChannel fcPara = fisDe.getChannel();
                FileChannel fcDe = fisPara.getChannel();

                if( fcPara.transferTo(0, fcPara.size(), fcDe ) == 0L ) {
                    fcPara.close();
                    fcDe.close();
                }
            } catch (FileNotFoundException ex) {
               Logger.getLogger(TelaFormularioProduto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TelaFormularioProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "img/" + nomeImagem;
        }else{
            return produto.getImagem();
        }        
    }
    
    private void carregaImagem(String caminho, String nomeImagem){
        ImageIcon icon = new ImageIcon(caminho);

        Rectangle rectangle = labelImagem.getBounds();

        Image image = icon.getImage().getScaledInstance(rectangle.width, rectangle.height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        labelImagem.setIcon(icon);
        
        labelNomeImagem.setText(nomeImagem);   
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
        panelImagem = new javax.swing.JPanel();
        labelImagem = new javax.swing.JLabel();
        botaoAbrir = new javax.swing.JButton();
        labelNomeImagem = new javax.swing.JLabel();
        botaoCadastrar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        checkAtivo = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        comboGrupoProduto = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        spinnerQtde = new javax.swing.JSpinner();
        campoPrecoAluguel = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imagem:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelImagemLayout = new javax.swing.GroupLayout(panelImagem);
        panelImagem.setLayout(panelImagemLayout);
        panelImagemLayout.setHorizontalGroup(
            panelImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
        );
        panelImagemLayout.setVerticalGroup(
            panelImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImagemLayout.createSequentialGroup()
                .addComponent(labelImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        botaoAbrir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoAbrir.setText("Carregar");
        botaoAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAbrirActionPerformed(evt);
            }
        });

        labelNomeImagem.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(botaoAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelNomeImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(botaoAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNomeImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        botaoCadastrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        botaoCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menu de opções:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Nome:");

        campoNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Preço Aluguel:");

        checkAtivo.setSelected(true);
        checkAtivo.setText("Ativo");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Quantidade:");

        comboGrupoProduto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comboGrupoProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new Object[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Grupo Produto:");

        spinnerQtde.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        spinnerQtde.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinnerQtde.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(comboGrupoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(359, 359, 359)
                        .addComponent(checkAtivo))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(118, 118, 118))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(campoPrecoAluguel)
                                .addGap(114, 114, 114)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinnerQtde, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(checkAtivo))
                .addGap(2, 2, 2)
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spinnerQtde, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoPrecoAluguel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(comboGrupoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(275, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(380, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed

        Produto produto = new Produto();
        
        int valorSpinner = Integer.parseInt(spinnerQtde.getValue().toString());
       
        produto.setNome(campoNome.getText());
        produto.setPrecoAluguel(Double.parseDouble( campoPrecoAluguel.getText()+""));
        produto.setSaldo(Double.parseDouble(valorSpinner+""));
        produto.setAtivo(checkAtivo.isSelected());
        produto.setGrupoProduto((GrupoProduto) comboGrupoProduto.getSelectedItem());
        
        produto.setQtde(valorSpinner);
        
        String caminhoImagem = null;
        try {
            caminhoImagem = salvarImagem();
        } catch (IOException ex) {
            Logger.getLogger(TelaFormularioProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        produto.setImagem(caminhoImagem);
        
        if (opcao.equals("Cadastrar")){
            cadastrar(produto);
        }else{
            alterar(produto);
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAbrirActionPerformed
        jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = jFileChooser.showOpenDialog(TelaFormularioProduto.this);
        
        if (res == JFileChooser.APPROVE_OPTION){
            arquivo = jFileChooser.getSelectedFile();
            
            carregaImagem(arquivo.getAbsolutePath(), arquivo.getName());
        }
    }//GEN-LAST:event_botaoAbrirActionPerformed
    
    private void cadastrar(Produto produto){
        ProdutoDAO dao = new ProdutoDAO();
        try {
            dao.inserir(produto);
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!!");
            limpar();
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void alterar(Produto produto){
        ProdutoDAO dao = new ProdutoDAO();
        produto.setId(this.produto.getId());
        try {
            dao.alterar(produto);
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!!");
            limpar();
            dispose();
        } catch (Exception e) {
        }
    }
    
    private void limpar(){
        //this.campoDescricao.setText("");
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAbrir;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoPrecoAluguel;
    private javax.swing.JCheckBox checkAtivo;
    private javax.swing.JComboBox<Object> comboGrupoProduto;
    private javax.swing.ButtonGroup grupoSexo;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelImagem;
    private javax.swing.JLabel labelNomeImagem;
    private javax.swing.JPanel panelImagem;
    private javax.swing.JSpinner spinnerQtde;
    // End of variables declaration//GEN-END:variables
}
