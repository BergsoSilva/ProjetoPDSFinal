package aplication.view.telaPrincipal;

import aplication.view.produto.*;
import aplication.modelo.GrupoProduto;
import aplication.modelo.Produto;
import aplication.renderizador.ImageRederer;
import aplication.renderizador.JTableRenderer;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class TabelaModeloCarrinhoPedido extends AbstractTableModel{
    
    private String colunas[] = {"Descrição"," Val unit"}; 
    private List<Produto> produtos;

 public TabelaModeloCarrinhoPedido(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    @Override
    public int getRowCount() {
        return produtos.size();
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
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out of bounds");
       }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);
        
         
        switch(columnIndex){
           
            case 0:
                return produto.getNome();
            case 1:
            
               return  produto.getPrecoAluguel();
            
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {            
        return colunas[column];       
    } 

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    
    
}
