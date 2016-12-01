/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.dao;

import aplication.Exceptions.BDException;
import aplication.modelo.Aluguel;
import aplication.modelo.StatusMulta;
import aplication.modelo.Multa;
import aplication.util.ConnectioinFactory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author flavio
 */
public class MultaDAO {
    
    public void inserir(Multa multa) throws BDException{
           
         EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
         
         manager.getTransaction().begin();
         System.out.println("id multa add: " +  multa.getId());
         manager.persist(multa);
         manager.getTransaction().commit();
         manager.close();
    }
    
    public void remover(Multa multa) throws BDException{
           
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();

         manager.getTransaction().begin();
         Multa multaEncontrada = manager.find(Multa.class, multa.getId());
         manager.remove(multaEncontrada);
         manager.getTransaction().commit();
         manager.close();
    }
    
    public void alterar(Multa multa) throws BDException{
           
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();

         manager.getTransaction().begin();
         System.out.println("id multa alt: " +  multa.getId());
         manager.merge(multa);
         manager.getTransaction().commit();
         manager.close();
    }
    
    public List<Multa> pesquisar(Aluguel aluguel) throws BDException{
        List<Multa> multas = new ArrayList();
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        manager.getTransaction().begin();
        Query query = manager.createQuery("select m from Multa m, Aluguel alu, StatusMulta s where alu.id = :mAluId and m.aluguel.id = alu.id and m.statusMulta.id = s.id");
        query.setParameter("mAluId", aluguel.getId());
        manager.getTransaction();
        multas = query.getResultList();
        manager.close();
        
        for(Multa multa: multas){
            StatusMulta statusMulta = new StatusMulta();
            statusMulta.setId(multa.getStatusMulta().getId());
            statusMulta.setDescricao(multa.getStatusMulta().getDescricao());
            
            multa.setStatusMulta(statusMulta);
        }
        
        return multas;
    }
}
