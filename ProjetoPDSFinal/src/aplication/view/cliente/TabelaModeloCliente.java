package aplication.view.cliente;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import aplication.modelo.Cliente;

public class TabelaModeloCliente extends AbstractTableModel{
    
    private String colunas[] = {"Código", "nome"}; 
    private List<Cliente> clientes;

    public TabelaModeloCliente(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return cliente.getId();
            case 1:
                return cliente.getNome();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {            
        return colunas[column];       
    }
}
