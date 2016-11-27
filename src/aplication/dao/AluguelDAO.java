/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.dao;

import aplication.modelo.Aluguel;
import aplication.modelo.Cliente;
import aplication.modelo.GrupoProduto;
import aplication.util.ConnectioinFactory;
import javax.persistence.EntityManager;
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
    
   
}
