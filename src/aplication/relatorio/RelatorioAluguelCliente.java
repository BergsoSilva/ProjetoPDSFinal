/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.relatorio;

/**
 *
 * @author pc
 */
public class RelatorioAluguelCliente {
    private String nome;
    private String cpf;
    private int id;
    private String descricao;

    public RelatorioAluguelCliente() {
    }

    public RelatorioAluguelCliente(String nome, String cpf, int id, String descricao) {
        this.nome = nome;
        this.cpf = cpf;
        this.id = id;
        this.descricao = descricao;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    

}
