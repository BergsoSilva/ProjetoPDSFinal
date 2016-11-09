/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.renderizador;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author pc
 */
public class jPanelRederer extends JPanel implements TableCellRenderer {
  

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
               setBounds(0, 0,10, 10);
               setVisible(true);
               setLayout(new FlowLayout());
                if (value instanceof JLabel) {
	            if (value != null) {
                          JLabel d = (JLabel) value;
                          add(d);
	            } else {
	               add(new JLabel("Default"));
	            }
	        }
          return this;
    }
    
    
}
