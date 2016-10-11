/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplication.Exceptions;

/**
 *Class reaproveitada " Projeto de Interface Grafica"
 * @author rogeriocarmine
 */
public class BDException extends Exception{
 
    public BDException(String message) {
        super(message);
    }
    // caso precise 
    public BDException(String message, Throwable cause) {
        super(message, cause);
    }

    public BDException(Throwable cause) {
        super(cause);
    }
    
}
