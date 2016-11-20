
 
package aplication.view.telaPrincipal;

import aplication.dao.ClienteDAO;
import aplication.dao.GrupoProdutoDAO;
import aplication.dao.ProdutoDAO;
import aplication.modelo.Carrinho;
import aplication.modelo.Cliente;
import aplication.modelo.Funcionario;
import aplication.modelo.GrupoProduto;
import aplication.modelo.Produto;
import aplication.regraDeNegocio.ThretdTempoPedido;
import aplication.regraDeNegocio.SingletonBiblioteca;
import aplication.regraDeNegocio.retornarValor;
import aplication.renderizador.ImageRederer;
import aplication.renderizador.jPanelRederer;
import aplication.view.aluguel.TelaVerDetalhesAluguel;
import aplication.view.cliente.IndentificacaoUser;
import aplication.view.cliente.TelaPesquisaCliente;
import aplication.view.funcionario.TelaPesquisaFuncionario;
import aplication.view.grupoproduto.TelaPesquisaGrupoProduto;
import aplication.view.multa.TelaPesquisarMulta;
import aplication.view.pedido.TelaDevolucao;
import aplication.view.pedido.TelaPesquisaPedido;
import aplication.view.produto.TelaPesquisaProduto;
import aplication.view.relatorio.TempoMedioAluguelProdutoTela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


/**
 *
 * @author pc
 */
public class TelaPrincipal extends javax.swing.JFrame {
    private Produto produto=new Produto();
    Carrinho carrinho;
    private List<Produto> produtos= new ArrayList<>();
    private final List<Carrinho> procarrinhoMemo= new ArrayList<>();
    
    public Funcionario funcinario= new Funcionario();
    
    private Double sutotal=0.0;
    public static Integer qtde=1;
    public static Integer tempo=0;
    
    private  ThretdTempoPedido thered;
    
    
    
