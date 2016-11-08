/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.regraDeNegocio;

import aplication.modelo.Carrinho;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author unkwow
 */
public class retornarValor {
    private final double valor=0.0;
    static{
        
        
    }
    
    public static Double subtotalItens(int  qtdeItem, Double valorItem ){
      
        return qtdeItem*valorItem;
    }
    
    
}
