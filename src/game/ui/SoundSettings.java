package game.ui;

import game.databases.GameDB;
import game.main.SoundManagment;
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
    public static JLabel uiLabel, gameLabel;
    public static JSlider uiSlider, gameSlider;
    public GameDB gameDB;

    public SoundSettings() {
        // this
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        //options
        constraints.weightx = 400;
        constraints.gridy = 0;
        uiLabel = new JLabel("UI");
        uiLabel.setFont(new Font("Impact", 0, 20));
        this.add(uiLabel, constraints);
        constraints.gridy = 1;
        uiSlider = new JSlider(0, 100, 100);
        uiSlider.setForeground(new Color(0, 255, 0, 255));
        uiSlider.setMajorTickSpacing(10);
        uiSlider.setPaintTicks(true);
        this.add(uiSlider, constraints);
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
        gameDB.connect();
        //show current
        showCurrentVolume();
        // method to save changes
        saveChanges();

    }



    public void showCurrentVolume() {
        gameLabel.setText("Game: " + SoundManagment.GAME_VOLUME + "%");
        gameSlider.setValue(SoundManagment.GAME_VOLUME);
         uiLabel.setText("UI: " + SoundManagment.UI_VOLUME + "%");
        uiSlider.setValue(SoundManagment.UI_VOLUME);
        
        gameSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gameLabel.setText("Game: " + gameSlider.getValue() + "%");
            }
        });
        uiSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                uiLabel.setText("UI: " + uiSlider.getValue() + "%");
                  SoundManagment.UI_VOLUME = uiSlider.getValue();
                SoundManagment.updateVolume();
            }
        });

    }

    public void saveChanges() {
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                gameDB.updateSoundTable(gameSlider.getValue(), uiSlider.getValue());
            }

        });
    }

}
