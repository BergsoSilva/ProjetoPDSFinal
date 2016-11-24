package aplication.view.produto;

import aplication.modelo.GrupoProduto;
import aplication.modelo.Produto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaModeloProduto extends AbstractTableModel{
    
    private String colunas[] = {"Código", "Nome", "Preço Aluguel"}; 
    private List<Produto> produtos;

    public TabelaModeloProduto(List<Produto> produtos) {
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return produto.getId();
            case 1:
                return produto.getNome();
            case 2:
                return produto.getPrecoAluguel();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {            
        return colunas[column];       
    }
}
