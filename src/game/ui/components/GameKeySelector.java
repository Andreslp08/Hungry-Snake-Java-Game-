package game.ui.components;

import game.main.GameManagment;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.OptionalInt;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameKeySelector extends JPanel {

    private JLabel titleOptionLabel;
    private GameButtonKey gameButtonKey;
    private String title;
    private String option;
    private int key = 0;
    private int optionIndex;
    public final String ENTER_TEXT = "'Enter a key'";

    public GameKeySelector(String title, String option) {
        this.title = title;
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        // label
        constraints.ipadx = 100;
        titleOptionLabel = new JLabel(this.title);
        titleOptionLabel.setFont(new Font("Impact", 0, 20));
        titleOptionLabel.setPreferredSize(new Dimension(200, 30));
        this.add(titleOptionLabel, constraints);
        // field
        constraints.ipadx = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        gameButtonKey = new GameButtonKey(option, this);
        gameButtonKey.setPreferredSize(new Dimension(150, 40));
        this.add(gameButtonKey, constraints);
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

    //--------------------------------------------------------------------
    public class GameButtonKey extends GameButton {

        private GameKeyHandler gameKeyHandler;
        private GameKeySelector gameKeySelector;

        public GameButtonKey(String text, GameKeySelector gameKeySelector) {
            super(text);
            this.setFont(new Font("Arial Narrow", 1, 20));
            this.setRadius(0);
            this.gameKeySelector = gameKeySelector;
            gameKeyHandler = new GameKeyHandler(this);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            setText(ENTER_TEXT);
            setFocusable(true);
            requestFocus();
            addKeyListener(gameKeyHandler);

        }

        public GameKeySelector getGameKeySelector() {
            return gameKeySelector;
        }

        //---------------------------------------------------------------------------------------
        public class GameKeyHandler implements KeyListener {

            private GameButtonKey gameButtonKey;

            public GameKeyHandler(GameButtonKey gameButtonKey) {
                this.gameButtonKey = gameButtonKey;
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                gameButtonKey.getGameKeySelector().setKey(e.getKeyCode());
                gameButtonKey.setText(e.getKeyText(e.getKeyCode()));
                gameButtonKey.removeKeyListener(this);
            }

        }

    }
}
