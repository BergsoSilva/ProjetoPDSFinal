package aplication.view.grupoproduto;

import aplication.modelo.GrupoProduto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaModeloGrupoProduto extends AbstractTableModel{
    
    private String colunas[] = {"Código", "Descrição"}; 
    private List<GrupoProduto> grupoProdutos;

    public TabelaModeloGrupoProduto(List<GrupoProduto> grupoProdutos) {
        this.grupoProdutos = grupoProdutos;
    }
    
    @Override
    public int getRowCount() {
        return grupoProdutos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GrupoProduto grupoProduto = grupoProdutos.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return grupoProduto.getId();
            case 1:
                return grupoProduto.getDescricao();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {            
        return colunas[column];       
    }
}
