/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.renderizador;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author pc
 */
public class jPanelRederer extends JList implements TableCellRenderer {
  

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
             
                if (value instanceof List) {
	                  DefaultListModel model = new DefaultListModel();
                          List<String> d = (List) value;
                           for (String s : d) {
                                this.setModel(model);
                                model.addElement(s);
                           }
                         
                           this.setModel(model);
                           
	         }
          return this;
    }
    
    
}
