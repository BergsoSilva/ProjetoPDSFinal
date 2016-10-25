package aplication.view.pedido;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import aplication.modelo.ItemAluguel;

public class TabelaModeloPedido extends AbstractTableModel{
    
    private String colunas[] = {"Código", "Produto", "Cliente", "Quantidade"}; 
    private List<ItemAluguel> itensAlugueis;

    public TabelaModeloPedido(List<ItemAluguel> itensAlugueis) {
        this.itensAlugueis = itensAlugueis;
    }
    
    @Override
    public int getRowCount() {
        return itensAlugueis.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemAluguel itemAluguel = itensAlugueis.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return itemAluguel.getProduto().getId();
            case 1:
                return itemAluguel.getProduto().getNome();
            case 2:
                return itemAluguel.getAluguel().getCliente().getNome();
            case 3:
                return itemAluguel.getQuantidade();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {            
        return colunas[column];       
    }
}
