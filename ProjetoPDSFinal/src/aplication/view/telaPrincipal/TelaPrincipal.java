
 
package aplication.view.telaPrincipal;

import aplication.dao.GrupoProdutoDAO;
import aplication.renderizador.JTableRenderer;
import aplication.dao.ProdutoDAO;
import aplication.modelo.GrupoProduto;
import aplication.modelo.Produto;
import aplication.renderizador.ButtonRederer;
import aplication.renderizador.ImageRederer;
import aplication.view.aluguel.TelaVerDetalhesAluguel;
import aplication.view.cliente.TelaPesquisaCliente;
import aplication.view.funcionario.TelaPesquisaFuncionario;
import aplication.view.grupoproduto.TelaPesquisaGrupoProduto;
import aplication.view.pedido.TelaPesquisaPedido;
import aplication.view.produto.TelaPesquisaProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.TableColumnModel;


/**
 *
 * @author pc
 */
public class TelaPrincipal extends javax.swing.JFrame {
    private Produto produto=new Produto();
    private List<Produto> produtos= new ArrayList<>();
    private List<Produto> procarrinhoMemo= new ArrayList<>();
   
    public TelaPrincipal() {
        initComponents();
       
        setLocationRelativeTo(this);
        pesquisar();
        carregarTabela();
        carregarMenuFlutuante();
        prencherComboCategoria();
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        
        menuAluguel.setVisible(false);
        menuCadastro.setVisible(false);
        menuRalatorios.setVisible(false);
    }
    
