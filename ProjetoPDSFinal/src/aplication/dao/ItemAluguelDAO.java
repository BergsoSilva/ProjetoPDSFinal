/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.dao;

import aplication.modelo.Aluguel;
import aplication.modelo.Cliente;
import aplication.modelo.ItemAluguel;
import aplication.util.ConnectioinFactory;
import javax.persistence.EntityManager;

/**
 *
 * @author unkwow
 */
public class ItemAluguelDAO {
    public ItemAluguel inserir(ItemAluguel item) throws Exception {
      // Inserido a classe de util
           
           EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
            manager.getTransaction().begin();
            manager.persist(item);
            manager.getTransaction().commit();
            manager.close();
            
            return item;
    }
    
    
    
   
}
