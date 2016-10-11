/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GRS
 */
public class ConnectioinFactory {
        
        public static EntityManagerFactory emf;
        
        static {
            emf= Persistence.createEntityManagerFactory("ProjetoPDSFinalPU");
        }
        
        public static EntityManager getEntityManagerFactory(){
              return  emf.createEntityManager();
        }
}
