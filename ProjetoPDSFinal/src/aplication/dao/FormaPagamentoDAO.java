package aplication.dao;

import aplication.modelo.FormPagamento;
import aplication.modelo.Produto;
import aplication.util.ConnectioinFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Erick
 */
public class FormaPagamentoDAO {
    
    public List<FormPagamento>pesquisar(FormPagamento formPagamento){
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        manager.getTransaction().begin();
            Query query = manager.createQuery("select c from FormPagamento c where c.descricao like :nomeParametro");
            query.setParameter("nomeParametro","%"+formPagamento.getDescricao()+"%");
            List<FormPagamento> formPagamentos = query.getResultList();
        manager.getTransaction();
        manager.close();
        
        return formPagamentos;    
    }
}
