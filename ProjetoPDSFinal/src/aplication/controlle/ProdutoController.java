/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.controlle;

import aplication.util.ControlaConnexao;
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
public class ProdutoController {
    
    String arquivo="relatorios/mediaHorasAluguel.jasper";

  // Connection con = ControlaConnexao.getConexao();
        
    Map parametros = new HashMap();
        
     
    //JasperPrint jp = JasperFillManager.fillReport(arquivo,null, con);
  //  JasperViewer jv = new JasperViewer(jp, false);

    
}
      
