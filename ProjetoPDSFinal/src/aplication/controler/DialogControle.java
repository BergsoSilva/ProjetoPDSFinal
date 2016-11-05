/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.controler;

import aplication.view.telaPrincipal.Dialog;
import javax.swing.JFrame;

/**
 *
 * @author unkwow
 */
public class DialogControle implements comand{
    
    private JFrame frame =new JFrame();

    public DialogControle(String frame) {
        switch(frame){
            case "dialog":
                this.frame =  new Dialog();
             break;
            default:
                System.out.println("Erro controller");
        }
       
    }
    
    

    @Override
    public void executar() {
        frame.setLocationRelativeTo(this.frame);
        frame.setVisible(true);
        
    }
    
}
