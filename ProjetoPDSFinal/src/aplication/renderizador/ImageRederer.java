/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.renderizador;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author pc
 */
public class ImageRederer extends DefaultTableCellRenderer {
     JLabel lbl = new JLabel();
     ImageIcon icon = new ImageIcon(getClass().getResource("/img/2.jpg"));

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column) {
        lbl.setText((String) value);
        lbl.setIcon(icon);
       return lbl;
  }
}
