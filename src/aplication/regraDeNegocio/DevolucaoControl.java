/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.regraDeNegocio;

import aplication.Exceptions.BDException;
import aplication.dao.AluguelDAO;
import aplication.dao.MultaDAO;
import aplication.dao.ProdutoDAO;
import aplication.modelo.Aluguel;
import aplication.modelo.Multa;
import aplication.modelo.Produto;
import aplication.modelo.Status;
import aplication.relatorio.Comprovante;
import aplication.view.multa.TelaFormularioMulta;
import aplication.view.multa.TelaPesquisarMulta;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author flavio
 */
public class DevolucaoControl {
    private Aluguel aluguel;
    
    public DevolucaoControl( Aluguel aluguel) throws BDException, JRException, SQLException{
        this.aluguel = aluguel;
    }
    
    //Processa a devolução de produtos alugados
    public void devolucao() throws BDException, JRException, SQLException{
        Long statusId = aluguel.getStatus().getId();
        
        if(statusId.equals(Aluguel.FINALIZADO) || statusId.equals(Aluguel.FINALIZADO_COM_PENDENCIA)){
            JOptionPane.showMessageDialog(null, "Aluguel Já Finalizado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }else {
            //Atribui a data e a hora atuais à data de devolução
            Calendar dtDevolucao = Calendar.getInstance();
            aluguel.setDtDevolucao(dtDevolucao);
        
            int tempoDeAtraso = estaAtrasado();
            
            if(temMulta()){
                new TelaPesquisarMulta(aluguel).setVisible(true);
            }else if (tempoDeAtraso > 0){
                updateAluguel();
                new TelaFormularioMulta(aluguel, new TelaPesquisarMulta(aluguel), tempoDeAtraso);
            }else{
                int escolha = JOptionPane.showConfirmDialog(null, "Houve extravio de produtos ou há avarias em algum produto?");

                if (escolha == 1){
                    finalizarDevolucaoSemMulta();
                    gerarComprovanteDevolucao();
                } else if (escolha == 0){
                    updateAluguel();
                    new TelaPesquisarMulta(aluguel).setVisible(true);
                }
            }
        }
    }
    
    //verifica se já foi cadastrada alguma multa para o aluguel
    public boolean temMulta() throws BDException{
        MultaDAO multaDAO = new MultaDAO();
        List<Multa> multas = multaDAO.pesquisar(aluguel);
        
        return multas.size() > 0;
    }
    
    //Verifica se o cliente está realizando a devolução atrasado e
    //informa a quantidade de horas de atraso
    //Concede um tempo extra de 20 min
    public int estaAtrasado(){
        int diaInicio = aluguel.getDtAluguel().get(Calendar.DAY_OF_MONTH);
        int horaInicio = aluguel.getDtAluguel().get(Calendar.HOUR_OF_DAY);
        int minutoInicio = aluguel.getDtAluguel().get(Calendar.MINUTE);
        int diaDevolucao = aluguel.getDtDevolucao().get(Calendar.DAY_OF_MONTH);
        int horaDevolucao = aluguel.getDtDevolucao().get((Calendar.HOUR_OF_DAY));
        int minutoDevolucao = aluguel.getDtDevolucao().get((Calendar.MINUTE));
        int tempoLocacao = aluguel.getTempo();
        int horaLimite = horaInicio + tempoLocacao;
        int minutoLimite = minutoInicio + 20;
        
        if ( diaInicio != diaDevolucao ){
            return 24;
        }
        
        if(minutoLimite > 59){
            horaLimite += 1;
            minutoLimite = minutoLimite - 60;
        }
        
        if (horaDevolucao < horaLimite){
            return 0;
        }else if( horaDevolucao == horaLimite && minutoDevolucao <= minutoLimite){
            return 0;
        } else if (horaDevolucao == horaLimite){
            return 1;
        } else {
            return horaDevolucao - horaLimite;
        }
    }
    
    public void finalizarDevolucaoSemMulta(){
        Status statusAluguel = new Status();
        statusAluguel.setId(Aluguel.FINALIZADO);
        aluguel.setStatus(statusAluguel);
        updateAluguel();
        updateSaldoProduto();
    }
    
    public void gerarComprovanteDevolucao() throws BDException, JRException, SQLException{
        Long status = aluguel.getStatus().getId();
        Comprovante comprovante = new Comprovante();
        
        if (status.equals(Aluguel.FINALIZADO) || status.equals(Aluguel.FINALIZADO_COM_PENDENCIA)){
            comprovante.gerarComprovanteDevolucao(aluguel);
        } else {
            JOptionPane.showMessageDialog(null, "Aluguel Não Finalizado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateSaldoProduto(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = aluguel.getProduto();
        Produto produtoBanco;
        int saldo = aluguel.getQuantidade();
        
        produtoBanco = produtoDAO.produtoFind(produto.getId());
        double saldoBanco = produtoBanco.getSaldo();
        produtoBanco.setSaldo(saldoBanco + saldo);
        produtoDAO.alterar(produtoBanco);
        
        if (saldoBanco+saldo <= produtoBanco.getQtde()){
            produtoDAO.alterar(produtoBanco);
        }else {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar saldo do produto!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateAluguel(){
        AluguelDAO aluguelDAO = new AluguelDAO();
        aluguelDAO.alterar(aluguel);
    }
}
