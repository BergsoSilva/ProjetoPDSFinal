/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.eventos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author unkwow
 */
public class JTableButtonMuseListener implements MouseListener {
    
    private JTable tabela;
    
    public void eventoParaButton(MouseEvent e){
        
            TableColumnModel columnModel = tabela.getColumnModel();
            
            int column = columnModel.getColumnIndexAtX(e.getX());
            
            int row    = e.getY() / tabela.getRowHeight();
            
            Object value;
            JButton button;
            MouseEvent buttonEvent;
            
            /* Caso não seja  */
            if(row >= tabela.getRowCount() || row < 0 ||
               column >= tabela.getColumnCount() || column < 0)
              return;
            
            /* Obter objeto da linha clicada */
            value = tabela.getValueAt(row, column);
      
            if(!(value instanceof JButton))
              return;
            
            button = (JButton)value;
            buttonEvent = (MouseEvent)SwingUtilities.convertMouseEvent(tabela, e, button);
            
            button.dispatchEvent(buttonEvent);
            // This is necessary so that when a button is pressed and released
            // it gets rendered properly.  Otherwise, the button may still appear
            // pressed down when it has been released.
            tabela.repaint();
    }

    public JTableButtonMuseListener(JTable tabela) {
        this.tabela = tabela;
    }

    

    @Override
    public void mouseClicked(MouseEvent e) {
        eventoParaButton(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        eventoParaButton(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        eventoParaButton(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
         eventoParaButton(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        eventoParaButton(e);
    }
    
}
