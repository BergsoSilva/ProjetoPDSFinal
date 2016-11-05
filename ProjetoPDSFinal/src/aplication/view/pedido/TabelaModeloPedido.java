package aplication.view.pedido;

import aplication.modelo.Aluguel;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TabelaModeloPedido extends AbstractTableModel{
    
    private String colunas[] = {"Código", "Produto", "Cliente", "Quantidade"}; 
    private List<Aluguel> alugueis;

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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aluguel aluguel = alugueis.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return aluguel.getProduto().getId();
            case 1:
                return aluguel.getProduto().getNome();
            case 2:
                return aluguel.getCliente().getNome();
            case 3:
                return aluguel.getQuantidade();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {            
        return colunas[column];       
    }
}
