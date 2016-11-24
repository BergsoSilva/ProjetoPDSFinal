/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.devolucao;

import aplication.modelo.Multa;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author flavio
 */
public class MultaTeste {
    Multa multa1;
    Multa multa2;
    
    public MultaTeste() {
        multa1 = new Multa();
        multa2 = new Multa();
    }
    
    @Before
    public void setUp() {
        //multa1.setValor(300.00);
        //multa2.setValor(200.00);
    }
    
    //Verifica se soma das multas é menor ou igual a zero
    @Ignore
    @Test
    public void totalDeveriaSerMenorIgualAZero(){
        double novoValorMulta1 = multa1.getValor() * (-1);
        double valorMulta2 = multa2.getValor();
        
        double total = novoValorMulta1 + valorMulta2;
        
        assertTrue(total <= 0);
    }
    
    //Verifica se soma das multas é maior que zero
    @Ignore
    @Test
    public void deveriaSerPositivo(){
        double total = multa1.getValor() + multa2.getValor();
        
        assertTrue(total > 0);
    }
    
    //Tenta converter String em Double para lançar exceção
    @Ignore
    @Test(expected = NumberFormatException.class)
    public void deveriaLancarNumberFormatException(){
        String str = "string";
        multa1.setValor(Double.parseDouble(str));
    }
    
    //Valida String antes de convertê-la em Double
    //Testa método setValor()
    @Test
    public void valorMultaNaoDeveriaSerNull(){
        String str = "s";
        try{
            multa1.setValor(Double.parseDouble(str));
        } catch(NumberFormatException nfe){
            System.out.println("Valor não é \"Double\": " + multa1.getValor());
        }
        
        assertNotNull(multa1.getValor());
    }
    
    //Verifica se o valor da multa tem duas casas decimais
    @Ignore
    @Test
    public void deveriaTerDuasCasasDecimais(){
        Double valor = 100.33456323;
        assertTrue(false);
    }
}
