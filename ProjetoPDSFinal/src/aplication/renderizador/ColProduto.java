/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.renderizador;

import javax.swing.Icon;

/**
 *
 * @author unkwow
 */
public class ColProduto {
    private String vId;
    private String vDescricao;
    private String vValorAluguel;
    private Icon vProduto;

    public String getvId() {
        return vId;
    }

    public void setvId(String vId) {
        this.vId = vId;
    }

    public String getvDescricao() {
        return vDescricao;
    }

    public void setvDescricao(String vDescricao) {
        this.vDescricao = vDescricao;
    }

    public Icon getvProduto() {
        return vProduto;
    }

    public void setvProduto(Icon vProduto) {
        this.vProduto = vProduto;
    }

    public String getvValorAluguel() {
        return vValorAluguel;
    }

    public void setvValorAluguel(String vValorAluguel) {
        this.vValorAluguel = vValorAluguel;
    }
    
    
    
    
}
