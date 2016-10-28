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

public class TabelaModeloCatalago extends AbstractTableModel{
    
    private String colunas[] = {"Imagem","Descrição","Ação"}; 
    private List<Produto> produtos;

 public TabelaModeloCatalago(List<Produto> produtos) {
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
                 return Image.class;
            case 1:
                return String.class;
         
            case 2:
                return Image.class;
           
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out of bounds");
       }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);
        
         
        switch(columnIndex){
            case 0:
                {
                    ImageIcon icon = new ImageIcon(getClass().getResource("/"+produto.getImagem()));
                    return icon;
                 }
            case 1:
                return produto.getNome() +" \n Preço Alugel : "+ produto.getPrecoAluguel();
            case 2:
            
               ImageIcon icon = new ImageIcon(getClass().getResource("/img/add.jpg"));
               
               return icon;
            
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
