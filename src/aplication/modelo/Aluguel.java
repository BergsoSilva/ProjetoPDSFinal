package aplication.modelo;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Erick
 */
@Entity
public class Aluguel implements Serializable{
    
    public static final Long PEDIDO = 1L;
    public static final Long ALUGADO = 2L;
    public static final Long FINALIZADO = 3L;
    public static final Long FINALIZADO_COM_PENDENCIA = 4L;
    
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "aludtaaluguel")
    private Calendar dtAluguel;
    @Temporal(TemporalType.DATE)
    @Column(name = "aludtadedevolucao" , nullable = true)
    private Calendar dtDevolucao;
    @ManyToOne
    private Status status;
    @ManyToOne
    private FormPagamento formPagamento;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Funcionario funcionario;
    @Column(name = "aluqtdeitem")
    private Integer quantidade;
    @Column(name = "alutempo")
    private Integer tempo;
    @ManyToOne
    private Produto produto;
    
    @Transient
    private Boolean campoCheck;

    public Boolean getCampoCheck() {
        return campoCheck;
    }

    public void setCampoCheck(Boolean campoCheck) {
        this.campoCheck = campoCheck;
    }
    
    public Aluguel() {
    }

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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
        
}
