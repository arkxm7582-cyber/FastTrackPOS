/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Styles;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;

public class Styles {
    public void styleLabel(javax.swing.JLabel label, Color color, Font font) {
        label.setForeground(color);
        label.setFont(font);
    }
    
    public void styleTextField(javax.swing.JTextField textField) {
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        textField.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(224, 224, 224)),
//            BorderFactory.createEmptyBorder(5, 0, 5, 0)
//        ));
    }
    
    public void styleButton(javax.swing.JButton button, Color bgColor, Color fgColor, String text) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setText(text);
//        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }
    
    public void styleTable(javax.swing.JTable table) {
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setIntercellSpacing(new java.awt.Dimension(0, 0));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        table.getTableHeader().setForeground(new Color(33, 33, 33));
        table.setSelectionBackground(new Color(232, 240, 254));
        table.setSelectionForeground(new Color(33, 33, 33));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
