/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.testesUnitario;

import aplication.dao.FuncionarioDAO;
import aplication.modelo.Funcionario;
import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author unkwow
 */
public class FuncionarioDAOTeste {
    
    private FuncionarioDAO dao = new FuncionarioDAO();
       
    public FuncionarioDAOTeste() {
    }
    
     @Test
     public void funcinario (){
                 
         assertEquals(dao.consultarPorId(Long.parseLong("1")).getNome(), "Bergson");
         
         System.out.println("teste realizado com suceso");
     }
     
}
