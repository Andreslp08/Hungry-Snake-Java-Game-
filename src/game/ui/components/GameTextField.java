/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class GameTextField extends JTextField implements MouseListener {

    private Color backgroundColor;
    private Color borderColor = new Color(0, 255, 0, 255);
    private Color borderEnteredColor = new Color(0, 150, 0, 255);
    private Color borderPressedColor = new Color(0, 100, 0, 255);
    private Color borderFocusedColor = new Color(0, 100, 0, 255);
    private Color borderReleasedColor = new Color(0, 150, 0, 255);
    private Color borderExitedColor = new Color(0, 255, 0, 255);

    public GameTextField() {
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, borderColor));
        this.backgroundColor = new Color(210, 210, 210, 255);
        this.setBackground(backgroundColor);
        this.setFont(new Font("Arial Narrow", 0, 20));
        this.setHorizontalAlignment(CENTER);
        this.setSelectionColor(new Color(0,255,0,255));
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        borderColor = borderPressedColor;
        setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, borderColor));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        borderColor = borderReleasedColor;
        setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, borderColor));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        borderColor = borderEnteredColor;
        setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, borderColor));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        borderColor = borderExitedColor;
        setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, borderColor));

    }

}
