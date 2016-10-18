/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.testesUnitario;

import aplication.dao.FuncionarioDAO;
import aplication.dao.TelefoneDAO;
import aplication.modelo.Funcionario;
import aplication.view.funcionario.Telefone;

/**
 *
 * @author unkwow
 */
public class TesteMetodo {
    public static void main(String[] args) {
        
        TelefoneDAO dao= new TelefoneDAO();
        StringBuilder strngTelefone= new StringBuilder();
        
        Funcionario f = new Funcionario();
        f.setId(Long.parseLong("1"));
  
        for (Telefone tel : dao.pesquisar(f)) {
            System.out.println(""+ tel.getTipo() +"\n" + tel.getNumero());
        }

    }
    
}
