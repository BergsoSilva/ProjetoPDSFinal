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
//@Entity
public class ItemAluguel implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    private int quantidade;
    
    private Integer tempo;
    
    private Calendar devolucao;
    
    @ManyToOne
    private Produto produto;
    
    @ManyToOne
    private Aluguel aluguel;
    
    @ManyToOne
    private Status status;

    
}
