/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.modelo;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Erick
 */
@Entity
public class Cliente implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    
    private String numHabilitacao;
    private char sexo;
    private Calendar dataDasc;
    private String cpf;
    private String clifone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumHabilitacao() {
        return numHabilitacao;
    }

    public void setNumHabilitacao(String numHabilitacao) {
        this.numHabilitacao = numHabilitacao;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Calendar getDataDasc() {
        return dataDasc;
    }

    public void setDataDasc(Calendar dataDasc) {
        this.dataDasc = dataDasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getClifone() {
        return clifone;
    }

    public void setClifone(String clifone) {
        this.clifone = clifone;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
