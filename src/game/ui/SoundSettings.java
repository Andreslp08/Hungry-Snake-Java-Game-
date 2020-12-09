package game.ui;

import game.databases.GameDB;
import game.ui.components.GameButton;
import game.ui.components.GameOptionBox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SoundSettings extends JPanel {

    public static GameButton saveButton;
    public static JLabel musicLabel, gameLabel;
    public static JSlider musicSlider, gameSlider;
    public GameDB gameDB;

    public SoundSettings() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        //options
        constraints.weightx = 400;
        constraints.gridy = 0;
        musicLabel = new JLabel("Music");
        musicLabel.setFont(new Font("Impact", 0, 20));
        this.add(musicLabel, constraints);
        constraints.gridy = 1;
        musicSlider = new JSlider(0, 100, 100);
        musicSlider.setForeground(new Color(0, 255, 0, 255));
        musicSlider.setMajorTickSpacing(10);
        musicSlider.setPaintTicks(true);
        this.add(musicSlider, constraints);
        constraints.gridy = 2;
        gameLabel = new JLabel("Game");
        gameLabel.setFont(new Font("Impact", 0, 20));
        this.add(gameLabel, constraints);
        constraints.gridy = 3;
        gameSlider = new JSlider(0, 100, 100);
        gameSlider.setMajorTickSpacing(10);
        gameSlider.setPaintTicks(true);
        this.add(gameSlider, constraints);
        //button to save
        constraints.gridy = 4;
        constraints.insets = new Insets(30, 30, 30, 30);
        saveButton = new GameButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 40));
        saveButton.setFont(new Font("Impact", 2, 20));
        saveButton.setForeground(new Color(0, 0, 0, 255));
        this.add(saveButton, constraints);
        // connect to database
        gameDB = new GameDB();
        // load the config
        //show current
        showCurrentVolume();
        // load the changes
        loadChanges();
        gameDB.connect();
        // method to save changes
        saveChanges();

    }

    public void loadChanges() {

    }

    public void showCurrentVolume() {
        gameSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gameLabel.setText("Game: " + gameSlider.getValue() + "%");
            }
        });
        musicSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                musicLabel.setText("Music: " + musicSlider.getValue() + "%");
            }
        });

    }

    public void saveChanges() {
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.

            }

        });
    }

}
