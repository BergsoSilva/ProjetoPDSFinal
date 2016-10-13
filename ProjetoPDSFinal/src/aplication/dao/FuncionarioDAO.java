package aplication.dao;

import aplication.Exceptions.BDException;
import aplication.Exceptions.BDMensagensPadrao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import aplication.modelo.Funcionario;
import aplication.util.ConnectioinFactory;
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
               manager.getTransaction().begin();;
                if(funcionario.getId() == null) {
                    // caso id for igual a nulo persistir
                     manager.persist(funcionario);
                } else {
                    /* Caso a instrução for edita verificar se o funcionario exit */
                    if (!manager.contains(funcionario)){
                         // funcionario ser alterado nao existe no banco
                        if (consultarPorId(funcionario.getId()) == null) {
                                throw new BDException(BDMensagensPadrao.FUNCIONARIO_NAO_EXIST);
                        }
                    }
                    
                    // caso o funcionario existe no banco executa a alteração
                     JOptionPane.showMessageDialog(null,funcionario.getNome()+BDMensagensPadrao.INSTRUCAO_ERRO);
                    return manager.merge(funcionario);
                }
                
                manager.flush();
                manager.getTransaction().commit();
               JOptionPane.showMessageDialog(null,BDMensagensPadrao.CADASTRADO_COM_SUCESSO);
                
           }catch(BDException ex ){
                manager.getTransaction().rollback();
               throw  new BDException(BDMensagensPadrao.INSTRUCAO_ERRO, ex);
           }finally{
               manager.close();
           }
           
           // retorna o  funcionario persitido
           return funcionario;
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
}
