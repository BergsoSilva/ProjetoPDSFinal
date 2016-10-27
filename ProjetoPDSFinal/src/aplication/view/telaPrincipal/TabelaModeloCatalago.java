package aplication.view.telaPrincipal;

import aplication.view.produto.*;
import aplication.modelo.GrupoProduto;
import aplication.modelo.Produto;
import aplication.renderizador.ImageRederer;
import aplication.renderizador.JTableRenderer;
import java.awt.Image;
import java.awt.Rectangle;
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
        
        JLabel j = new JLabel();
        j.setBounds(0, 0, 200, 150);
        Rectangle rectangle = j.getBounds();
        
        //ImageIcon icon = new ImageIcon(getClass().getResource("/img/add.jpg"));
        ImageIcon icon = new ImageIcon(getClass().getResource("/"+produto.getImagem()));
        Image image = icon.getImage().getScaledInstance(rectangle.width, rectangle.height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        
        switch(columnIndex){
            case 0:
                {
                    //ImageIcon icon2 = new ImageIcon(getClass().getResource("/"+produto.getImagem()));
                    return icon;
                 }
            case 1:
                return produto.getNome() +" \n Preço Alugel : "+ produto.getPrecoAluguel();
            case 2:       
               
                ImageIcon icon2 = new ImageIcon(getClass().getResource("/img/add.jpg"));
               return icon2;
            
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
