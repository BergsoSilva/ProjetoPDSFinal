/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.testesUnitario;

import aplication.util.ConnectioinFactory;
import javax.persistence.EntityManager;

/**
 *
 * @author unkwow
 */
public class UtilTestesCreateEntity {
    
    public static void main(String[] args) {
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
    }
    
}
