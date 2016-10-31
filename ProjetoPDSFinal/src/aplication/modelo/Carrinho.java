/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.modelo;

/**
 *
 * @author unkwow
 */
public class Carrinho {
    private Long codReferncia=0l;
    private String nomeProduto;
    private Double valorAluguel;
    private Integer qntde;
    private Cliente cliente;
    private Double subtotal;
    

    public Carrinho() {
        this.codReferncia=codReferncia+1;
    }

    public Long getCodReferncia() {
        return codReferncia;
    }

    public void setCodReferncia() {
        this.codReferncia = codReferncia;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(Double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

  

    public Integer getQntde() {
        return qntde;
    }

    public void setQntde(Integer qntde) {
        this.qntde = qntde;
    }

    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    
    
    
    
}
