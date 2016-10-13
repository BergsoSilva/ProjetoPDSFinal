package aplication.view.funcionario;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import aplication.modelo.Funcionario;

public class TabelaModeloFuncionario extends AbstractTableModel{
    
    private String colunas[] = {"Código", "nome"}; 
    private List<Funcionario> funcionarios;

    public TabelaModeloFuncionario(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    @Override
    public int getRowCount() {
        return funcionarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcionario funcionario = funcionarios.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return funcionario.getId();
            case 1:
                return funcionario.getNome();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {            
        return colunas[column];       
    }
}
