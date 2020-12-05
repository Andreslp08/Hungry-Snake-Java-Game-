package game.ui;

import game.main.Game;
import game.main.GameManagment;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.*;
import sun.java2d.ScreenUpdateManager;

public class Hud extends JPanel {

    private int score;
    private JLabel scoreTitle, scoreLabel, timeTitle, timeLabel, healthTitle, healthLabel;
    private JPanel hudContainer;
    
    public Hud() {
        this.setBounds(0, 0, ScreenManagment.WIDTH, ScreenManagment.HUD_HEIGHT);
        this.setLayout(new BorderLayout());
        hudContainer = new JPanel();
        hudContainer.setBackground( new Color(20,20,20,255));
        hudContainer.setLayout(new GridBagLayout());
        this.add(hudContainer, BorderLayout.CENTER);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 0, 0);
        // score
        scoreTitle = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 2;
        scoreTitle.setText("Score");
        scoreTitle.setFont(new Font("Arial Narrow", 1, 22));
        scoreTitle.setForeground(new Color(255, 255, 255, 255));
        hudContainer.add(scoreTitle, constraints);
        scoreLabel = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 1;
        scoreLabel.setText("0");
        scoreLabel.setFont(new Font("Arial Narrow", 0, 20));
        scoreLabel.setForeground(new Color(255, 255, 255, 255));
        hudContainer.add(scoreLabel, constraints);
        // time
        timeTitle = new JLabel();
        constraints.gridx = 1;
        constraints.gridy = 0;
        timeTitle.setText("Time");
        timeTitle.setFont(new Font("Arial Narrow", 1, 22));
        timeTitle.setForeground(new Color(255, 255, 255, 255));
        hudContainer.add(timeTitle, constraints);
        timeLabel = new JLabel();
        constraints.gridx = 1;
        constraints.gridy = 1;
        timeLabel.setText("0");
        timeLabel.setFont(new Font("Arial Narrow", 0, 20));
        timeLabel.setForeground(new Color(255, 255, 255, 255));
        hudContainer.add(timeLabel, constraints);
        // health
        healthTitle = new JLabel();
        constraints.gridx = 2;
        constraints.gridy = 0;
        healthTitle.setText("Health");
        healthTitle.setFont(new Font("Arial Narrow", 1, 22));
        healthTitle.setForeground(new Color(255, 255, 255, 255));
        hudContainer.add(healthTitle, constraints);
        healthLabel = new JLabel();
        constraints.gridx = 2;
        constraints.gridy = 1;
        healthLabel.setText("100");
        healthLabel.setFont(new Font("Arial Narrow", 0, 20));
        healthLabel.setForeground(new Color(255, 255, 255, 255));
        hudContainer.add(healthLabel, constraints);

    }

    public void updateScore( int score ) {
        this.score = score;
        scoreLabel.setText(score+"");
        
    }
    
    public void reset(){
        score = 0;
        scoreLabel.setText(score+"");
    }
}