    private void carregarTabela(){
        pesquisar();
        
        ImageRederer imagem= new ImageRederer();
        ImageRederer imagem2= new ImageRederer();
        
        
        
       
        tabelaCatalog.setModel(new TabelaModeloCatalago(produtos));
        
        
        
        tabelaCatalog.setAutoCreateRowSorter(true);
        tabelaCatalog.setRowHeight(150);
        tabelaCatalog.setUpdateSelectionOnSort(true);
        
        /**  Coluna com imagen na celular*/
        tabelaCatalog.getColumnModel().getColumn(0).setPreferredWidth(100);
        
        tabelaCatalog.getColumnModel().getColumn(1).setPreferredWidth(30);
        tabelaCatalog.getColumnModel().getColumn(2).setPreferredWidth(5);
       
        
       
        tabelaCatalog.getColumnModel().getColumn(0).setCellRenderer(imagem);
        
        tabelaCatalog.getColumnModel().getColumn(2).setCellRenderer(imagem2); 
        
        
        
        //alinhaTableCentro(tabelaCatalog, tabelaCatalog.getSelectedColumns());
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
        int coluna = tabelaCatalog.columnAtPoint(evt.getPoint());
        
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
            if (evt.getClickCount()==1){
                
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
    
    private void prencherComboCategoria(){
        GrupoProdutoDAO dao = new GrupoProdutoDAO();
        
        comboCategoria.removeAllItems();
        comboCategoria.addItem("");
        
        for (GrupoProduto gp : dao.findAll()) {
            comboCategoria.addItem(gp);
        }
        
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCarrinhoPedido = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        valorAPagar = new javax.swing.JLabel();
        botaFecharPedido = new javax.swing.JButton();
        comboCategoria = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        barraDeMenu = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuCadastro = new javax.swing.JMenu();
        minFuncionario = new javax.swing.JMenuItem();
        miCliente = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        miGrupoProduto = new javax.swing.JMenuItem();
        menuRalatorios = new javax.swing.JMenu();
        menuAluguel = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuAutenticação = new javax.swing.JMenu();
        miLogin = new javax.swing.JMenuItem();
        miLogaut = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        tabelaCatalog.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 51, 51)));
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
        tabelaCatalog.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelaCatalog.setShowVerticalLines(false);
        tabelaCatalog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCatalogMouseClicked(evt);
            }
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

        tableCarrinhoPedido.setModel(new javax.swing.table.DefaultTableModel(
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
        tableCarrinhoPedido.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tableCarrinhoPedido);

        jLabel1.setText("Total Produto:");

        labelTotal.setText("..");

        jLabel2.setText("Valor R$: ");

        valorAPagar.setText("..");

        botaFecharPedido.setText("Fechar Pedido");
        botaFecharPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                botaFecharPedidoMouseReleased(evt);
            }
        });
        botaFecharPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaFecharPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(valorAPagar)
                                    .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(botaFecharPedido))
                        .addContainerGap(141, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(valorAPagar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(botaFecharPedido))
        );

        comboCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Categoria");

        menuFile.setText("File");

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuFile.add(jMenuItem1);

        barraDeMenu.add(menuFile);

        menuCadastro.setText("Cadastro");

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
        barraDeMenu.add(menuRalatorios);

        menuAluguel.setText("Aluguel");

        jMenuItem2.setText("Pedidos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuAluguel.add(jMenuItem2);

        barraDeMenu.add(menuAluguel);

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoPesquisa)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    public void liberRecursos( String usuario, boolean bool ){            
        this.menuCadastro.setVisible(true);
        this.menuAluguel.setVisible(true);
        this.menuRalatorios.setVisible(true);
        this.miLogin.setEnabled(false);
        this.miLogaut.setEnabled(true);
           
        this.minFuncionario.setEnabled(bool);
            
        JOptionPane.showMessageDialog(null,"Liberado para : "+ usuario);
    }
    
    public void travarRecursos( ){            
    this.menuCadastro.setEnabled(false);
    this.menuCadastro.setEnabled(false);
    this.menuFile.setEnabled(false);
    this.menuRalatorios.setEnabled(false);
    this.miLogin.setEnabled(true);
    this.miLogaut.setEnabled(false);
    this.menuAluguel.setVisible(false);
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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        TelaPesquisaPedido telaPesquisaPedido = new TelaPesquisaPedido();
        telaPesquisaPedido.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void tabelaCatalogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCatalogMouseClicked
        adicionarCarrinho(evt);
        perrencherTabelaCarrinho();
        realizaCalculos();
    }//GEN-LAST:event_tabelaCatalogMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
       perrencherTabelaCarrinho();
    }//GEN-LAST:event_formWindowGainedFocus

    private void botaFecharPedidoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaFecharPedidoMouseReleased
       JOptionPane.showMessageDialog(null,"Carregar Item Alugel");
    }//GEN-LAST:event_botaFecharPedidoMouseReleased

    private void botaFecharPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaFecharPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaFecharPedidoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraDeMenu;
    private javax.swing.JButton botaFecharPedido;
    private javax.swing.JTextField campoPesquisa;
    private javax.swing.JComboBox comboCategoria;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JMenu menuAluguel;
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
    private javax.swing.JTable tableCarrinhoPedido;
    private javax.swing.JLabel valorAPagar;
    // End of variables declaration//GEN-END:variables

    
    /** *
     * Metodos para realizar a ação  de inserção no carrinho
     */
    private void adicionarCarrinho(MouseEvent evt) {
        
        /*  Seleciona a coluna clicada*/
        int coluna = tabelaCatalog.columnAtPoint(evt.getPoint());
        
        /* Verifica se a coluna clicada e a coluna que esta a imagem de ADD */
        if (coluna== 2){
            /*  Seleciona a linha da JTable com o Objeto*/
            int linha = tabelaCatalog.rowAtPoint(evt.getPoint());
            
            /* Se houver clik*/
            if(linha >= 0){
                
                tabelaCatalog.setRowSelectionInterval(linha, linha);
                
                linha = tabelaCatalog.getSelectedRow();
                /**
                 * Metodo@carregarCarrinho
                 * Possui um paramentro do tipo produto
                 * Esse metedo será responsavel por adicionar
                 * o Objeto aa List<procarrinhoMemo> 
                 */
                carregarCarrinho(produtos.get(linha));
             }
            
          
        }
     
    }
    
    /**
     * Metodo carregarCarrinho
     * @param produto 
     */
    private void carregarCarrinho(Produto produto){
         
         /* Verifiação se produto da existe na lista*/
         if (procarrinhoMemo.contains(produto)){
             JOptionPane.showMessageDialog(null, "Produto ja existe");
         }else
              procarrinhoMemo.add(produto);
    }
    /**
     * Metodo responsavéil por preencher a JTable tableCarrinhoPedido
     */
    private void perrencherTabelaCarrinho(){
        tableCarrinhoPedido.setModel(new TabelaModeloCarrinhoPedido(this.procarrinhoMemo));
    }
    
    private void realizaCalculos(){
        
        labelTotal.setText(procarrinhoMemo.size()+"");
        
        valorAPagar.setText("");
        
        Double valorApagar=0.0;
        
        for (Produto p : procarrinhoMemo) {
            valorApagar=valorApagar+p.getPrecoAluguel();
        }
         valorAPagar.setText(valorApagar+"");
         
    }
}
