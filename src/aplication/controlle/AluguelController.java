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
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author pc
 */
public class AluguelController {
    
 private Connection con =null;
  
public void gerarPorCodigo(Integer id){   
        try{
            con=ControlaConnexao.getConexao();
            
            //String arquivo = "src\\aplication\\relatorio\\aluguelProcliente.jasper";

            File file = new File("src/aplication/relatorio/aluguelProcliente.jasper");
            
            Map parametros = new HashMap();
           parametros.put("cID",id); 
            
             System.out.println(file.getAbsolutePath());
             JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), parametros, con);

             JasperViewer view = new JasperViewer(print,true);
             view.setVisible(true);



        }catch( Exception ex){
                System.out.println("Erro"+ex.getMessage());
        }
}
public void gerarGrafico1(){   
        try{
                    con=ControlaConnexao.getConexao();
            
                    File file = new File("src/aplication/relatorio/Grafico1.jasper");
           

                    JasperPrint print = JasperFillManager.fillReport(file.getPath(), null, con);

                    JasperViewer view = new JasperViewer(print,true);
                    view.setVisible(true);



        }catch( Exception ex){
                System.out.println("Erro"+ex.getMessage());
        }
}
    
}
