package game.ui;

import game.main.Game;
import game.main.GameManagment;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

public class Hud extends JPanel {

    private int score;
    private JPanel hudContainer;
    public static int height = 50;

    public Hud() {

        this.setBounds(0, 0, ScreenManagment.WIDTH, ScreenManagment.HEIGHT / 3);
        this.setOpaque(false);
        setBackground(new Color(0,0,0,0));
    }




    
    public void update() {

    }
}
