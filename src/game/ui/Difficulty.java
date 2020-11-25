package game.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Difficulty extends JPanel {

    private JLabel title, titleDifficulty;
    private GameButton easyButton, normalButton, hardButton;

    public Difficulty() {
          this.setBounds(0, 0, ScreenManagment.WIDTH, ScreenManagment.HEIGHT);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // title
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 2;
        constraints.gridy = 0;
        title = new JLabel("Snake Game");
        title.setFont(new Font("Arial", 3, 80));
        this.add(title, constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        titleDifficulty = new JLabel("Difficulty");
        titleDifficulty.setFont(new Font("Arial", 3, 50));
        this.add(titleDifficulty, constraints);
        // start button
        constraints.insets = new Insets(20, 20, 20, 20);
        easyButton = new GameButton("Easy");
        easyButton.setPreferredSize(new Dimension(250, 60));
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(20, 20, 20, 20);
        this.add(easyButton, constraints);
        // settings button
        normalButton = new GameButton("Normal");
        normalButton.setPreferredSize(new Dimension(250, 60));
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.add(normalButton, constraints);
        // exit button
        hardButton = new GameButton("Hard");
        hardButton.setPreferredSize(new Dimension(250, 60));
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.add(hardButton, constraints);
    }

    //GETTERS AND SETTERS

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JLabel getTitleDifficulty() {
        return titleDifficulty;
    }

    public void setTitleDifficulty(JLabel titleDifficulty) {
        this.titleDifficulty = titleDifficulty;
    }

    public GameButton getEasyButton() {
        return easyButton;
    }

    public void setEasyButton(GameButton easyButton) {
        this.easyButton = easyButton;
    }

    public GameButton getNormalButton() {
        return normalButton;
    }

    public void setNormalButton(GameButton normalButton) {
        this.normalButton = normalButton;
    }

    public GameButton getHardButton() {
        return hardButton;
    }

    public void setHardButton(GameButton hardButton) {
        this.hardButton = hardButton;
    }


    
}
