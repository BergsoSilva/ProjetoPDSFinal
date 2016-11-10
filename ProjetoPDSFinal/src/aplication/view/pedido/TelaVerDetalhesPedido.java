
package aplication.view.pedido;

import aplication.Exceptions.BDException;
import aplication.dao.AluguelDAO;
import aplication.dao.MultaDAO;
import aplication.dao.PedidoDAO;
import aplication.dao.ProdutoDAO;
import aplication.modelo.Aluguel;
import aplication.modelo.Multa;
import aplication.modelo.Produto;
import aplication.modelo.Status;
import aplication.view.multa.TelaFormularioMulta;
import aplication.view.multa.TelaPesquisarMulta;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Erick
 */
public class TelaVerDetalhesPedido extends javax.swing.JFrame {

    private Aluguel aluguel;
    
    public TelaVerDetalhesPedido(Aluguel alugel) {
        initComponents();
        
        this.aluguel = alugel;
        
        setar();
        
        Status status = this.aluguel.getStatus();
        
        botaoAlugar.setEnabled(status.getId() == Aluguel.PEDIDO ? true : false);
        
        ativaBotoes();
    }
    
    //Ativa botoes de devolução e multa de acordo com o status do aluguel
    public void ativaBotoes(){
        if ( aluguel.getStatus().getId().equals(Aluguel.PEDIDO)){
            botaoDevolucao.setVisible(false);
        } else if ( ! aluguel.getStatus().getId().equals(Aluguel.ALUGADO)){
            botaoDevolucao.setText("Ver Multas");
        }
    }
    
    private void carregaImagem(String caminho, String nomeImagem){
        ImageIcon icon = new ImageIcon(caminho);

        Rectangle rectangle = labelImagem.getBounds();

        Image image = icon.getImage().getScaledInstance(rectangle.width, rectangle.height, Image.SCALE_DEFAULT);
        icon = new ImageIcon(image);

        labelImagem.setIcon(icon);
    }

    private void setar(){        
        labelNome.setText(aluguel.getProduto().getNome());
        labelAluguel.setText(aluguel.getProduto().getPrecoAluguel()+"");
        labelAQtde.setText(aluguel.getQuantidade()+"");
        labelGrupoProduto.setText(aluguel.getProduto().getGrupoProduto().getDescricao());
        labelId.setText(aluguel.getProduto().getId()+"");
        labelSaldo.setText(aluguel.getProduto().getSaldo()+"");
        labelCliente.setText(aluguel.getCliente().getNome());
        labelTempo.setText(aluguel.getTempo() + "");
        
        File file = new File("");
        String caminho = file.getAbsolutePath() + "/src/" + aluguel.getProduto().getImagem();
        String nomeImagem = aluguel.getProduto().getImagem().replaceAll("img/", "");
        carregaImagem(caminho, nomeImagem);
    }
    
    //abre a janela de multas quando o aluguel já foi parcialmente ou totalmente finalizado
    private void verMultas() throws BDException{
        TelaPesquisarMulta telaPesquisaMulta = new TelaPesquisarMulta(aluguel,this);
        telaPesquisaMulta.setVisible(true);
    }
    
    private int estaAtrasado(){
        int horaInicio = aluguel.getDtAluguel().get(Calendar.HOUR_OF_DAY);
        int minutoInicio = aluguel.getDtAluguel().get(Calendar.MINUTE);
        int horaAtual = Calendar.getInstance().get((Calendar.HOUR_OF_DAY));
        int minutoAtual = Calendar.getInstance().get((Calendar.MINUTE));
        int tempoLocacao = aluguel.getTempo();
        int horaLimite = horaInicio + tempoLocacao;
        int minutoLimite = minutoInicio + 20;
        
        if(minutoLimite > 59){
            horaLimite += 1;
            minutoLimite = minutoLimite - 60;
            //Calendar hora Calendar.getInstance().set(ERROR, WIDTH, WIDTH, horaAtual, WIDTH);
        }
        
        System.out.println("hora ini " + horaInicio );
        System.out.println("Minuto ini " + minutoInicio);
        
        if (horaAtual < horaLimite){
            return 0;
        }else if( horaAtual == horaLimite && minutoAtual <= minutoLimite){
            return 0;
        } else if (horaAtual == horaLimite){
            return 1;
        } else {
            return horaAtual - horaLimite;
        }
    }
    
    private boolean temMulta() throws BDException{
        MultaDAO multaDAO = new MultaDAO();
        List<Multa> multas = multaDAO.pesquisar(aluguel);
        
        if (multas.size() > 0){
            return true;
        }
        return false;
    }
    
