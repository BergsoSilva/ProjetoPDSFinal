/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.telaPrincipal;

import aplication.renderizador.JTableRenderer;
import aplication.dao.ProdutoDAO;
import aplication.modelo.Produto;
import aplication.renderizador.ImageRederer;
import aplication.view.aluguel.TelaVerDetalhesAluguel;
import aplication.view.cliente.TelaPesquisaCliente;
import aplication.view.funcionario.TelaPesquisaFuncionario;
import aplication.view.grupoproduto.TelaPesquisaGrupoProduto;
import aplication.view.produto.TelaPesquisaProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.TableColumnModel;


/**
 *
 * @author pc
 */
public class TelaPrincipal extends javax.swing.JFrame {
    private Produto produto=new Produto();
    private List<Produto> produtos= new ArrayList<>();
    private DefaultTableCellRenderer celrenCellRenderer;
   
    public TelaPrincipal() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(this);
        pesquisar();
        carregarTabela();
        carregarMenuFlutuante();
     
    }
    
    private void carregarTabela(){
        pesquisar();
        TableColumnModel columModel=tabelaCatalog.getColumnModel();
        JTableRenderer renderer = new JTableRenderer();
        
        tabelaCatalog.setModel(new TabelaModeloCatalago(produtos));
        
        tabelaCatalog.setAutoCreateRowSorter(true);
        tabelaCatalog.setRowHeight(50);
        
        tabelaCatalog.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabelaCatalog.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabelaCatalog.getColumnModel().getColumn(2).setPreferredWidth(10);
        tabelaCatalog.getColumnModel().getColumn(3).setPreferredWidth(20);
      
        tabelaCatalog.getColumnModel().getColumn(3).setCellRenderer( new ImageRederer());
       
       
        
    }
    
    

    private void pesquisar(){        
        preencherProduto();
        try {            
            ProdutoDAO dao = new ProdutoDAO();
            this.produtos = (List<Produto>) dao.pesquisar(produto);
        }catch(Exception e){
            e.printStackTrace();
        }           
        
    }
  
    private void preencherProduto(){
        this.produto.setNome(campoPesquisa.getText());
    }
    
    private void carregarMenuFlutuante(){
        
        JMenuItem menuItem[] = {new JMenuItem("Ver detalhes"), new JMenuItem("Alugar Produto")};
        
        menuItem[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              verDetalhes();
            }
        });
        
        menuItem[1].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                  //  alterar();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        for (JMenuItem menuItem1 : menuItem) {
            popupMenuProduto.add(menuItem1);
        }
    }
    /**
     * pegar referencia produto ao clicar em cima com o muose
     * @param evt 
     */
    private void selecionarProduto(MouseEvent evt){
        int linha = tabelaCatalog.rowAtPoint(evt.getPoint());
        
        if(linha >= 0){
            tabelaCatalog.setRowSelectionInterval(linha, linha);
            linha = tabelaCatalog.getSelectedRow();
            
            this.produto = produtos.get(linha);            
        }
    }
    
    /**
     * Ações que serão realizadas com o objero selecionado da tabela
     * @param evt 
     */
    private void realizarAcao(MouseEvent evt){
        /* botão direito */
        if (evt.getButton() == evt.BUTTON1){
            
            if(evt.getClickCount() > 1){
                verDetalhes();
            }
        }else{
            if(evt.getButton() == evt.BUTTON3){
                popupMenuProduto.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }
   
    private void verDetalhes(){
        TelaVerDetalhesAluguel verdtalhesProdutoAuguel = new TelaVerDetalhesAluguel(produto);
        verdtalhesProdutoAuguel.setVisible(true);
    }
    
     public static void main(String[] args) {
        TelaPrincipal principal = new TelaPrincipal();
        principal.setVisible(true);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuProduto = new javax.swing.JPopupMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCatalog = new javax.swing.JTable();
        campoPesquisa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        barraDeMenu = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuCadastro = new javax.swing.JMenu();
        minFuncionario = new javax.swing.JMenuItem();
        miCliente = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        miGrupoProduto = new javax.swing.JMenuItem();
        menuRalatorios = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuAutenticação = new javax.swing.JMenu();
        miLogin = new javax.swing.JMenuItem();
        miLogaut = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabelaCatalog.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaCatalog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaCatalogMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCatalog);

        campoPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoPesquisaKeyPressed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        menuFile.setText("File");
        menuFile.setEnabled(false);

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuFile.add(jMenuItem1);

        barraDeMenu.add(menuFile);

        menuCadastro.setText("Cadastro");
        menuCadastro.setEnabled(false);

        minFuncionario.setText("Funcionario");
        minFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minFuncionarioActionPerformed(evt);
            }
        });
        menuCadastro.add(minFuncionario);

        miCliente.setText("Cliente");
        miCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miClienteActionPerformed(evt);
            }
        });
        menuCadastro.add(miCliente);

        jMenuItem4.setText("Produto");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuCadastro.add(jMenuItem4);

        miGrupoProduto.setText("Gurpo Produto");
        miGrupoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miGrupoProdutoActionPerformed(evt);
            }
        });
        menuCadastro.add(miGrupoProduto);

        barraDeMenu.add(menuCadastro);

        menuRalatorios.setText("Relatórios");
        menuRalatorios.setEnabled(false);
        barraDeMenu.add(menuRalatorios);

        jMenu2.setText("Aluguel");

        jMenuItem2.setText("Pedidos");
        jMenu2.add(jMenuItem2);

        barraDeMenu.add(jMenu2);

        menuAutenticação.setText("Autenticar");

        miLogin.setText("Login");
        miLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLoginActionPerformed(evt);
            }
        });
        menuAutenticação.add(miLogin);

        miLogaut.setText("Logaut");
        miLogaut.setEnabled(false);
        miLogaut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLogautActionPerformed(evt);
            }
        });
        menuAutenticação.add(miLogaut);

        barraDeMenu.add(menuAutenticação);

        setJMenuBar(barraDeMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campoPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void minFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minFuncionarioActionPerformed
       TelaPesquisaFuncionario funcionario =new TelaPesquisaFuncionario();
       funcionario.setVisible(true);
    }//GEN-LAST:event_minFuncionarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pesquisar();
        carregarTabela();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void campoPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPesquisaKeyPressed
       if (evt.getKeyCode()== KeyEvent.VK_ENTER){
           pesquisar();
           carregarTabela();
       }
    }//GEN-LAST:event_campoPesquisaKeyPressed

    public void liberRecursos( String usuario ){
            
            this.menuCadastro.setEnabled(true);
            this.menuCadastro.setEnabled(true);
            this.menuFile.setEnabled(true);
            this.menuRalatorios.setEnabled(true);
            this.miLogin.setEnabled(false);
            this.miLogaut.setEnabled(true);
            
            JOptionPane.showMessageDialog(null,"Liberaddo para : "+ usuario);
    }
    
    
     public void travarRecursos( ){
            
            this.menuCadastro.setEnabled(false);
            this.menuCadastro.setEnabled(false);
            this.menuFile.setEnabled(false);
            this.menuRalatorios.setEnabled(false);
            this.miLogin.setEnabled(true);
            this.miLogaut.setEnabled(false);
    }
    private void tabelaCatalogMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCatalogMouseReleased
        selecionarProduto(evt);
        realizarAcao(evt);
    }//GEN-LAST:event_tabelaCatalogMouseReleased

    private void miClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miClienteActionPerformed
        TelaPesquisaCliente telaCadastroCliente = new TelaPesquisaCliente();
        telaCadastroCliente.setVisible(true);
                                             
    }//GEN-LAST:event_miClienteActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        TelaPesquisaProduto produto = new TelaPesquisaProduto();
        produto.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void miGrupoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miGrupoProdutoActionPerformed
        TelaPesquisaGrupoProduto grupo = new TelaPesquisaGrupoProduto();
        grupo.setVisible(true);
    }//GEN-LAST:event_miGrupoProdutoActionPerformed

    private void miLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLoginActionPerformed
       Login login= new Login(this);
       login.setVisible(true);
    }//GEN-LAST:event_miLoginActionPerformed

    private void miLogautActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLogautActionPerformed
        travarRecursos();
    }//GEN-LAST:event_miLogautActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraDeMenu;
    private javax.swing.JTextField campoPesquisa;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuAutenticação;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuRalatorios;
    private javax.swing.JMenuItem miCliente;
    private javax.swing.JMenuItem miGrupoProduto;
    private javax.swing.JMenuItem miLogaut;
    private javax.swing.JMenuItem miLogin;
    private javax.swing.JMenuItem minFuncionario;
    private javax.swing.JPopupMenu popupMenuProduto;
    private javax.swing.JTable tabelaCatalog;
    // End of variables declaration//GEN-END:variables
}
