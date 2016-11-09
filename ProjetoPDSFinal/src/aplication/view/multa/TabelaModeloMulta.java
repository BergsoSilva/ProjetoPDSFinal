/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.multa;

import aplication.modelo.Multa;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author flavio
 */
public class TabelaModeloMulta extends AbstractTableModel{
    
    List<Multa> multas;
    
    public TabelaModeloMulta(List<Multa> multas){
        this.multas = multas;
    }
    
    @Override
    public int getRowCount() {
        return multas.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Multa multa = multas.get(linha);
        
        switch (coluna){
            case 0: return multa.getId();
            case 1: return multa.getDescricao();
            case 2: return multa.getValor();
            case 3: return multa.getStatusMulta().getDescricao();
            case 4: return multa.getValorPago();
        }
        return null;
    }
    
    public String getColumnName(int coluna){
         switch (coluna){
            case 0: return "Id";
            case 1: return "Descrição";
            case 2: return "Valor";
            case 3: return "Status";
            case 4: return "Valor Pago";
        }
        return null;
    }
    
}
