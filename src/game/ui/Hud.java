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

public class Hud extends JPanel {

    private int score;
    private String difficulty, playerName;
    private JLabel scoreTitle, scoreLabel, difficultyTitle, difficultyLabel, playerNameTitle, playerNameLabel;
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
        difficultyTitle = new JLabel();
        constraints.gridx = 1;
        constraints.gridy = 0;
        difficultyTitle.setText("Difficulty");
        difficultyTitle.setFont(new Font("Arial Narrow", 1, 22));
        difficultyTitle.setForeground(new Color(255, 255, 255, 255));
        hudContainer.add(difficultyTitle, constraints);
        difficultyLabel = new JLabel();
        constraints.gridx = 1;
        constraints.gridy = 1;
        difficultyLabel.setText("0");
        difficultyLabel.setFont(new Font("Arial Narrow", 0, 20));
        difficultyLabel.setForeground(new Color(255, 255, 255, 255));
        hudContainer.add(difficultyLabel, constraints);
        // health
        playerNameTitle = new JLabel();
        constraints.gridx = 2;
        constraints.gridy = 0;
        playerNameTitle.setText("Player");
        playerNameTitle.setFont(new Font("Arial Narrow", 1, 22));
        playerNameTitle.setForeground(new Color(255, 255, 255, 255));
        hudContainer.add(playerNameTitle, constraints);
        playerNameLabel = new JLabel();
        constraints.gridx = 2;
        constraints.gridy = 1;
        playerNameLabel.setText("100");
        playerNameLabel.setFont(new Font("Arial Narrow", 0, 20));
        playerNameLabel.setForeground(new Color(255, 255, 255, 255));
        hudContainer.add(playerNameLabel, constraints);

    }

    public void updateScore( int score ) {
        this.score = score;
        scoreLabel.setText(score+"");
        
    }
    
    public void setDifficulty( String difficulty ){
        this.difficulty = difficulty;
        difficultyLabel.setText(difficulty);
    }
    
    public void setPlayerName( String playerName ){
        this.playerName = playerName;
        playerNameLabel.setText(playerName);
    }
    
    public void reset(){
        score = 0;
        scoreLabel.setText(score+"");
    }
}
