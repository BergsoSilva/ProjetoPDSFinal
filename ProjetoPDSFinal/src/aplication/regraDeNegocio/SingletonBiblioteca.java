/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.regraDeNegocio;

import aplication.dao.FuncionarioDAO;
import aplication.dao.ProdutoDAO;
import aplication.modelo.Aluguel;
import aplication.modelo.Carrinho;
import aplication.modelo.Funcao;
import aplication.modelo.Funcionario;
import aplication.modelo.Produto;
import aplication.modelo.Usuario;
import static aplication.view.telaPrincipal.TelaPrincipal.qtde;
import java.util.List;

/**
 * 
 * @author unkwow
 */
public class SingletonBiblioteca {
    
    static{
        
    }
    
    public static void retornarValorParaStoque(int valor, Produto produto){
        Double contaSaldo = produto.getSaldo()+qtde; 
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produto.setSaldo(contaSaldo);
        produtoDAO.alteraStatus(produto);
    }
    
   public static void baixaSaldoParaEstoque(int valor, Produto produto){
        Double contaSaldo = produto.getSaldo() - qtde; 
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produto.setSaldo(contaSaldo);
        produtoDAO.alteraStatus(produto);
   }
   
   public static boolean produtoExistCarrinho(List<Carrinho> carrinhos , Carrinho carrinho){
       int r=carrinhos.size();
       boolean variaveRetorno=true;
            
            if(r>0){
                
                for (Carrinho c : carrinhos) {
                    if (carrinho.getNomeProduto().equals(c.getNomeProduto())){
                        variaveRetorno =false;
                        break;
                     }
                 }
            }
            
         return variaveRetorno;
            
   }
   
   public static Usuario validarUsuario( String login, String senha){
      boolean v= false;
      Usuario user =null;
      
      FuncionarioDAO dao = new FuncionarioDAO();
      Funcionario f = new Funcionario();
      f.setNome("");
      List<Funcionario> funcionarios= dao.pesquisar(f);
      
       for (Funcionario fun : funcionarios) {
           if (fun.getLogin().equals(login)&& fun.getSenha().equals(senha)){
               user= dao.validarUsuario(login, senha);
           }else{
               user =null;
           }
              
       }
       
      
      return user;
   }
   
   public static  Funcao getFuncao(String login, String senha){
       FuncionarioDAO dao = new FuncionarioDAO();
        
       Funcao f = dao.descobreFuncao(login, senha).getFuncao();
       
       return f;
   }
   
   public static void erasePedido(int qtde, Produto produto , Aluguel al){
      if (al.getStatus().getId()==1){
          ThretdTempoPedido t = new ThretdTempoPedido(qtde, produto);
          t.start();
      }
      
       
   }
   
   public static void paraThred( ThretdTempoPedido t ){
       t.stop();
   }
   
   
   
}
