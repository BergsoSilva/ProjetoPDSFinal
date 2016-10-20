/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.renderizador;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pc
 */
public class MyTalbeModel  extends AbstractTableModel{
    
    public Object getValueAt(int row, int column) {
          return "" + (row * column);
      }

     public int getColumnCount() {
         return 4;
    }

     public int getRowCount() {
         return 5;
    }
    
}
