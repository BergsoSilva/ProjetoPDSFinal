/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.regraDeNegocio;

import aplication.modelo.Carrinho;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author unkwow
 */
public class OperationsCarrinho {
    
    static {
        
    }
    public static Carrinho getObject(MouseEvent evt,JTable table,List<Carrinho> objetcs){
        Carrinho objetc; 
        int linha = table.rowAtPoint(evt.getPoint());
        int coluna = table.columnAtPoint(evt.getPoint());
        try{
            if(linha >= 0){
                table.setRowSelectionInterval(linha, linha);
                linha = table.getSelectedRow();
               
                return  objetc = objetcs.get(linha);            
            }
        }catch(Exception e){
            e.getLocalizedMessage();
        }
       return null;
    }
    
   
}
