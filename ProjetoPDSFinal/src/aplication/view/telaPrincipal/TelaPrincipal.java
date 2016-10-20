/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.telaPrincipal;

import aplication.rererizardor.JTableRenderer;
import aplication.dao.ProdutoDAO;
import aplication.modelo.Produto;
import aplication.rererizardor.ColProduto;
import aplication.view.aluguel.TelaVerDetalhesAluguel;
import aplication.view.funcionario.TelaPesquisaFuncionario;
import aplication.view.produto.TelaVerDetalhesProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author pc
 */
public class TelaPrincipal extends javax.swing.JFrame {
    private Produto produto=new Produto();
    private List<Produto> produtos= new ArrayList<>();
    private DefaultTableModel modelo = new DefaultTableModel();
    private TabelaModeloCatalago tableModeoCatalogo= new TabelaModeloCatalago();
   
    public TelaPrincipal() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(this);
        pesquisar();
        carregarTabela();
        carregarMenuFlutuante();
        
       carregarTabela();
    }
    
    private void carregarTabela(){
        pesquisar();
        TableColumnModel columModel=tabelaCatalog.getColumnModel();
        JTableRenderer renderer = new JTableRenderer();
        
        tabelaCatalog.setModel(new TabelaModeloCatalago(produtos));
        tabelaCatalog.setAutoCreateRowSorter(true);
        tabelaCatalog.setRowHeight(40);
        tabelaCatalog.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabelaCatalog.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabelaCatalog.getColumnModel().getColumn(2).setPreferredWidth(10);
        tabelaCatalog.getColumnModel().getColumn(3).setPreferredWidth(20);
       
        try {
            File file = new File("");
            
            getModel().limpar();
        
          
            for (Produto p : produtos) {
                //addProduto(p.getId()+"", p.getNome(),p.getSaldo()+"",produtoImagen)
                String caminho = file.getAbsolutePath() + "/src/" + p.getImagem();
                Icon produtoImagen = new ImageIcon(caminho);
                columModel.getColumn(3).setCellRenderer(renderer);
                modelo=(DefaultTableModel) tabelaCatalog.getModel();
                modelo.addRow(new Object[]{
                   // new ImageIcon(caminho)
                    new ImageIcon(caminho)
                   });
            }
            
      
            
        } catch (Exception erro) {
        }
    }
    
    private TabelaModeloCatalago getModel() {
        if (tableModeoCatalogo == null) {
            tableModeoCatalogo = (TabelaModeloCatalago) tabelaCatalog.getModel();
        }
        return tableModeoCatalogo;
    }
    
    private Produto getProduto(String id, String descricao, String preco, Icon pro) {
        Produto colProduto = new  Produto();
        colProduto.setId(Long.parseLong(id));
        colProduto.setNome(descricao);
        colProduto.setPrecoAluguel(Double.parseDouble(preco));
        colProduto.setImagemProduto(pro);
        return colProduto;
    }
    
    private  void addProduto(String id, String descricao, String valor,Icon prod) {
        getModel().addProdutos(getProduto(id, descricao, valor,prod));
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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuProduto = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCatalog = new javax.swing.JTable();
        campoPesquisa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

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

        campoPesquisa.setText("Pesquisar produto");
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

        jMenu1.setText("File");

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Cadastro");

        jMenuItem2.setText("Funcinario");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       TelaPesquisaFuncionario funcionario =new TelaPesquisaFuncionario();
       funcionario.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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

    private void tabelaCatalogMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCatalogMouseReleased
        selecionarProduto(evt);
        realizarAcao(evt);
    }//GEN-LAST:event_tabelaCatalogMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoPesquisa;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu popupMenuProduto;
    private javax.swing.JTable tabelaCatalog;
    // End of variables declaration//GEN-END:variables
}
