package aplication.dao;

import aplication.modelo.Aluguel;
import aplication.modelo.Cliente;
import aplication.modelo.ItemAluguel;
import aplication.util.ConnectioinFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Erick
 */
public class PedidoDAO {
    
    public List<Aluguel> pesquisar(Aluguel aluguel){
        
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        System.out.println(aluguel.getCliente().getNome());
        manager.getTransaction().begin();
            Query query = manager.createQuery(" select a from Aluguel a "
                    + "INNER JOIN  a.status  "
                    + "INNER JOIN  a.produto  "
                    + "INNER JOIN  a.cliente  "
                    + " WHERE a.status.id =?1 AND a.cliente.cpf like ?2");
            
           query.setParameter(1, aluguel.getStatus().getId());
           query.setParameter(2, "%" + aluguel.getCliente().getCpf() + "%");
            
        List<Aluguel> itemAluguels = query.getResultList();
        manager.getTransaction();
        manager.close();
        return itemAluguels;    
    }
    
    public void finalizaPedido(Aluguel aluguel){
        
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        Aluguel alu = new Aluguel();
        
        manager.getTransaction().begin();
            alu = manager.find(Aluguel.class, aluguel.getId());
            alu.setStatus(aluguel.getStatus());
        manager.getTransaction().commit();
        manager.close();
    }
    
    public Aluguel inserir(Aluguel aluguel) throws Exception {
      // Inserido a classe de util
           
           EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
            manager.getTransaction().begin();
            manager.persist(aluguel);
            manager.getTransaction().commit();
            manager.close();
            
            return aluguel;
    } 
    
      public List<Aluguel> pesquisarRelatorio(Long cliid){
        
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        manager.getTransaction().begin();
        
            Query query = manager.createQuery(" select a from Aluguel a "
                                                                        + " INNER JOIN fetch a.status s"
                                                                        + " INNER JOIN fetch a.cliente  c  "
                                                                        + "  where c.id=?1");

            
       query.setParameter(1, cliid);
            
        List<Aluguel> itemAluguels = query.getResultList();
        manager.getTransaction();
        manager.close();
        return itemAluguels;    
    }
}
