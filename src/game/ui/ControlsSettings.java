package game.ui;

import game.databases.GameDB;
import game.main.GameManagment;
import static game.main.GameManagment.pauseMenuInputHandler;
import static game.main.GameManagment.snakeInputHandler;
import game.main.SoundManagment;
import game.ui.components.GameButton;
import game.ui.components.GameKeySelector;
import game.ui.components.GameOptionBox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
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

public class ControlsSettings extends JPanel {

    public static GameButton saveButton;
    public static GameKeySelector snakeUp, snakeDown, snakeRight, snakeLeft, pauseMenu;
    public GameDB gameDB;

    public ControlsSettings() {
        // connect to database
        gameDB = new GameDB();
        gameDB.connect();
        updateInputHandler();
        // this
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        //options
        constraints.weightx = 400;
        constraints.gridy = 0;
        snakeUp = new GameKeySelector("Snake up", KeyEvent.getKeyText(snakeInputHandler.SNAKE_UP )+ "");
        this.add(snakeUp, constraints);
        constraints.gridy = 1;
        snakeDown = new GameKeySelector("snake down", KeyEvent.getKeyText(snakeInputHandler.SNAKE_DOWN )+ "");
        this.add(snakeDown, constraints);
        constraints.gridy = 2;
        snakeLeft = new GameKeySelector("snake left", KeyEvent.getKeyText(snakeInputHandler.SNAKE_LEFT )+ "");
        this.add(snakeLeft, constraints);
        constraints.gridy = 3;
        snakeRight = new GameKeySelector("snake right", KeyEvent.getKeyText(snakeInputHandler.SNAKE_RIGHT )+ "");
        this.add(snakeRight, constraints);
        constraints.gridy = 4;
        pauseMenu = new GameKeySelector("Pause game", KeyEvent.getKeyText(pauseMenuInputHandler.PAUSE_KEY )+ "");
        this.add(pauseMenu, constraints);
        //button to save
        constraints.gridy = 5;
        constraints.insets = new Insets(30, 30, 30, 30);
        saveButton = new GameButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 40));
        saveButton.setFont(new Font("Impact", 2, 20));
        saveButton.setForeground(new Color(0, 0, 0, 255));
        this.add(saveButton, constraints);
        //show current
        // method to save changes
        saveChanges();

    }

    public void updateInputHandler() {
        snakeInputHandler.SNAKE_UP = gameDB.getKey(GameDB.UP_CONTROL_ATTR);
        snakeInputHandler.SNAKE_DOWN = gameDB.getKey(GameDB.DOWN_CONTROL_ATTR);
        snakeInputHandler.SNAKE_LEFT = gameDB.getKey(GameDB.LEFT_CONTROL_ATTR);
        snakeInputHandler.SNAKE_RIGHT = gameDB.getKey(GameDB.RIGHT_CONTROL_ATTR);
        pauseMenuInputHandler.PAUSE_KEY = gameDB.getKey(GameDB.PAUSE_CONTROL_ATTR);
    }

    public void saveChanges() {
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                gameDB.updateControlsTable(snakeUp.getKey(), snakeDown.getKey(), snakeLeft.getKey(), snakeRight.getKey(), pauseMenu.getKey());
                updateInputHandler();
            }

        });
    }

}
