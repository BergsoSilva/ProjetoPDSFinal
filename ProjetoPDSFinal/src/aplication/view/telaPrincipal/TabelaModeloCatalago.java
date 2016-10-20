package aplication.view.telaPrincipal;

import aplication.view.produto.*;
import aplication.modelo.GrupoProduto;
import aplication.modelo.Produto;
import aplication.rererizardor.JTableRenderer;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class TabelaModeloCatalago extends AbstractTableModel{
    
    private String colunas[] = {"Código", "Descrição", "Preço Aluguel","Imagem"}; 
    private List<Produto> pro;

    public TabelaModeloCatalago() {
            pro=new ArrayList<>();
    }
    
    

    public TabelaModeloCatalago(List<Produto> produtos) {
        this.pro = new ArrayList<Produto>(produtos);
    }
    
    @Override
    public int getRowCount() {
        return pro.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
     @Override
    public Class<?> getColumnClass(int columnIndex) {
       
        switch(columnIndex){
           case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Image.class;
           
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out of bounds");
       }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = pro.get(rowIndex);
        
         
        switch(columnIndex){
            case 0:
                return produto.getId();
            case 1:
                return produto.getNome();
            case 2:
                return produto.getPrecoAluguel();
            case 3:
                 return produto.getImagemProduto();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {            
        return colunas[column];       
    }

   

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {};

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public Produto getProdutos(int indicelinha){
        return pro.get(indicelinha);
    }
    
    public void addProdutos(Produto produtos){
       this.pro.add(produtos);
       int ultimoIndice = getRowCount() - 1;
       fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    public void removeProduto(int indiceLinha){
        pro.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
    public void addListaProdutos(List<Produto> produto){
        int tamanhoAntigo = getRowCount();
        pro.addAll(produto);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() -1);
    }
    public void limpar(){
        pro.clear();
        fireTableDataChanged();
    }
    public boolean isEmpty(){
        return pro.isEmpty();
    }
    
    
    
    
}
