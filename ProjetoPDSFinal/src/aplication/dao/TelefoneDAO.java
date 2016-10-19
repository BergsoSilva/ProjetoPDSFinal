package aplication.dao;

import aplication.Exceptions.BDException;
import aplication.Exceptions.BDMensagensPadrao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import aplication.modelo.Cliente;
import aplication.modelo.Funcionario;
import aplication.util.ConnectioinFactory;
import aplication.modelo.Telefone;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.persistence.TypedQuery;
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
               manager.getTransaction().rollback();
        }finally{
               manager.close();
        }
        
    }
    
    public void alterar(Telefone fone){        
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
    }
    
    public List<Telefone> pesquisar(Funcionario funcionario){
        List<Telefone> telefones = new ArrayList<>();
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
       
        manager.getTransaction().begin();
        
        String qry = "SELECT  t  FROM Telefone t "
                    + " where t.funcionario.id = :pid";
        Query query = manager.createQuery(qry);
        query.setParameter("pid",funcionario.getId());
        telefones = query.getResultList();
        
        manager.getTransaction();
        
        manager.close();
        
        return telefones;    
    }
    
    // falta o delete
}
