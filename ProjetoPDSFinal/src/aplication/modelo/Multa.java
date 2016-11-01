/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author flavio
 */
@Entity
public class Multa implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "mulid")
    private Long id;
    
    @Column(name = "muldescricao")
    private String descricao;
    
    @Column(name = "mulvalor")
    private Double valor;
    
    @Column(name = "mulvalorpago")
    private Double valorPago;
    
    @ManyToOne
    @JoinColumn(name = "mulaluid" , nullable = false)
    private Aluguel aluguel;
    
    @ManyToOne
    @JoinColumn(name = "mulstmid" , nullable = false)
    private StatusMulta statusMulta;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }
    
    //Valida o parãmetro e,  se o valor for válido, seta e retorna verdadeiro 
    public boolean setValor(Double valor) {
        if (valor > 0){
            this.valor = valor;
            return true;
        }
        
        return false;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public StatusMulta getStatusMulta() {
        return statusMulta;
    }

    public void setStatusMulta(StatusMulta statusMulta) {
        this.statusMulta = statusMulta;
    }
}
