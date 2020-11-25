package game.ui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.accessibility.AccessibleContext;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class GameWindow extends JFrame {

    private static JLayeredPane layers;

    public GameWindow() {
        initComponents();
    }

    public void initComponents() {
        // layer
        layers = new JLayeredPane();
        layers.setSize(new Dimension(ScreenManagment.WIDTH, ScreenManagment.HEIGHT));
        layers.setPreferredSize(new Dimension(ScreenManagment.WIDTH, ScreenManagment.HEIGHT));
//        LayoutManager overlay = new OverlayLayout(layers);
//        layers.setLayout(overlay);
        this.add(layers);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.pack();
    }

    //GETTERS & SETTERS
    public JLayeredPane getLayers() {
        return layers;
    }

    public JRootPane getRootPane() {
        return rootPane;
    }

}
