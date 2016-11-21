package aplication.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Erick
 */
@Entity
public class FormPagamento implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String descricao;
    private int qtdeParcelas;

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

    public int getQtdeParcelas() {
        return qtdeParcelas;
    }

    public void setQtdeParcelas(int qtdeParcelas) {
        this.qtdeParcelas = qtdeParcelas;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    
}
