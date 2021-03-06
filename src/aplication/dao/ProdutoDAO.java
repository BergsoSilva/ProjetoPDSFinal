package aplication.dao;

import aplication.modelo.Cliente;
import aplication.modelo.ItemAluguel;
import aplication.modelo.Produto;
import aplication.util.ConnectioinFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Erick
 */
public class ProdutoDAO {
    
    public void inserir(Produto produto){
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        manager.getTransaction().begin();
        manager.persist(produto);
        manager.getTransaction().commit();
        manager.close();
    }
    
    public void alterar(Produto produtoNovo){        
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();

        Produto produto = new Produto();

        manager.getTransaction().begin();
            produto = manager.find(Produto.class, produtoNovo.getId());
            produto.setNome(produtoNovo.getNome());
            produto.setPrecoAluguel(produtoNovo.getPrecoAluguel());
            produto.setSaldo(produtoNovo.getSaldo());
            produto.setQtde(produtoNovo.getQtde());
            produto.setAtivo(produtoNovo.isAtivo());
            produto.setGrupoProduto(produtoNovo.getGrupoProduto());
            produto.setImagem(produtoNovo.getImagem());
        manager.getTransaction().commit();
        manager.close();
    }
    
    public List<Produto> pesquisar(Produto produto){
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        manager.getTransaction().begin();
            Query query = manager.createQuery("select c from Produto c where c.nome like :nomeParametro");
            query.setParameter("nomeParametro","%"+produto.getNome()+"%");
            List<Produto> produtos = query.getResultList();
        manager.getTransaction();
        manager.close();
        
        return produtos;    
    }
    
    public void alteraStatus(Produto produtoNovo){
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        Produto produto;
        
        manager.getTransaction().begin();
            produto = manager.find(Produto.class, produtoNovo.getId());
            produto.setSaldo(produtoNovo.getSaldo());
        manager.getTransaction().commit();
        manager.close();
    }
    
    public List<Produto> pesquisarProdutoCategoria(Produto produto){
        
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        manager.getTransaction().begin();
            Query query = manager.createQuery("select c from Produto c where c.grupoProduto.id =:pgrupoProduto");
            query.setParameter("pgrupoProduto",produto.getGrupoProduto().getId());
            List<Produto> produtos = query.getResultList();
        manager.getTransaction();
        manager.close();
        
        return produtos;    
    }
    
     public Produto produtoFind(Long id){
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        try{
            manager.getTransaction().begin();
            Produto c = manager.find(Produto.class,id);
            manager.getTransaction();
            return c;
        } catch(Exception e){
            e.getLocalizedMessage();
        }finally{
            manager.close();
        }
        
        return null;
                
    }
     
     
}
