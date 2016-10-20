package aplication.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.swing.Icon;

/**
 *
 * @author Erick
 */
@Entity
public class Produto implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String nome;
    private double precoAluguel;
    private double saldo;
    private int qtde;
    private String imagem;
    private boolean ativo;
    @Transient
    private Icon imagemProduto;
    
    
    @ManyToOne
    private GrupoProduto grupoProduto;

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

    public double getPrecoAluguel() {
        return precoAluguel;
    }

    public void setPrecoAluguel(double precoAluguel) {
        this.precoAluguel = precoAluguel;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Icon getImagemProduto() {
        return imagemProduto;
    }

    public void setImagemProduto(Icon imagemProduto) {
        this.imagemProduto = imagemProduto;
    }
    
    
}
