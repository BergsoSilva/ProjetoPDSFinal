package aplication.dao;

import aplication.Exceptions.BDException;
import aplication.Exceptions.BDMensagensPadrao;
import aplication.modelo.Cidade;
import aplication.modelo.EstadoCivil;
import aplication.modelo.Funcao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import aplication.modelo.Funcionario;
import aplication.util.ConnectioinFactory;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author Erick
 */
public class FuncionarioDAO {
    
    public Funcionario inserir(Funcionario funcionario) throws BDException{
      // Inserido a classe de util
      
           EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
           try {
               manager.getTransaction().begin();
                if(funcionario.getId() == null) {
                    // caso id for igual a nulo inserir no banco
                     manager.persist(funcionario);
                } else {
                   
                    
                    /* caso o funcionario existe no banco executa a alteração */
                     return manager.merge(funcionario);
                  
                }
                
                manager.flush();
                manager.getTransaction().commit();
               JOptionPane.showMessageDialog(null,BDMensagensPadrao.CADASTRADO_COM_SUCESSO);
                 return funcionario;
           }catch(Exception ex ){
               manager.getTransaction().rollback();
               throw  new BDException(BDMensagensPadrao.INSTRUCAO_ERRO, ex);
           }finally{
               manager.close();
           }
           
           // retorna o  funcionario persitido
          
     }
    
   /* public void alterar(Funcionario funcionarios){        
       // EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjetoPDSFinalPU");
       // EntityManager manager = factory.createEntityManager();
       EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        
        Cliente cliente = new Cliente();
        
        manager.getTransaction().begin();
            cliente = manager.find(Cliente.class, funcionarios.getId());
            cliente.setNome(funcionarios.getNome());
            cliente.setClifone(funcionarios.getClifone());
            cliente.setNumHabilitacao(funcionarios.getNumHabilitacao());
            cliente.setSexo(funcionarios.getSexo());
            cliente.setDataDasc(funcionarios.getDataDasc());
        manager.getTransaction().commit();
        manager.close();
    }*/
    
    public List<Funcionario> pesquisar(Funcionario funcinario){
        
        EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
        System.out.println(funcinario.getNome());
        
            manager.getTransaction().begin();
            Query query = manager.createQuery("select f from Funcionario f where f.nome like :nomeRecebido");
            query.setParameter("nomeRecebido","%"+funcinario.getNome()+"%");
            List<Funcionario> funcionarios = query.getResultList();
            manager.getTransaction();
            manager.close();
        return funcionarios;    
    }
    
    // falta o delete
    
    
    public Funcionario consultarPorId(Long id) {
            EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
            Funcionario funcionario = null;
            try {
                 return manager.find(Funcionario.class, id);
            }finally {
                manager.close();
            }
  }
    
    
    public void excluir (Long id) throws BDException{
      EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
      try {
            manager.getTransaction().begin();
            Funcionario funcionario = consultarPorId(id);
            manager.remove(funcionario);
            manager.flush();
            manager.getTransaction().commit();
           JOptionPane.showMessageDialog(null,BDMensagensPadrao.CADASTRADO_COM_SUCESSO);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null,BDMensagensPadrao.INSTRUCAO_ERRO);
        manager.getTransaction().rollback();
    } finally {
      manager.close();
    }
  }
    
    public List<Funcao> listarFuncao(){
        List<Funcao> fcs = new ArrayList<>();
         EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
            manager.getTransaction().begin();
            Query query = manager.createQuery("select fc from Funcao fc ");
            fcs = query.getResultList();
            manager.getTransaction();
            manager.close();
        return fcs;
    }
   
    public List<Cidade> listarCidades(){
            List<Cidade> cidades = new ArrayList<>();
            EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
            manager.getTransaction().begin();
            Query query = manager.createQuery("select c from Cidade c ");
            cidades = query.getResultList();
            manager.getTransaction();
            manager.close();
        return cidades;
        
    }
    
    public List<EstadoCivil> listarEstadoCivil(){
            List<EstadoCivil> estadocivis = new ArrayList<>();
            EntityManager manager= ConnectioinFactory.getEntityManagerFactory();
            manager.getTransaction().begin();
            Query query = manager.createQuery("select e from EstadoCivil e ");
            estadocivis = query.getResultList();
            manager.getTransaction();
            manager.close();
        return estadocivis;
        
        
    }
}
