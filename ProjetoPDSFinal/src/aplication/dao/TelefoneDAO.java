package aplication.dao;

import aplication.Exceptions.BDException;
import aplication.Exceptions.BDMensagensPadrao;
import javax.persistence.EntityManager;
import aplication.modelo.Telefone;
import aplication.util.ConnectioinFactory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JOptionPane;


/**
 *
 * @author Erick
 */
public class TelefoneDAO {
    
    public void inserir(Telefone telefone){
      // Inserido a classe de util
      
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        try{
        
            manager.getTransaction().begin();
            manager.persist(telefone);
            manager.getTransaction().commit();
            manager.close(); 
            
        }catch(Exception ex ){
               JOptionPane.showMessageDialog(null,BDMensagensPadrao.INSTRUCAO_ERRO);
               //manager.getTransaction().rollback();
        }
        
    }
    
    /*public void alterar(Telefone fone){        
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        Telefone telefone;
        try{
            
        manager.getTransaction().begin();
            telefone = manager.find(Telefone.class, fone.getClass());
            telefone.setTipo(fone.getTipo());
            telefone.setNumero(fone.getNumero());
            
            
            manager.getTransaction().commit();
            JOptionPane.showMessageDialog(null,BDMensagensPadrao.CADASTRADO_COM_SUCESSO);
            manager.close();
        }catch(Exception ex ){
               JOptionPane.showMessageDialog(null,BDMensagensPadrao.INSTRUCAO_ERRO);
               manager.getTransaction().rollback();
        }finally{
               manager.close();
        }
    }*/
    
    public List<Telefone> pesquisarTelefoneCliente(){
        List<Telefone> telefones = new ArrayList<>();
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
       
        manager.getTransaction().begin();
        
        Query query = manager.createQuery("SELECT t FROM Telefone t where t.cliente is not null");
                
        telefones = query.getResultList();
        System.out.println(telefones);
        
        manager.getTransaction();
        
        manager.close();
        
        return telefones;    
    }
    
    // falta o delete
}
