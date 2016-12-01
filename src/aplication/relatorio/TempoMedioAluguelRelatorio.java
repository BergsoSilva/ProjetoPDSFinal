/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.relatorio;

import aplication.Exceptions.BDException;
import aplication.util.ControlaConnexao;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
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
public class TempoMedioAluguelRelatorio {
    private Calendar dataInicial;
    private Calendar dataFinal;
    private String caminhoRelatorio;
    private String caminhoPdf;
    
    public TempoMedioAluguelRelatorio(Calendar dataInicial, Calendar dataFinal){
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }
    
    public void tempoMedioPorCliente() throws BDException, JRException, SQLException{
        caminhoRelatorio = "src\\relatorios\\tempoMedioAluguelCliente.jasper";
        caminhoPdf = "src\\relatorios\\tempoMedioAluguelCliente.pdf";
        gerarRelatorio();
    }
    
    public void tempoMedioPorProduto() throws BDException, JRException, SQLException{
        caminhoRelatorio = "src\\relatorios\\tempoMedioAluguelProduto.jasper";
        caminhoPdf = "src\\relatorios\\tempoMedioAluguelProduto.pdf";
        gerarRelatorio();
    }
    
    public void tempoMedioPorCategoria() throws BDException, JRException, SQLException{
        caminhoRelatorio = "src\\relatorios\\tempoMedioAluguelCategoria.jasper";
        caminhoPdf = "src\\relatorios\\tempoMedioAluguelCategoria.pdf";
        gerarRelatorio();
    }
    
    private void gerarRelatorio() throws BDException, JRException, SQLException{
        Connection con = ControlaConnexao.getConexao();
        Map parametros = new HashMap();
        
        //parametro para exibir a data final do relatório
        Date dataExibicao = dataFinal.getTime();
        
        //A data final é incrementada em um dia para que
        //a consulta ao banco de dados retorne o valor esperado
        int dia = dataFinal.get(Calendar.DAY_OF_MONTH) + 1;
        dataFinal.set(Calendar.DAY_OF_MONTH, dia);
        
        java.sql.Date dataInicialDB = new java.sql.Date(dataInicial.getTimeInMillis());
        java.sql.Date dataFinalDB = new java.sql.Date(dataFinal.getTimeInMillis());
        
        System.out.println("dataInicial: " + dataInicialDB);
        System.out.println("dataFinal: " + dataFinalDB);
        
        parametros.put("datainicial", dataInicialDB);
        parametros.put("datafinal", dataFinalDB);
        parametros.put("dataexibicao", dataExibicao);
        
        JasperPrint jp = JasperFillManager.fillReport( caminhoRelatorio, parametros, con);
        JasperExportManager.exportReportToPdfFile (jp, caminhoPdf);
        JasperViewer jv = new JasperViewer(jp, false);
        jv.setVisible(true);
    }
}
