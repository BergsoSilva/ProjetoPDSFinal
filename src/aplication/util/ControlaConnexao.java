/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.activation.DataSource;


/**
 *
 * @author GRS
 */
public class ControlaConnexao {

 public static Connection getConexao ()throws SQLException{
        
        try{
         
        Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            System.err.println("Erro Driver Manager "+ex.getLocalizedMessage());
        }
      
        return DriverManager.getConnection("jdbc:mysql://localhost/projetopds","root","123");
    }
   
}
