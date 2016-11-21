/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.regraDeNegocio;

import aplication.modelo.Produto;
import static aplication.view.telaPrincipal.TelaPrincipal.qtde;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class ThretdTempoPedido extends Thread{
    private int d;
    private Produto produto;

    public ThretdTempoPedido(int d, Produto produto) {
        this.d = d;
        this.produto = produto;
    }
    
      @Override
        public void run() {
            for (int i=10; i>=0; i--) {
                try {
                    Thread.sleep(4000);
                    if (i==0){
                        
                        SingletonBiblioteca.retornarValorParaStoque(qtde, produto);
                        
                    }
                } catch (InterruptedException ex) {
                        Logger.getLogger(ThretdTempoPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
                         System.out.println(" Time"+i +"Nome"+Thread.currentThread().getName());
           }
        }

    
}
