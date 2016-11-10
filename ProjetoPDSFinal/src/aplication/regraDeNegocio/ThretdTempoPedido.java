/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.regraDeNegocio;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class ThretdTempoPedido extends Thread{
    private final int contador=20;
    private int valor=0;

    static{
   
    }
    
    @Override
    public void run() {
        
        for (int i = contador; i>=0; i--) {
            try {
                Thread.sleep(1000);
                valor++;
            } catch (InterruptedException ex) {
                Logger.getLogger(ThretdTempoPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
                 System.out.println(" Time"+i +"Nome"+Thread.currentThread().getName());
                
                                      
                  
           
        }
        
        
    }
    
    public boolean resetarCarrinho( int val){
        boolean validar=true;
        
        if (val<contador){
            validar=false;
        }
        
        return   validar;
    }
    
    
    
}