    //Verifica se alguma multa deve ser aplicada antes de finalizar
    private void devolucao() throws BDException{
        int tempoDeAtraso = estaAtrasado();
        if(temMulta()){
            TelaPesquisarMulta telaPesquisaMulta = new TelaPesquisarMulta(aluguel, this);
            telaPesquisaMulta.setVisible(true);
        }else if (tempoDeAtraso > 0){
            TelaPesquisarMulta telaPesquisaMulta = new TelaPesquisarMulta(aluguel, this);
            telaPesquisaMulta.setVisible(true);

            new TelaFormularioMulta(aluguel, telaPesquisaMulta, tempoDeAtraso);
        }else{
            int escolha = JOptionPane.showConfirmDialog(null, "Houve extravio de produtos ou há avarias em algum produto?");
        
            if (escolha == 1){
                Status statusAluguel = new Status();
                statusAluguel.setId(Aluguel.FINALIZADO);
                updateStatusAluguel(statusAluguel);
                ativaBotoes();
            } else if (escolha == 0){
                TelaPesquisarMulta telaPesquisaMulta = new TelaPesquisarMulta(aluguel, this);
                telaPesquisaMulta.setVisible(true);
            }
        }
    }
    
    private void updateStatusAluguel(Status status){
        AluguelDAO dao = new AluguelDAO();
        aluguel.setStatus(status);
        dao.alterar(aluguel);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoAlugar = new javax.swing.JButton();
        botaoCancelar3 = new javax.swing.JButton();
        botaoDevolucao = new javax.swing.JButton();
        labelAluguel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelAQtde = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelSaldo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelGrupoProduto = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panelImagem = new javax.swing.JPanel();
        labelImagem = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelGrupoProduto1 = new javax.swing.JLabel();
        labelCaminho = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelCliente = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelTempo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Nome:");

        labelNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menus de Opções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        botaoAlugar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoAlugar.setText("Alugar");
        botaoAlugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlugarActionPerformed(evt);
            }
        });

        botaoCancelar3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        botaoCancelar3.setText("Cancelar");
        botaoCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelar3ActionPerformed(evt);
            }
        });

        botaoDevolucao.setText("Devolução");
        botaoDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDevolucaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoAlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoAlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(botaoDevolucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        labelAluguel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Preço Aluguel:");

        labelAQtde.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Quantidade:");

        labelSaldo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Saldo:");

        labelGrupoProduto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Grupo Produto:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imagem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelImagemLayout = new javax.swing.GroupLayout(panelImagem);
        panelImagem.setLayout(panelImagemLayout);
        panelImagemLayout.setHorizontalGroup(
            panelImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        );
        panelImagemLayout.setVerticalGroup(
            panelImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImagemLayout.createSequentialGroup()
                .addComponent(labelImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Caminho:");

        labelGrupoProduto1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelCaminho.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelGrupoProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel9)
                .addGap(9, 9, 9)
                .addComponent(labelCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelGrupoProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(panelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("ID:");

        labelId.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Cliente:");

        labelCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Tempo:");

        labelTempo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labelAQtde, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                .addComponent(labelNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAluguel, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelGrupoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelAluguel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelAQtde, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelGrupoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoAlugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlugarActionPerformed
        alugar();
    }//GEN-LAST:event_botaoAlugarActionPerformed

    private void alugar(){
        Produto produto = aluguel.getProduto();
        Double contaSaldo = produto.getSaldo() - aluguel.getQuantidade(); 
        
        if (contaSaldo > 0 ){            
            Status status = aluguel.getStatus();
            status.setId(Long.parseLong("2"));

            produto.setSaldo(contaSaldo);
            
            aluguel.setStatus(status);

            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.finalizaPedido(aluguel);
            
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.alteraStatus(produto);
            JOptionPane.showMessageDialog(this, "Produto Alugado!!");
        }else {
           JOptionPane.showMessageDialog(null," Saldo <0");
        }
    }
    private void botaoCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelar3ActionPerformed
        dispose();
    }//GEN-LAST:event_botaoCancelar3ActionPerformed

    private void botaoDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDevolucaoActionPerformed
        try {
            if (botaoDevolucao.getText().equals("Devolução")){
                devolucao();
            }else{
                verMultas();
            }
        } catch (BDException ex) {
            
        }
    }//GEN-LAST:event_botaoDevolucaoActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlugar;
    private javax.swing.JButton botaoCancelar3;
    private javax.swing.JButton botaoDevolucao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelAQtde;
    private javax.swing.JLabel labelAluguel;
    private javax.swing.JLabel labelCaminho;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelGrupoProduto;
    private javax.swing.JLabel labelGrupoProduto1;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelImagem;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelSaldo;
    private javax.swing.JLabel labelTempo;
    private javax.swing.JPanel panelImagem;
    // End of variables declaration//GEN-END:variables
}
