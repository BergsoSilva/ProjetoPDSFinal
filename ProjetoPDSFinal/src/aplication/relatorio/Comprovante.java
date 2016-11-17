/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.relatorio;

import aplication.Exceptions.BDException;
import aplication.modelo.Aluguel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author flavio
 */
public class Comprovante {
    public void comprovanteDevolucaoFinalizado( Aluguel aluguel ) throws BDException, JRException, SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projetopds","root","123");
        Map parametros = new HashMap();
        
        parametros.put("idaluguel", aluguel.getId());
        
        JasperPrint jp = JasperFillManager.fillReport( "C:\\Users\\flavio\\Documents\\Didático\\TDS\\4º\\PDS\\projeto\\ProjetoPDSFinal\\relatorios\\comprovanteDevolucaoFinalizado.jasper", parametros, con);
        JasperExportManager.exportReportToPdfFile (jp, "C:\\Users\\flavio\\Documents\\Didático\\TDS\\4º\\PDS\\projeto\\ProjetoPDSFinal\\relatorios\\comprovanteDevolucaoFinalizado.pdf");
        JasperViewer jv = new JasperViewer(jp, false);
        jv.setVisible(true);
    }
}
