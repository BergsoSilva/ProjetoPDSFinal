/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.AluguelTeste;

import aplication.controlle.AluguelController;

/**
 *
 * @author pc
 */
public class AluguelRelatorioTeste {
    
    public static void main(String[] args) {
         AluguelController con = new AluguelController();
     //   con.imprimir();
        con.gerarPorCodigo(1);
    }
   
    
    
}
