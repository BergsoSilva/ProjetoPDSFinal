/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.regraDeNegocio;

import aplication.dao.ProdutoDAO;
import static aplication.modelo.Aluguel_.produto;
import aplication.modelo.Carrinho;
import aplication.modelo.Produto;
import static aplication.view.telaPrincipal.TelaPrincipal.qtde;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author unkwow
 */
public class UnBiblioteca {
    
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
}