    boolean carrinhoAtivo=true;
    
   
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
        if (carrinhoAtivo==false){
           procarrinhoMemo.clear();
           carrinhoAtivo=true;
        } 
        this.setIconImage(new ImageIcon(getClass().getResource("/img/icone.png")).getImage());
        campoHoras.setVisible(false);
        jLabelQtde.setVisible(false);
        jLabelTempo1.setVisible(false);
        campoQuatidade.setVisible(false);
    }
    
    private void carregarTabela(){
        pesquisar();
        ImageRederer imagem= new ImageRederer();
        ImageRederer imagem2= new ImageRederer();
      
        jPanelRederer panel = new jPanelRederer();
        tabelaCatalog.setModel(new TabelaModeloCatalago(produtos));
        tabelaCatalog.setAutoCreateRowSorter(true);
        tabelaCatalog.setRowHeight(250);
        tabelaCatalog.setUpdateSelectionOnSort(true);
        
        /**  Coluna com imagen na celular*/
        tabelaCatalog.getColumnModel().getColumn(0).setPreferredWidth(250);
        
        tabelaCatalog.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabelaCatalog.getColumnModel().getColumn(2).setPreferredWidth(10);
        tabelaCatalog.getColumnModel().getColumn(0).setCellRenderer(imagem);
        tabelaCatalog.getColumnModel().getColumn(1).setCellRenderer(panel);
        tabelaCatalog.getColumnModel().getColumn(2).setCellRenderer(imagem2);
   
        tabelaCatalog.repaint();
        imagem.repaint();
        imagem2.repaint();
       
        
    }
   
    private void pesquisar(){  
        
        preencherProduto();
        try {            
            ProdutoDAO dao = new ProdutoDAO();
            if (comboCategoria.getSelectedIndex() >0)
                this.produtos=dao.pesquisarProdutoCategoria(produto);
            else
                 this.produtos = (List<Produto>) dao.pesquisar(produto);
        }catch(Exception e){
            e.printStackTrace();
        }           
        
    }
  
    private void preencherProduto(){
        if (comboCategoria.getSelectedIndex() >0)        
             this.produto.setGrupoProduto((GrupoProduto) comboCategoria.getSelectedItem());
     
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
     
     // Thred
     public class tempoPedido  extends Thread{

        @Override
        public void run() {
            for (int i=10; i>=0; i--) {
                try {
                    Thread.sleep(1000);
                    if (i==0){
                        procarrinhoMemo.clear();
                        JOptionPane.showMessageDialog(null, "adasdaaa");
                        SingletonBiblioteca.retornarValorParaStoque(qtde, produto);
                        perrencherTabelaCarrinho();
                        preencherProduto();
                    }
                } catch (InterruptedException ex) {
                        Logger.getLogger(ThretdTempoPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
                         System.out.println(" Time"+i +"Nome"+Thread.currentThread().getName());
           }
        }
         
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCarrinhoPedido = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        valorAPagar = new javax.swing.JLabel();
        botaFecharPedido = new javax.swing.JButton();
        botaoCancelarPedido = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel2 = new javax.swing.JPanel();
        comboCategoria = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        campoPesquisa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        campoHoras = new javax.swing.JTextField();
        jLabelQtde = new javax.swing.JLabel();
        campoQuatidade = new javax.swing.JTextField();
        jLabelTempo1 = new javax.swing.JLabel();
        barraDeMenu = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuCadastro = new javax.swing.JMenu();
        minFuncionario = new javax.swing.JMenuItem();
        miCliente = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        miGrupoProduto = new javax.swing.JMenuItem();
        miMulta = new javax.swing.JMenuItem();
        menuRalatorios = new javax.swing.JMenu();
        tempoMedioAluguelProduto = new javax.swing.JMenuItem();
        menuAluguel = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        miDevolucao = new javax.swing.JMenuItem();
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabelaCatalogMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaCatalogMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCatalog);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 426, 420));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        tableCarrinhoPedido.setEnabled(false);
        tableCarrinhoPedido.setShowVerticalLines(false);
        tableCarrinhoPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCarrinhoPedidoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableCarrinhoPedidoMouseReleased(evt);
            }
        });
        tableCarrinhoPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableCarrinhoPedidoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableCarrinhoPedidoKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tableCarrinhoPedido);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 350, 170));

        jLabel2.setText("Valor Total  R$: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        valorAPagar.setText("..");
        jPanel1.add(valorAPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 220, -1, -1));

        botaFecharPedido.setText("Fechar Pedido");
        botaFecharPedido.setEnabled(false);
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
        jPanel1.add(botaFecharPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, -1, -1));

        botaoCancelarPedido.setText("Cacelar Pedido");
        botaoCancelarPedido.setEnabled(false);
        botaoCancelarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarPedidoActionPerformed(evt);
            }
        });
        jPanel1.add(botaoCancelarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, -1));

        jInternalFrame1.setVisible(false);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 220, 0, 0));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, -1, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(comboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 195, 30));

        jLabel3.setText("Categoria");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        campoPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoPesquisaKeyPressed(evt);
            }
        });
        jPanel2.add(campoPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 22, 590, 30));

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, -1, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 880, 60));

        campoHoras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoHorasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoHorasKeyTyped(evt);
            }
        });
        getContentPane().add(campoHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 60, 30));

        jLabelQtde.setText("Qtde");
        getContentPane().add(jLabelQtde, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 50, -1));

        campoQuatidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoQuatidadeKeyPressed(evt);
            }
        });
        getContentPane().add(campoQuatidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 60, 30));

        jLabelTempo1.setText("Tempo/ Hr");
        getContentPane().add(jLabelTempo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 70, -1));

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

        miGrupoProduto.setText("Grupo Produto");
        miGrupoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miGrupoProdutoActionPerformed(evt);
            }
        });
        menuCadastro.add(miGrupoProduto);

        miMulta.setText("Multa");
        miMulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miMultaActionPerformed(evt);
            }
        });
        menuCadastro.add(miMulta);

        barraDeMenu.add(menuCadastro);

        menuRalatorios.setText("Relatórios");

        tempoMedioAluguelProduto.setText("Tempo médio de aluguel por produto");
        tempoMedioAluguelProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempoMedioAluguelProdutoActionPerformed(evt);
            }
        });
        menuRalatorios.add(tempoMedioAluguelProduto);

        barraDeMenu.add(menuRalatorios);

        menuAluguel.setText("Aluguel");

        jMenuItem2.setText("Pedidos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuAluguel.add(jMenuItem2);

        miDevolucao.setText("Devolucão");
        miDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDevolucaoActionPerformed(evt);
            }
        });
        menuAluguel.add(miDevolucao);

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
        this.menuCadastro.setVisible(false);
        this.menuRalatorios.setVisible(false);
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
        TelaPesquisaPedido telaPesquisaPedido = new TelaPesquisaPedido(thered);
        telaPesquisaPedido.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
   
    private void tabelaCatalogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCatalogMouseClicked
        
            pegarCarrinho(evt);
            reserva(this.produto,"");
            boolean valid=SingletonBiblioteca.produtoExistCarrinho(this.procarrinhoMemo, this.carrinho);
            
            if(valid==true){
                jLabelTempo1.setVisible(true);
                campoHoras.setVisible(true);
                campoHoras.requestFocus();
                botaFecharPedido.setEnabled(true);
                botaoCancelarPedido.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null, "Produto ja existe");
                return ;
            }
        
    }//GEN-LAST:event_tabelaCatalogMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        carregarTabela();
        perrencherTabelaCarrinho();
    }//GEN-LAST:event_formWindowGainedFocus

    private void botaFecharPedidoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaFecharPedidoMouseReleased
       if (!procarrinhoMemo.isEmpty()){
            IndentificacaoUser user = new IndentificacaoUser(this.procarrinhoMemo,thered);
            user.setVisible(true);
            carrinhoAtivo=false;
            botaFecharPedido.setEnabled(false);
       }else{
           JOptionPane.showMessageDialog(null, "Lista de carrinho está vázia.");
 
       }
    }//GEN-LAST:event_botaFecharPedidoMouseReleased

    private void botaFecharPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaFecharPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaFecharPedidoActionPerformed

    private void tableCarrinhoPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCarrinhoPedidoKeyPressed
       if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            qtde=(Integer) tableCarrinhoPedido.getModel().getValueAt(tableCarrinhoPedido.getSelectedRow(),2);
            reserva(this.produto,"U");
            realizaCalculos();
            tableCarrinhoPedido.repaint();
          
       }
    }//GEN-LAST:event_tableCarrinhoPedidoKeyPressed

    private void tableCarrinhoPedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCarrinhoPedidoKeyTyped
       
        qtde=(Integer) tableCarrinhoPedido.getModel().getValueAt(tableCarrinhoPedido.getSelectedRow(),2);
        
        if (qtde<=0){
          procarrinhoMemo.remove(this.carrinho);
        }
        reserva(this.produto,"U");
          
        realizaCalculos();
          
        tableCarrinhoPedido.repaint();
    }//GEN-LAST:event_tableCarrinhoPedidoKeyTyped

    private void tableCarrinhoPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCarrinhoPedidoMouseClicked
       
      
    }//GEN-LAST:event_tableCarrinhoPedidoMouseClicked

    private void tableCarrinhoPedidoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCarrinhoPedidoMouseReleased
            removerCarrinho(evt);
            botaFecharPedido.setEnabled(false);
            botaoCancelarPedido.setEnabled(false);
           
    }//GEN-LAST:event_tableCarrinhoPedidoMouseReleased

    private void botaoCancelarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarPedidoActionPerformed
       
            this.procarrinhoMemo.clear();
            botaFecharPedido.setEnabled(false);
            valorAPagar.setText("");
            perrencherTabelaCarrinho();
            botaoCancelarPedido.setEnabled(false);
            carrinhoAtivo=true;
            jLabelTempo1.setVisible(false);
            jLabelQtde.setVisible(false);
            campoHoras.setVisible(false);
            campoQuatidade.setVisible(false);
            SingletonBiblioteca.retornarValorParaStoque(qtde, produto);
      
       
    }//GEN-LAST:event_botaoCancelarPedidoActionPerformed

    private void miMultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miMultaActionPerformed
       TelaPesquisarMulta multa= new TelaPesquisarMulta();
       multa.setVisible(true);
              
    }//GEN-LAST:event_miMultaActionPerformed

    private void miDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDevolucaoActionPerformed
        TelaDevolucao devolucao = new TelaDevolucao();
        devolucao.setVisible(true);
    }//GEN-LAST:event_miDevolucaoActionPerformed

    private void campoHorasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoHorasKeyTyped
      // nao servio
    }//GEN-LAST:event_campoHorasKeyTyped

    private void campoHorasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoHorasKeyPressed
       
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            
            String t=campoHoras.getText();
                
                 if (t.equals("")){
                     JOptionPane.showMessageDialog(null," Valor da hora esta em vazio");
                     return;
                 }
                 if(Integer.parseInt(t)>12){
                    JOptionPane.showMessageDialog(null,"Excede o horario de funcionamento");
                    }else{
                         jLabelQtde.setVisible(true);
                         campoQuatidade.setVisible(true);
                         campoQuatidade.requestFocus();
                         tempo=Integer.parseInt(t);
                       
                     }   
                 
                   
        }   
    }//GEN-LAST:event_campoHorasKeyPressed

    private void campoQuatidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoQuatidadeKeyPressed
        
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
          
            String q= campoQuatidade.getText();
            
            if (q.equals("") ){
                 JOptionPane.showMessageDialog(null, "Quantidade tem que ser maior que zero.");
            
            }else if (Integer.parseInt(q)> produto.getSaldo()){
                    JOptionPane.showMessageDialog(null, "Valor solicitado maior que o Saldo.");
                    
                }else{
                                
                    qtde=Integer.parseInt(q);
             
                   // pegarCarrinho(evt);
                    adicionarCarrinho(reserva(this.produto,"N"));
                    perrencherTabelaCarrinho();
                    realizaCalculos();

                    jLabelQtde.setVisible(false);
                    campoHoras.setVisible(false);
                    jLabelTempo1.setVisible(false);
                    campoQuatidade.setVisible(false);
                    carrinhoAtivo=true; 
                   // qtde=carrinho.getQntde();
                   // tempoPedido tt= new tempoPedido();
                  //  tt.start();
            }
        }
    }//GEN-LAST:event_campoQuatidadeKeyPressed

    private void tabelaCatalogMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCatalogMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaCatalogMouseEntered

    private void tempoMedioAluguelProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempoMedioAluguelProdutoActionPerformed
        new TempoMedioAluguelProdutoTela().setVisible(true);
    }//GEN-LAST:event_tempoMedioAluguelProdutoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraDeMenu;
    private javax.swing.JButton botaFecharPedido;
    private javax.swing.JButton botaoCancelarPedido;
    private javax.swing.JTextField campoHoras;
    private javax.swing.JTextField campoPesquisa;
    private javax.swing.JTextField campoQuatidade;
    private javax.swing.JComboBox comboCategoria;
    private javax.swing.JButton jButton1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelQtde;
    private javax.swing.JLabel jLabelTempo1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu menuAluguel;
    private javax.swing.JMenu menuAutenticação;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuRalatorios;
    private javax.swing.JMenuItem miCliente;
    private javax.swing.JMenuItem miDevolucao;
    private javax.swing.JMenuItem miGrupoProduto;
    private javax.swing.JMenuItem miLogaut;
    private javax.swing.JMenuItem miLogin;
    private javax.swing.JMenuItem miMulta;
    private javax.swing.JMenuItem minFuncionario;
    private javax.swing.JPopupMenu popupMenuProduto;
    private javax.swing.JTable tabelaCatalog;
    private javax.swing.JTable tableCarrinhoPedido;
    private javax.swing.JMenuItem tempoMedioAluguelProduto;
    private javax.swing.JLabel valorAPagar;
    // End of variables declaration//GEN-END:variables

    private Carrinho reserva(Produto produto, String acao){
         
        if (acao.equals("N")){
           
           // Carrinho c = new Carrinho();
            ClienteDAO dao = new ClienteDAO();
            Cliente clidefault=dao.clienteFind(Long.parseLong("1"));
            this.carrinho.setCodReferncia(produto.getId());
            this.carrinho.setNomeProduto(produto.getNome());
            this.carrinho.setValorAluguel(produto.getPrecoAluguel());
            this.carrinho.setQntde(qtde);
            this.carrinho.setTemposolicitado(tempo);
            this.carrinho.setCliente(clidefault);
            
            SingletonBiblioteca.baixaSaldoParaEstoque(qtde, this.produto);
            sutotal= retornarValor.subtotalItens(this.carrinho.getQntde(), this.carrinho.getValorAluguel(),this.carrinho.getTemposolicitado());

            this.carrinho.setSubtotal(sutotal);
         
            return this.carrinho;
         }else
            if (acao.endsWith("U")){
               carrinho.setQntde(qtde);
               sutotal= retornarValor.subtotalItens(carrinho.getQntde(), carrinho.getValorAluguel(),carrinho.getTemposolicitado());
               carrinho.setSubtotal(sutotal);
               qtde=1;
               return this.carrinho;
         }else if  (acao.endsWith("")){
           
                Carrinho c = new Carrinho();
                ClienteDAO dao = new ClienteDAO();
                Cliente clidefault=dao.clienteFind(Long.parseLong("1"));
                c.setCodReferncia(produto.getId());
                c.setNomeProduto(produto.getNome());
                c.setValorAluguel(produto.getPrecoAluguel());
                c.setQntde(qtde);
                c.setTemposolicitado(tempo);
                c.setCliente(clidefault);
                sutotal= retornarValor.subtotalItens(c.getQntde(), c.getValorAluguel(),c.getTemposolicitado());

                c.setSubtotal(sutotal);
                
                this.carrinho=c;
              return this.carrinho;
         }
         return  null;
     }
    
    private void pegarCarrinho(MouseEvent evt) {
       
        int coluna = tabelaCatalog.columnAtPoint(evt.getPoint());
        if (coluna== 2){
            int linha = tabelaCatalog.rowAtPoint(evt.getPoint());
            if(linha >= 0){
            tabelaCatalog.setRowSelectionInterval(linha, linha);
            linha = tabelaCatalog.getSelectedRow();
            
            this.produto=produtos.get(linha);
             //adicionarCarrinho(reserva(this.produto,"N"));
             }
            
          
        }
     
    }
    
    private Carrinho removerCarrinho(MouseEvent evt) {
       
        int coluna = tableCarrinhoPedido.columnAtPoint(evt.getPoint());
        int l= tableCarrinhoPedido.rowAtPoint(evt.getPoint());
        Carrinho c=procarrinhoMemo.get(l);
        if (coluna== 5){
            int linha = tableCarrinhoPedido.rowAtPoint(evt.getPoint());
            if(linha >= 0){
            tableCarrinhoPedido.setRowSelectionInterval(linha, linha);
            linha = tableCarrinhoPedido.getSelectedRow();
            int i = JOptionPane.showConfirmDialog(null, "Excluir?","Atenção",JOptionPane.YES_NO_OPTION);
            if (i==JOptionPane.YES_OPTION){
                procarrinhoMemo.remove( this.carrinho=procarrinhoMemo.get(linha));
                SingletonBiblioteca.retornarValorParaStoque(carrinho.getQntde(), produto);
             }else
                JOptionPane.showMessageDialog(null, "Operação concelada..");
        }
            
          
        }
      
     return this.carrinho=c;
    }
    
    private void adicionarCarrinho(Carrinho carrinho){
       procarrinhoMemo.add(carrinho);
        
    }
    
    private void perrencherTabelaCarrinho(){
        ImageRederer r= new ImageRederer();
        
        tableCarrinhoPedido.setModel(new TabelaModeloCarrinhoPedido(this.procarrinhoMemo));
        
        tableCarrinhoPedido.getColumnModel().getColumn(5).setCellRenderer(r);
        
        
    }
    
    private void realizaCalculos(){
               
        valorAPagar.setText("");
        Double valorApagar=0.0;
        
        for (Carrinho p : procarrinhoMemo) {
            valorApagar=valorApagar+p.getSubtotal();
        }
         valorAPagar.setText(valorApagar+"");
         
    }
    
    private void prencheCombo(){
        GrupoProdutoDAO dao = new GrupoProdutoDAO();
        comboCategoria.removeAllItems();
        comboCategoria.addItem("Selecione categogia..");
        for (GrupoProduto p : dao.findAll()) {
            comboCategoria.addItem(p);
        }
    }
    
}
