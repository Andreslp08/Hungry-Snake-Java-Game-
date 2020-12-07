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

public class GameOver extends JPanel {

    private JLabel title, titleGameOver;
    private GameButton RetryButton, menuButton, exitButton;

    public GameOver() {
          this.setBounds(0, 0, ScreenManagment.WIDTH, ScreenManagment.HEIGHT);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // title
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 2;
        constraints.gridy = 0;
        title = new JLabel(GameManagment.gameTitle);
        title.setFont(new Font("Arial", 3, 80));
        this.add(title, constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        titleGameOver = new JLabel("Game Over!");
        titleGameOver.setFont(new Font("Arial", 3, 50));
        this.add(titleGameOver, constraints);
        // start button
        constraints.insets = new Insets(20, 20, 20, 20);
        RetryButton = new GameButton("Retry");
        RetryButton.setPreferredSize(new Dimension(250, 60));
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(20, 20, 20, 20);
        this.add(RetryButton, constraints);
        // settings button
        menuButton = new GameButton("Menu");
        menuButton.setPreferredSize(new Dimension(250, 60));
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.add(menuButton, constraints);
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

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JLabel getTitleGameOver() {
        return titleGameOver;
    }

    public void setTitleGameOver(JLabel titleGameOver) {
        this.titleGameOver = titleGameOver;
    }

    public GameButton getRetryButton() {
        return RetryButton;
    }

    public void setRetryButton(GameButton RetryButton) {
        this.RetryButton = RetryButton;
    }

    public GameButton getMenuButton() {
        return menuButton;
    }

    public void setMenuButton(GameButton menuButton) {
        this.menuButton = menuButton;
    }

    public GameButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(GameButton exitButton) {
        this.exitButton = exitButton;
    }
    
    
}
