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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ScreenSettings extends JPanel {

    public static GameButton saveButton;
    public static GameOptionBox screenOption, fpsOption;
    public GameDB gameDB;

    public ScreenSettings() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        //options
        constraints.weightx = 400;
        screenOption = new GameOptionBox("Screen size:", ScreenManagment.SCREEN_OPTIONS_STR);
        screenOption.setDefaultOption(0);
        this.add(screenOption, constraints);
        constraints.gridy = 1;
        fpsOption = new GameOptionBox("FPS:", ScreenManagment.FPS_OPTIONS_STR);
        fpsOption.setDefaultOption(1);
        this.add(fpsOption, constraints);
        //button to save
        constraints.gridy = 2;
        saveButton = new GameButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 40));
        saveButton.setFont(new Font("Impact", 2, 20));
        saveButton.setForeground(new Color(0, 0, 0, 255));
        this.add(saveButton, constraints);
        // connect to database
        gameDB = new GameDB();
        // load the config
        loadChanges();
        gameDB.connect();
        // method to save changes
        saveChanges();
    }

    public void loadChanges() {
        for (int i = 0; i < ScreenManagment.SCREEN_OPTIONS_STR.length; i++) {
            String screen = ScreenManagment.WIDTH + "x" + ScreenManagment.HEIGHT;
            if (screen.equals(ScreenManagment.SCREEN_OPTIONS_STR[i])) {
                screenOption.setDefaultOption(i);
                break;
            }
        }
        for (int i = 0; i < ScreenManagment.FPS_OPTIONS_STR.length; i++) {
            String fps = ScreenManagment.FPS_REQUIRED + " Hz";
            if (fps.equals(ScreenManagment.FPS_OPTIONS_STR[i])) {
                fpsOption.setDefaultOption(i);
                break;
            }

        }
    }

    public void saveChanges() {
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                int width = 0;
                int height = 0;
                int fps = 0;
                for (int i = 0; i < ScreenManagment.SCREEN_OPTIONS_STR.length; i++) {
                    if (screenOption.getSelectedOption().equals(ScreenManagment.SCREEN_OPTIONS_STR[i])) {
                        width = ScreenManagment.SCREEN_OPTIONTS_INT[i][0];
                        height = ScreenManagment.SCREEN_OPTIONTS_INT[i][1];
                        break;
                    }
                }
                for (int i = 0; i < ScreenManagment.FPS_OPTIONS_STR.length; i++) {
                    if (fpsOption.getSelectedOption().equals(ScreenManagment.FPS_OPTIONS_STR[i])) {
                        fps = ScreenManagment.FPS_OPTIONS_INT[i];
                        break;
                    }
                }
                gameDB.updateScreenTable(width, height, fps);
                JOptionPane.showMessageDialog(null, "Please restart the game to see changes.");
            }

        });
    }

}
