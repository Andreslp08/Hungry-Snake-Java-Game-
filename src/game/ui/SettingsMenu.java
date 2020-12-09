package game.ui;

import game.ui.components.GameButton;
import game.main.GameManagment;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class SettingsMenu extends JPanel {

    public static SettingsButton returnOption, screenOption, soundOption, gameControls, playerOption;
    public static JPanel optionsPanel;
    public static JLayeredPane sectionContainer;
    public static JLabel sectionTitle;
    public static ScreenSettings screenSettings;
    public static final int OPTION_PANEL_HEIGHT = 150;
    public static SoundSettings soundSettings;

    public SettingsMenu() {
        // this
        this.setBounds(0, 0, ScreenManagment.WIDTH, ScreenManagment.HEIGHT);
        this.setLayout(new BorderLayout());
        // options panel
        optionsPanel = new JPanel();
        optionsPanel.setPreferredSize(new Dimension(ScreenManagment.WIDTH, OPTION_PANEL_HEIGHT));
        optionsPanel.setLayout(new GridBagLayout());
        this.add(optionsPanel, BorderLayout.NORTH);
        GridBagConstraints optionPanelConstraints = new GridBagConstraints();
        optionPanelConstraints.insets = new Insets(10, 10, 10, 10);
        // options
        returnOption = new SettingsButton("Return to menu", "game/assets/images/settingsOptions/return.png");
        optionsPanel.add(returnOption, optionPanelConstraints);
        screenOption = new SettingsButton("Screen", "game/assets/images/settingsOptions/Screen.png");
        optionsPanel.add(screenOption, optionPanelConstraints);
        soundOption = new SettingsButton("Sound", "game/assets/images/settingsOptions/sound.png");
        optionsPanel.add(soundOption, optionPanelConstraints);
        gameControls = new SettingsButton("Game controls", "game/assets/images/settingsOptions/controls.png");
        optionsPanel.add(gameControls, optionPanelConstraints);
        playerOption = new SettingsButton("Player settings", "game/assets/images/settingsOptions/player.png");
        optionsPanel.add(playerOption, optionPanelConstraints);
        //section title
        optionPanelConstraints.insets = new Insets(5, 5, 5, 5);
        optionPanelConstraints.gridy = 1;
        optionPanelConstraints.gridwidth = 5;
        sectionTitle = new JLabel("Section");
        sectionTitle.setFont(new Font("Impact", 0, 35));
        optionsPanel.add(sectionTitle, optionPanelConstraints);
        // section container
        sectionContainer = new JLayeredPane();
        this.add(sectionContainer, BorderLayout.CENTER);
        // sections
        screenSettings = new ScreenSettings();
        screenSettings.setSize(ScreenManagment.WIDTH, ScreenManagment.HEIGHT - OPTION_PANEL_HEIGHT);
        sectionContainer.add(screenSettings, Integer.valueOf(1));
        soundSettings = new SoundSettings();
        soundSettings.setSize(ScreenManagment.WIDTH, ScreenManagment.HEIGHT - OPTION_PANEL_HEIGHT);
        sectionContainer.add(soundSettings, Integer.valueOf(2));
        // handler
        returnToMenuHandler();
        openSectionHandler();
        showDefaultSection();
        selectOptionHandler();
    }

    public void selectOptionHandler() {
        screenOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                hideSections();
                screenSettings.setVisible(true);
            }
        });
        soundOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                hideSections();
                soundSettings.setVisible(true);

            }
        });
        gameControls.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                hideSections();

            }
        });
        playerOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                hideSections();

            }
        });
    }

    public void hideSections() {
        screenSettings.setVisible(false);
        soundSettings.setVisible(false);
    }

    public void returnToMenuHandler() {
        returnOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                GameManagment.menu.setVisible(true);
                setVisible(false);
                showDefaultSection();
            }

        });
    }

    public void showDefaultSection() {
         hideSections();
         screenSettings.setVisible(true);
        sectionTitle.setText("Screen");
    }

    public void openSectionHandler() {
        screenOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                sectionTitle.setText("Screen");
            }
        });
        soundOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                sectionTitle.setText("Sound");
            }
        });
        gameControls.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                sectionTitle.setText("Game controls");
            }
        });
        playerOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                sectionTitle.setText("Player Settings");
            }
        });
    }

    //---------------------------------------------------------------------------------------------
    // button class
    public class SettingsButton extends GameButton {

        private ImageIcon icon;

        public SettingsButton(String text, String iconPath) {
            super(text);
            this.setText("");
            this.setPreferredSize(new Dimension(60, 60));
            this.setRadius(60);
            this.setToolTipText("<html><div style='margin:-5 -5 -5 -5; padding: 10px;  border-radius: 20px; outline:none; background-color:#222222; color:#ffffff;'>" + text + "</div></html>");
            icon = new ImageIcon(getClass().getClassLoader().getResource(iconPath));
            Image imageScaled = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            icon = new ImageIcon(imageScaled);
            this.setIcon(icon);
        }
    }

}
