package aplication.view.telaPrincipal;

import aplication.Exceptions.BDException;
import aplication.modelo.Produto;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.AbstractTableModel;

public class TabelaModeloCatalago extends AbstractTableModel{
    
    private final  String colunas[] = {"Imagem","Descrição","Ação"}; 
    private final List<Produto> produtos;

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
                return JLabel.class;
           case 2:
                return Image.class;
           
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out of bounds");
       }
    }
    
    public List<JLabel> label (String d1, String d2 , String d3){
        
       List<JLabel> ls = new ArrayList<>();
        JLabel myLabel0 = new JLabel(d1);
        JLabel myLabel1 = new JLabel(d2);
        JLabel myLabel2 = new JLabel(d3);
        myLabel0.setVisible(true);
        myLabel1.setVisible(true);
        myLabel2.setVisible(true);
        
        ls.add(myLabel0);
        ls.add(myLabel1); 
        ls.add(myLabel2);
      
        return ls;        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto =this.produtos.get(rowIndex);
        
         
        switch(columnIndex){
            case 0: 
                    return retornaIcone(200,200, produto.getImagem());
            case 1:
                //return produto.getNome() +" \n Preço Alugel : "+ produto.getPrecoAluguel();
                for (JLabel label : label( produto.getNome(),produto.getPrecoAluguel()+"",produto.getSaldo()+"")) {
                    return label.toString();
                }
              
            case 2:
            {
               String c="img/add.png";
               
               return retornaIcone(24,24, c);
            }
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
   
    
}
