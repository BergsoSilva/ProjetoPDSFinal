/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.devolucao;

import aplication.modelo.Aluguel;
import aplication.modelo.Multa;
import aplication.modelo.StatusMulta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author flavio
 */
public class MultaDAOTeste {
    
    Multa multa1;
    Multa multa2;
    Aluguel aluguel;
    StatusMulta status;
    
    public MultaDAOTeste() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        multa1 = new Multa();
        multa2 = new Multa();
        
        multa1.setDescricao("Descrição multa 1");
        multa1.setValor(300.00);
        multa1.setAluguel(aluguel);
        multa1.setStatusMulta(status);
        
        multa2.setDescricao("Descrição multa 1");
        multa2.setValor(300.00);
        multa2.setAluguel(aluguel);
        multa2.setStatusMulta(status);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void deveriaRetornarUmaMulta(){
        assertEquals("str", "str");
    }
}
