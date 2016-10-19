package aplication.dao;

import aplication.Exceptions.BDException;
import aplication.Exceptions.BDMensagensPadrao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import aplication.modelo.Cliente;
import aplication.util.ConnectioinFactory;
import java.sql.SQLException;


/**
 *
 * @author Erick
 */
public class ClienteDAO {
    
    public void inserir(Cliente cliente){
      // Inserido a classe de util
      
           EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
            manager.getTransaction().begin();
            manager.persist(cliente);
            manager.getTransaction().commit();
            manager.close(); 
        
    }
    
    public void alterar(Cliente clienteNovo){        
       // EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjetoPDSFinalPU");
       // EntityManager manager = factory.createEntityManager();
       EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        Cliente cliente = new Cliente();
        
        manager.getTransaction().begin();
            cliente = manager.find(Cliente.class, clienteNovo.getId());
            cliente.setNome(clienteNovo.getNome());
            //cliente.setClifone(clienteNovo.getClifone());
            cliente.setNumHabilitacao(clienteNovo.getNumHabilitacao());
            cliente.setSexo(clienteNovo.getSexo());
            cliente.setDataDasc(clienteNovo.getDataDasc());
        manager.getTransaction().commit();
        manager.close();
    }
    
    public List<Cliente> pesquisar(Cliente cliente){
     //   EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjetoPDSFinalPU");
      //  EntityManager manager = factory.createEntityManager();
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        System.out.println(cliente.getNome());
        manager.getTransaction().begin();
            Query query = manager.createQuery("select c from Cliente c where c.nome like :nomeRecebido");
            query.setParameter("nomeRecebido","%"+cliente.getNome()+"%");
            List<Cliente> clientes = query.getResultList();
        manager.getTransaction();
        manager.close();
        return clientes;    
    }
    
    // falta o delete
}
