/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication.testesUnitario;

import aplication.renderizador.ImageRederer;
import aplication.renderizador.MyTalbeModel;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author pc
 */
public class ImagemJtable {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());

        MyTalbeModel model = new MyTalbeModel();

        JTable table = new JTable(model);
        table.setRowHeight(80);
        table.getColumnModel().getColumn(0).setCellRenderer( new ImageRederer());
        JScrollPane pane = new JScrollPane(table);
        frame.getContentPane().add(BorderLayout.CENTER, pane);
        frame.setSize(500, 400);
        frame.setVisible(true);
  }
    
}
