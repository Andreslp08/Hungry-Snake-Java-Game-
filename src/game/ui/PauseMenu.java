package game.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

public class PauseMenu extends JPanel {

    private JLabel title;
    private GameButton continueButton, menuButton, exitButton;

    public PauseMenu() {
        this.setBounds(0, 0, ScreenManagment.WIDTH, ScreenManagment.HEIGHT);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(50, 50, 50, 50);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        title = new JLabel("Snake Game");
        title.setFont(new Font("Arial", 3, 80));
        this.add(title, gridBagConstraints);
        gridBagConstraints.insets = new Insets(20, 20, 20, 20);
        gridBagConstraints.gridy = 2;
        continueButton = new GameButton("Continue");
        continueButton.setPreferredSize(new Dimension(250, 60));
        this.add(continueButton, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        menuButton = new GameButton("Menu");
        menuButton.setPreferredSize(new Dimension(250, 60));
        this.add(menuButton, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        exitButton = new GameButton("Exit");
        exitButton.setPreferredSize(new Dimension(250, 60));
        this.add(exitButton, gridBagConstraints);

    }

    // GETTERS AND SETTERS
    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public GameButton getContinueButton() {
        return continueButton;
    }

    public void setContinueButton(GameButton continueButton) {
        this.continueButton = continueButton;
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
