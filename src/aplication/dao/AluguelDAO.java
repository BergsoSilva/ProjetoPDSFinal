/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.dao;

import aplication.modelo.Aluguel;
import aplication.modelo.Cliente;
import aplication.modelo.GrupoProduto;
import aplication.modelo.Usuario;
import aplication.relatorio.RelatorioAluguelCliente;
import aplication.util.ConnectioinFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author unkwow
 */
public class AluguelDAO {
    
    public Aluguel inserir(Aluguel aluguel) throws Exception {
      // Inserido a classe de util
           
           EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
            manager.getTransaction().begin();
            manager.persist(aluguel);
            manager.getTransaction().commit();
            manager.close();
            
            return aluguel;
    }
    
    public Aluguel consultarPorId(Long id ){
         Aluguel object=null;
        
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        manager.getTransaction().begin();
        object=manager.find(Aluguel.class, id);
        return object;
        
    }
     
    public void alterar(Aluguel aluguel){
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        manager.getTransaction().begin();
        manager.merge(aluguel);
        manager.getTransaction().commit();
        manager.close();
    }
    
    public void deletar(Long id){
        EntityManager manager = ConnectioinFactory.getEntityManagerFactory();
        
        Aluguel aluguel = consultarPorId(id);
        
        manager.getTransaction().begin();
              aluguel= manager.find(Aluguel.class, id);
            manager.remove(aluguel);
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        manager.getTransaction().commit();
        manager.close();
    }
    
     public List<RelatorioAluguelCliente> consultaRelatorio(Aluguel aluguel){
        
       List<RelatorioAluguelCliente> usuario;
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        manager.getTransaction().begin();
       try{        
            String qry = "SELECT NEW aplication.relatorio.RelatorioAluguelCliente( u.c.nome, u.c.cpf, u.id ,u.s.descricao ) FROM Aluguel a "
                    + " inner join fetch  cliente c "
                    + " inner join fetch  status s "
                    + " where u.id=:1";

            Query query = manager.createQuery(qry);
            query.setParameter(1, aluguel.getId());
            usuario=query.getResultList();
            manager.getTransaction();
            manager.close();
        
             return  usuario;
       }catch(Exception ex){
           JOptionPane.showMessageDialog(null,"Usuario não existe"+ex.getCause());
       }
       
       return  null;
    }
   
}
