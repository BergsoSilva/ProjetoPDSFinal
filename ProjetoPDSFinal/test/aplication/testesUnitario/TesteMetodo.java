/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.testesUnitario;

import aplication.dao.FuncionarioDAO;
import aplication.dao.GrupoProdutoDAO;
import aplication.dao.TelefoneDAO;
import aplication.modelo.Funcionario;
import aplication.modelo.GrupoProduto;
import aplication.modelo.Telefone;

/**
 *
 * @author unkwow
 */
public class TesteMetodo {
    public static void main(String[] args) {
        
        GrupoProdutoDAO dao= new GrupoProdutoDAO();
        
        GrupoProduto p = new GrupoProduto();
        p.setDescricao("");
        
        for (GrupoProduto arg : dao.pesquisar(p)) {
            System.out.println("-"+arg.getDescricao());
        }
  
        /*for (Telefone tel : dao.pesquisar(f)) {
            System.out.println(""+ tel.getTipo() +"\n" + tel.getNumero());
        }*/

    }
    
}
