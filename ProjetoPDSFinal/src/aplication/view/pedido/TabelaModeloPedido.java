package aplication.view.pedido;

import aplication.modelo.Aluguel;
import java.awt.Checkbox;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import org.hibernate.metamodel.source.annotations.JPADotNames;


public class TabelaModeloPedido extends AbstractTableModel{
    
    private String colunas[] = {"#","Código Aluguel", "Produto", "Cliente", "Quantidade"}; 
    private List<Aluguel> alugueis;
    private Boolean selecionado = false;

    public TabelaModeloPedido(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }
    
    @Override
    public int getRowCount() {
        return alugueis.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
       
        switch(columnIndex){
            case 0:
                return Boolean.class;
            case 1:
                return Long.class;
            case 2:
                 return String.class;
            case 3:
                 return String.class;
            case 4:
                return String.class;

             default:
                throw new IndexOutOfBoundsException("ColumnIndex out of bounds");
       }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 0);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aluguel aluguel = alugueis.get(rowIndex);
        //JCheckBox jCheckBox = new JCheckBox();
                
        switch(columnIndex){
            case 0:
                return aluguel.getCampoCheck();
            case 1:
                return aluguel.getId();
            case 2:
                return aluguel.getProduto().getNome();
            case 3:
                return aluguel.getCliente().getNome();
            case 4:
                return aluguel.getQuantidade();
        }
        return null;
    }
   
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Aluguel aluguel = alugueis.get(rowIndex);
        if (columnIndex == 0){
            aluguel.setCampoCheck((Boolean) aValue);
        }
    }
    
    @Override
    public String getColumnName(int column) {            
        return colunas[column];       
    }
}
