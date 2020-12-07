package game.ui;

import game.main.GameManagment;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Menu extends JPanel {

    private JLabel title;
    private GameButton startButton, configButton, exitButton;

    public Menu() {
        this.setBounds(0, 0, ScreenManagment.WIDTH, ScreenManagment.HEIGHT);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // title
        constraints.insets = new Insets(30, 30, 30, 30);
        constraints.gridx = 2;
        constraints.gridy = 0;
        title = new JLabel(GameManagment.gameTitle);
        title.setFont(new Font("Arial", 3, 80));
        this.add(title, constraints);
        // start button
         constraints.insets = new Insets(20, 20, 20, 20);
        startButton = new GameButton("Start Game");
        startButton.setPreferredSize(new Dimension(250, 60));
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(20, 20, 20, 20);
        this.add(startButton, constraints);
        // settings button
        configButton = new GameButton("Settings");
        configButton.setPreferredSize(new Dimension(250, 60));
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.add(configButton, constraints);
        // exit button
        exitButton = new GameButton("Exit");
        exitButton.setPreferredSize(new Dimension(250, 60));
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.add(exitButton, constraints);
    }

    //GETTERS AND SETTERS
    public GameButton getStartButton() {
        return startButton;
    }

    public void setStartButton(GameButton startButton) {
        this.startButton = startButton;
    }

    public GameButton getConfigButton() {
        return configButton;
    }

    public void setConfigButton(GameButton configButton) {
        this.configButton = configButton;
    }

    public GameButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(GameButton exitButton) {
        this.exitButton = exitButton;
    }

}
