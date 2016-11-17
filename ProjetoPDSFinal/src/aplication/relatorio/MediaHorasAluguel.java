/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.relatorio;

import aplication.Exceptions.BDException;
import aplication.modelo.ItemAluguel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
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
public class MediaHorasAluguel {
    public void gerarRelatorio( Calendar dataInicial, Calendar dataFinal ) throws BDException, JRException, SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projetopds","root","123");
        Map parametros = new HashMap();
        
        java.sql.Date dataInicialDB = new java.sql.Date(dataInicial.getTimeInMillis());
        java.sql.Date dataFinalDB = new java.sql.Date(dataFinal.getTimeInMillis());
        
        parametros.put("datainicial", dataInicialDB);
        parametros.put("datafinal", dataFinalDB);
        
        JasperPrint jp = JasperFillManager.fillReport( "C:\\Users\\flavio\\Documents\\Didático\\TDS\\4º\\PDS\\projeto\\ProjetoPDSFinal\\relatorios\\mediaHorasAluguel.jasper", parametros, con);
        JasperExportManager.exportReportToPdfFile (jp, "C:\\Users\\flavio\\Documents\\Didático\\TDS\\4º\\PDS\\projeto\\ProjetoPDSFinal\\relatorios\\mediaHorasAluguel.pdf");
        JasperViewer jv = new JasperViewer(jp, false);
        jv.setVisible(true);
    }
}
