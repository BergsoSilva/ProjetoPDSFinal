package aplication.dao;

import aplication.modelo.GrupoProduto;
import aplication.util.ConnectioinFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Erick
 */
public class GrupoProdutoDAO {
    
    public void inserir(GrupoProduto grupoProduto){
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        manager.getTransaction().begin();
        manager.persist(grupoProduto);
        manager.getTransaction().commit();
        manager.close();
    }
    
    public void alterar(GrupoProduto grupoProdutoNovo){        
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();

        GrupoProduto grupoProduto = new GrupoProduto();
                
        manager.getTransaction().begin();
            grupoProduto = manager.find(GrupoProduto.class, grupoProdutoNovo.getId());
            grupoProduto.setDescricao(grupoProdutoNovo.getDescricao());
        manager.getTransaction().commit();
        manager.close();
    }
    
    public List<GrupoProduto> pesquisar(GrupoProduto grupoProduto){
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        manager.getTransaction().begin();
            Query query = manager.createQuery("select c from GrupoProduto c where c.descricao like :descricao");
            query.setParameter("descricao","%"+grupoProduto.getDescricao()+"%");
            List<GrupoProduto> grupoProdutos = query.getResultList();
        manager.getTransaction();
        manager.close();
        
        return grupoProdutos;    
    }
    
    public void deletar(Long id){
        EntityManager manager = ConnectioinFactory.getEntityManagerFactory();
        
        GrupoProduto grupoProdutoNova = new GrupoProduto();
        
        manager.getTransaction().begin();
            grupoProdutoNova = manager.find(GrupoProduto.class, id);
            manager.remove(grupoProdutoNova);
            //JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        manager.getTransaction().commit();
        manager.close();
    }
}
