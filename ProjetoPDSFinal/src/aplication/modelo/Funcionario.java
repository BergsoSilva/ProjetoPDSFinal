/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.modelo;

import aplication.view.funcionario.Telefone;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author unkwow
 */
@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funid")
    private Long id;
    @Column(name = "funome")
    private String nome;
    @Column(name = "funsalario")
    private Double salario;
    @Column(name = "fundtaadimissao")
    @Temporal(TemporalType.DATE)
    private Calendar dataadimissao= Calendar.getInstance();
    @Temporal(TemporalType.DATE)
    @Column(name = "fundatademissao")
    private Calendar datademissao= Calendar.getInstance();
    @Column(name="funlogin")
    private String login;
    @Column(name = "funsenha")
    private String senha; 
    @OneToMany
    private Collection<Telefone> telefones;
    @ManyToOne
    @JoinColumn(name = "funfunid" , nullable = false)
    private Funcao funcao;
    @ManyToOne
    @JoinColumn(name = "funestid" , nullable = false)
    private EstadoCivil estadocivil;
    @ManyToOne
    @JoinColumn(name = "funcidid" , nullable = false)
    private Cidade  cidade;

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

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Calendar getDataadimissao() {
        return dataadimissao;
    }

    public void setDataadimissao(Calendar dataadimissao) {
        this.dataadimissao = dataadimissao;
    }

    public Calendar getDatademissao() {
        return datademissao;
    }

    public void setDatademissao(Calendar datademissao) {
        this.datademissao = datademissao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Collection<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Collection<Telefone> telefones) {
        this.telefones = telefones;
    }
    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public EstadoCivil getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(EstadoCivil estadocivil) {
        this.estadocivil = estadocivil;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    

   
    
    
    
}
