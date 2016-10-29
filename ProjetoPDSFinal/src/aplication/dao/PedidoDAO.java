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
    
    public List<ItemAluguel> pesquisar(ItemAluguel itemAluguel){
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        System.out.println(itemAluguel.getAluguel().getCliente().getNome());
        manager.getTransaction().begin();
            Query query = manager.createQuery("select i from ItemAluguel i "
                    + "INNER JOIN FETCH i.status "
                    + "INNER JOIN FETCH i.produto "
                    + "INNER JOIN FETCH i.aluguel a "
                    + "INNER JOIN FETCH a.cliente "
                    + "WHERE i.status.id = ?1 AND i.aluguel.cliente.nome like ?2");
            
            query.setParameter(1, itemAluguel.getStatus().getId());
            query.setParameter(2, "%"+itemAluguel.getAluguel().getCliente().getNome()+"%");
            
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
            itemAluguel.setStatus(itemAluguelFinalizado.getStatus());
        manager.getTransaction().commit();
        manager.close();
    }
}
