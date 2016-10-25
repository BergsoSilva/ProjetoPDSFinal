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
    
    public List<ItemAluguel> pesquisar(Long idStatus){
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        //System.out.println(cliente.getNome());
        manager.getTransaction().begin();
            Query query = manager.createQuery("select i from ItemAluguel i "
                    + "INNER JOIN FETCH i.status "
                    + "INNER JOIN FETCH i.produto "
                    + "INNER JOIN FETCH i.aluguel a "
                    + "INNER JOIN FETCH a.cliente "
                    + "WHERE i.status.id = :id");
            
            query.setParameter("id", idStatus);
            List<ItemAluguel> itemAluguels = query.getResultList();
        manager.getTransaction();
        manager.close();
        return itemAluguels;    
    }
    
    public void finalizaPedido(ItemAluguel itemAluguelFinalizado){
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        ItemAluguel itemAluguel = new ItemAluguel();
        
        manager.getTransaction().begin();
            itemAluguel = manager.find(ItemAluguel.class, itemAluguelFinalizado.getId());
            itemAluguel.setProduto(itemAluguelFinalizado.getProduto());
            itemAluguel.setStatus(itemAluguelFinalizado.getStatus());
        manager.getTransaction().commit();
        manager.close();
    }
}
