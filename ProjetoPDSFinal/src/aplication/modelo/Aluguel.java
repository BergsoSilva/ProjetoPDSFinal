package aplication.modelo;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Erick
 */
@Entity
public class Aluguel implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    private Calendar dtAluguel;
    private Calendar dtDevolucao;
    
    @ManyToOne
    private FormPagamento formPagamento;
    
    @ManyToOne
    private Status status;
    
    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Funcionario funcionario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDtAluguel() {
        return dtAluguel;
    }

    public void setDtAluguel(Calendar dtAluguel) {
        this.dtAluguel = dtAluguel;
    }

    public Calendar getDtDevolucao() {
        return dtDevolucao;
    }

    public void setDtDevolucao(Calendar dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
    }

    public FormPagamento getFormPagamento() {
        return formPagamento;
    }

    public void setFormPagamento(FormPagamento formPagamento) {
        this.formPagamento = formPagamento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
