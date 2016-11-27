/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.relatorio;

import aplication.modelo.Aluguel;
import aplication.modelo.Cliente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pc
 */
public class AluguelClienteTableModel extends AbstractTableModel{
    
    private String colunas[] = {"Nome", "CPG","CodigoAluguel","Status"}; 
    private List<Aluguel> r;

    public AluguelClienteTableModel(List<Aluguel> r) {
        this.r = r;
    }
    
    @Override
    public int getRowCount() {
        return r.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aluguel aluguel = r.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return aluguel.getCliente().getNome();
            case 1:
                return aluguel.getCliente().getCpf();
            case 3:
                return aluguel.getStatus().getDescricao();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {            
        return colunas[column];       
    }
    
}
