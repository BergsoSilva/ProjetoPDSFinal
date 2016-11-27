/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.controlle;

import aplication.util.ControlaConnexao;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author pc
 */
public class AluguelController {
    
 private Connection con =null;
  
public void gerarPorCodigo(){   
        try{
            con=ControlaConnexao.getConexao();
            
            //String arquivo = "src\\aplication\\relatorio\\aluguelProcliente.jasper";

            File file = new File("src/aplication/relatorio/aluguelProcliente.jasper");
            
                System.out.println(file.getAbsolutePath());
                JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), null, con);

                JasperViewer view = new JasperViewer(print,true);
                view.setVisible(true);



        }catch( Exception ex){
                System.out.println("Erro"+ex.getMessage());
        }
}
public void geraTodos(Integer codigo){   
        try{
            con=ControlaConnexao.getConexao();
            
            String arquivo = "src\\aplication\\relatorio\\aluguelProcliente.jasper";

            HashMap filtro = new HashMap();

                filtro.put("alId",codigo);

                JasperPrint print = JasperFillManager.fillReport(arquivo, filtro, con);

                JasperViewer view = new JasperViewer(print,true);
                view.setVisible(true);



        }catch( Exception ex){
                System.out.println("Erro"+ex.getMessage());
        }
}
    
}
