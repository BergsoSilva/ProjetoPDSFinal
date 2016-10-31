package aplication.view.telaPrincipal;

import aplication.modelo.Carrinho;
import aplication.renderizador.EditarCelalrJTable;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;


public class TabelaModeloCarrinhoPedido extends AbstractTableModel{
    
    private final String colunas[] = {"Descrição"," Val unit", "Qtde","subTotal","X"}; 
    private final List<Carrinho> produtos;

 public TabelaModeloCarrinhoPedido(List<Carrinho> produtos) {
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
                return Double.class;
            case 2:
                return String.class;
            case 3: 
                 return Double.class;
            case 4:
                 return Image.class;
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out of bounds");
       }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Carrinho produto = produtos.get(rowIndex);
        
         
        switch(columnIndex){
           
            case 0:
                return produto.getNomeProduto();
            case 1:
                 return  produto.getValorAluguel();
            case 2:
                return produto.getQntde();
            case 3:
                return  produto.getSubtotal();
            case 4:
            {
               
                String c= "img/delete.jpg";
                return  retornaIcone(14, 14, c);
            }
             
            
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {            
        return colunas[column];       
    } 

       
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Carrinho carrinho = (Carrinho)produtos.get(rowIndex);

    if (columnIndex == 2) 
        carrinho.setQntde(Integer.parseInt(aValue.toString()));
    if (columnIndex == 3) 
        carrinho.setSubtotal(Double.parseDouble(aValue.toString()));
   
    }
    
    private Icon  retornaIcone(int x, int y, String caminho){
     
        JLabel label = new JLabel();
        label.setBounds(0, 0, x, y);
       
        Rectangle rectangle =label.getBounds();
        String pathImagem = caminho(caminho);
        ImageIcon icon = new ImageIcon(getClass().getResource(pathImagem)); 
        Image image = icon.getImage().getScaledInstance(rectangle.width, rectangle.height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        return  icon;
                
        
    }
    
    private String caminho(String path){
        
        try{
             File file = new File("");
             String caminho = file.getPath() + "/" + path;
             return caminho; 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
        return  null;
    }

    // aqui defino a celula que ficará editavel
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
       switch(columnIndex){
           case 0:
               return false;
           case 1:
               return  false;
           case 2:// coluna editavel
               return true;
           case 3: 
               return false;
           case 4:
               return false;
           default:
               return false;
               
       }
    }
    
    
    
    
    
}
