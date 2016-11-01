/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.view.devolucao;

import aplication.modelo.Multa;
import aplication.modelo.StatusMulta;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author flavio
 */
public class DevolucaoTeste {
    StatusMulta statusMultaPago;
    StatusMulta statusMultaNaoPago;
    StatusMulta statusMultaParcPago;
            
    Multa multa1;
    Multa multa2;
    Multa multa3;
    
    Double valorMulta1;
    Double valorMulta2;
    Double valorMulta3;
    
    Double troco;
    Double debito; //Valor que o cliente fica devendo
    Double valorPagoParcial; //Valor que o cliente pagou parcialmente de uma multa
    
    public DevolucaoTeste() {
        multa1 = new Multa();
        multa2 = new Multa();
        multa3 = new Multa();
        
        statusMultaPago = new StatusMulta();
        statusMultaNaoPago = new StatusMulta();
        statusMultaParcPago = new StatusMulta();
    }
    
    @Before
    public void setUp() {
        multa1.setValor(100.00);
        multa2.setValor(200.00);
        multa3.setValor(300.00);
        
        valorMulta1 = multa1.getValor();
        valorMulta2 = multa2.getValor();
        valorMulta3 = multa3.getValor();
        
        statusMultaPago.setDescricao("Pago");
        statusMultaNaoPago.setDescricao("Não Pago");
        statusMultaParcPago.setDescricao("Parcialmente Pago");
    }
    
    //Testa um cálculo de total de multas
    @Ignore
    @Test
    public void deveriaSomarMultas(){
        Double valorTotal;
        valorTotal = valorMulta1 + valorMulta2 + valorMulta3;
        
        assertEquals(600.00, valorTotal, 0.0);
    }
    
    //Testa os valores das multas
    @Test
    public void naoDeveriaHaverValorNulo(){
        assertNotNull(valorMulta1);
        assertNotNull(valorMulta2);
        assertNotNull(valorMulta3);
    }
    
    //Testa o valor fornecido pelo cliente
    @Test
    public void valorEntregueDeveriaSerMaiorQueZero(){
        Double valorPagoTotal = 1.1;
        assertTrue(valorPagoTotal > 0);
    }
    
    //Testa o valor do troco
    @Test
    public void trocoDeveriaSerMaiorIgualAZero(){
        pagarMultas();
        assertTrue(troco >= 0);
    }
    
    //Testa o valor do débito igual a zero
    
    @Test
    @Ignore
    public void debitoDeveriaSerIgualAZero(){
        pagarMultas();
        assertTrue(debito == 0);
    }
    
    //Testa o valor do débito maior que zero
    @Ignore
    @Test
    public void debitoDeveriaSerMaiorQueZero(){
        pagarMultas();
        assertTrue(debito > 0);
    }
    
    //Testa se o valor do débito é igual a um determinado valor
    @Test
    public void debitoDeveriaSerIgualAX(){
        pagarMultas();
        assertTrue(debito == 350);
    }
    
    //Testa o status da multa "Pago"
    @Test
    public void StatusDeveriaSerPago(){
        pagarMultas();
        assertEquals("Pago", multa2.getStatusMulta().getDescricao());
    }
    
    //Testa o status da multa "Parcialmente Pago"
    @Test
    public void StatusDeveriaSerParcPago(){
        pagarMultas();
        assertEquals("Parcialmente Pago", multa3.getStatusMulta().getDescricao());
    }
    
    //Testa o status da multa "Não Pago"
    
    @Test
    public void StatusDeveriaSerNaoPago(){
        pagarMultas();
        assertEquals("Não Pago", multa3.getStatusMulta().getDescricao());
    }
    
    @Test
    public void valorPagoParcialDeveriaSerX(){
        assertTrue(multa3.getValorPago() == 100);
    }
            
    //calcula quais multas serão pagas, o troco e
    //quanto o cliente fica devendo e seta os status
    void pagarMultas(){
        Double valorPagoTotal = 350.00;
        Double totalMultas = valorMulta1 + valorMulta2 + valorMulta3;
        
        if(valorPagoTotal >= totalMultas){
            troco = valorPagoTotal - totalMultas;
            debito = 0.0;
            multa1.setStatusMulta(statusMultaPago);
            multa2.setStatusMulta(statusMultaPago);
            multa3.setStatusMulta(statusMultaPago);
        } else {
            debito = totalMultas - valorPagoTotal;
            troco = 0.00;
            
            if( valorPagoTotal < valorMulta1 ){
                multa1.setValorPago(valorPagoTotal);
                multa1.setStatusMulta(statusMultaParcPago);
                multa2.setStatusMulta(statusMultaNaoPago);
                multa3.setStatusMulta(statusMultaNaoPago);
            }else {
                multa1.setStatusMulta(statusMultaPago);
                valorPagoTotal = valorPagoTotal - valorMulta1;
            
                if (valorPagoTotal < valorMulta2){
                    if(valorPagoTotal == 0){
                        multa2.setStatusMulta(statusMultaNaoPago);
                    }else{
                        multa2.setStatusMulta(statusMultaParcPago);
                        multa2.setValorPago(valorPagoTotal);
                    }
                    multa3.setStatusMulta(statusMultaNaoPago);
                } else {
                    multa2.setStatusMulta(statusMultaPago);
                    valorPagoTotal = valorPagoTotal - valorMulta2;
                    
                    if (valorPagoTotal < valorMulta3){
                        if(valorPagoTotal == 0){
                            multa3.setStatusMulta(statusMultaNaoPago);
                        }else{
                            multa3.setStatusMulta(statusMultaParcPago);
                            multa3.setValorPago(valorPagoTotal);
                        }
                        System.out.println("valor pago: " + valorPagoTotal);
                    }
                }
            }
        }
        
        System.out.println("Troco: " + troco);
        System.out.println("Débito: " + debito);
        
    }
}
