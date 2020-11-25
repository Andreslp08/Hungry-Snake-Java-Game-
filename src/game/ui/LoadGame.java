package game.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class LoadGame extends JFrame {

    private static int WIDTH = 500;
    private static int HEIGHT = 600;
    private static JPanel panel;
    private static JLayeredPane loadBarContainer;
    private static JPanel loadBar;
    private static JLabel loadBarPercentage;
    private static int percentage = 0;
    private static int loadBarWidth = 30;

    public LoadGame() {
        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        //panel
        panel = new JPanel();
        panel.setBackground(new Color(20, 20, 20, 255));
        this.add(panel, BorderLayout.CENTER);
        // bar containr
        loadBarContainer = new JLayeredPane();
        loadBarContainer.setOpaque(true);
        loadBarContainer.setBackground(new Color(100, 100, 100, 255));
        loadBarContainer.setPreferredSize(new Dimension(WIDTH, loadBarWidth));
        this.add(loadBarContainer, BorderLayout.SOUTH);
        //loadBar
        loadBar = new JPanel();
        loadBar.setBackground(new Color(255, 255, 255, 255));
        loadBar.setSize(WIDTH, 0);
        loadBarContainer.add(loadBar, new Integer(1));
        // loadBar porcentage
        loadBarPercentage = new JLabel("100%");
        loadBarPercentage.setForeground(new Color(0, 0, 0));
        loadBarPercentage.setSize(WIDTH, loadBarWidth);
        loadBarPercentage.setVerticalAlignment((int) CENTER_ALIGNMENT);
        loadBarPercentage.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        loadBarContainer.add(loadBarPercentage, new Integer(2));
    }

    public boolean load() {
        percentage++;
        int barWidthPercentage = (WIDTH*percentage)/100;
        loadBarPercentage.setText(percentage + "%");
        loadBar.setSize(barWidthPercentage, loadBarWidth);
        if (percentage <= 99) {
            return false;
        } else {
            percentage = 100;
            return true;
        }
    }
    
    public JFrame getFrame(){
        return this;
    }
}
