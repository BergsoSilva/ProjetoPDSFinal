/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.relatorio;

import aplication.Exceptions.BDException;
import aplication.util.ControlaConnexao;
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
public class AluguelRelatorio {
    private Calendar dataInicial;
    private Calendar dataFinal;
    private String caminhoRelatorio;
    private String caminhoPdf;
    private Map parametros;
    
    public AluguelRelatorio(Calendar dataInicial, Calendar dataFinal){
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        parametros = new HashMap();
    }
    
    public void alugueisPorData() throws BDException, JRException, SQLException{
        caminhoRelatorio = "src\\relatorios\\alugueisPorData.jasper";
        caminhoPdf = "src\\relatorios\\alugueisPorData.pdf";
        gerarRelatorio();
    }
    
    public void alugueisPorDiaDaSemana() throws BDException, JRException, SQLException{
        caminhoRelatorio = "src\\relatorios\\alugueisPorDiaDaSemana.jasper";
        caminhoPdf = "src\\relatorios\\alugueisPorDiaDaSemana.pdf";
        gerarRelatorio();
    }
    
    public void alugueisPorDataEProduto(Long idProduto) throws BDException, JRException, SQLException{
        caminhoRelatorio = "src\\relatorios\\alugueisPorDataEProduto.jasper";
        caminhoPdf = "src\\relatorios\\alugueisPorDataEProduto.pdf";
        parametros.put("idproduto", idProduto);
        gerarRelatorio();
    }
    
    public void alugueisPorDiaDaSemanaEProduto(Long idProduto) throws BDException, JRException, SQLException{
        caminhoRelatorio = "src\\relatorios\\alugueisPorDiaDaSemanaEProduto.jasper";
        caminhoPdf = "src\\relatorios\\alugueisPorDiaDaSemanaEProduto.pdf";
        parametros.put("idproduto", idProduto);
        gerarRelatorio();
    }
    
    public void alugueisPorDataECategoria(Long idCategoria) throws BDException, JRException, SQLException{
        caminhoRelatorio = "src\\relatorios\\alugueisPorDataECategoria.jasper";
        caminhoPdf = "src\\relatorios\\alugueisPorDataECategoria.pdf";
        parametros.put("idcategoria", idCategoria);
        gerarRelatorio();
    }
    
    public void alugueisPorDiaDaSemanaECategoria(Long idCategoria) throws BDException, JRException, SQLException{
        caminhoRelatorio = "src\\relatorios\\alugueisPorDiaDaSemanaECategoria.jasper";
        caminhoPdf = "src\\relatorios\\alugueisPorDiaDaSemanaECategoria.pdf";
        parametros.put("idcategoria", idCategoria);
        gerarRelatorio();
    }
    
    private void configuraParametros(){
        //parametro para exibir a data final do relatório
        Date dataExibicao = dataFinal.getTime();
        
        //A data final é incrementada em um dia para que
        //a consulta ao banco de dados retorne o valor esperado
        int dia = dataFinal.get(Calendar.DAY_OF_MONTH) + 1;
        dataFinal.set(Calendar.DAY_OF_MONTH, dia);
        
        java.sql.Date dataInicialDB = new java.sql.Date(dataInicial.getTimeInMillis());
        java.sql.Date dataFinalDB = new java.sql.Date(dataFinal.getTimeInMillis());
        
        parametros.put("datainicial", dataInicialDB);
        parametros.put("datafinal", dataFinalDB);
        parametros.put("dataexibicao", dataExibicao);
    }
    
    private void gerarRelatorio() throws BDException, JRException, SQLException{
        Connection con = ControlaConnexao.getConexao();
        configuraParametros();
        
        JasperPrint jp = JasperFillManager.fillReport( caminhoRelatorio, parametros, con);
        JasperExportManager.exportReportToPdfFile (jp, caminhoPdf);
        JasperViewer jv = new JasperViewer(jp, false);
        jv.setVisible(true);
    }
}
