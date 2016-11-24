/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.relatorio;

import aplication.Exceptions.BDException;
import aplication.util.ControlaConnexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
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
public class TempoMedioAluguelProdutoRelatorio {
    public void gerarRelatorio( Calendar dataInicial, Calendar dataFinal ) throws BDException, JRException, SQLException{
        Connection con = ControlaConnexao.getConexao();
        
        Map parametros = new HashMap();
        
        Date dataExibicao = dataFinal.getTime();
        
        int dia = dataFinal.get(Calendar.DAY_OF_MONTH) + 1;
        dataFinal.set(Calendar.DAY_OF_MONTH, dia);
        
        java.sql.Date dataInicialDB = new java.sql.Date(dataInicial.getTimeInMillis());
        java.sql.Date dataFinalDB = new java.sql.Date(dataFinal.getTimeInMillis());
        
        System.out.println("dataInicial: " + dataInicialDB);
        System.out.println("dataFinal: " + dataFinalDB);
        
        parametros.put("datainicial", dataInicialDB);
        parametros.put("datafinal", dataFinalDB);
        parametros.put("dataexibicao", dataExibicao);
        
        JasperPrint jp = JasperFillManager.fillReport( "C:\\Users\\flavio\\Documents\\Didático\\TDS\\4º\\PDS\\projeto\\ProjetoPDSFinal\\relatorios\\mediaHorasAluguel.jasper", parametros, con);
        JasperExportManager.exportReportToPdfFile (jp, "C:\\Users\\flavio\\Documents\\Didático\\TDS\\4º\\PDS\\projeto\\ProjetoPDSFinal\\relatorios\\mediaHorasAluguel.pdf");
        JasperViewer jv = new JasperViewer(jp, false);
        jv.setVisible(true);
    }
    
    private Calendar atualizaDataFinal(Calendar dataInicial) throws ParseException{
        int dia = dataInicial.get(Calendar.DAY_OF_MONTH);
        dia ++;
        dataInicial.set(Calendar.DAY_OF_MONTH, dia);
        
        return dataInicial;
    }
}
