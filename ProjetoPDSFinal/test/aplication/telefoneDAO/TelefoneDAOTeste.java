/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.telefoneDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc
 */
public class TelefoneDAOTeste {
    
    public static void main(String[] args) {
        Date data = new Date(System.currentTimeMillis());  
        SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        System.out.print(formatarDate.format(data));
        System.out.println("\n\n");
        System.out.println("********************\n");
        
        Calendar d= new GregorianCalendar();
        System.out.println(Calendar.getInstance().getTime());
    }
   
}
