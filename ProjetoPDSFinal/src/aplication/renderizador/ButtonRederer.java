/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.renderizador;

import aplication.dao.ProdutoDAO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author pc
 */
public class ButtonRederer extends DefaultTableCellRenderer {
        JButton btn = new JButton();
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/2.jpg"));
        
  public  Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column) {
        
        btn.setText((String) value);
        btn.setSize(new Dimension(10, 10));
        btn.setIcon(icon);
        
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               acaoButos();
            }
        });
       return btn;
  }
  
  private void acaoButos(){
      JOptionPane.showMessageDialog(null,"Ola aqui funcionou");
  }
}
