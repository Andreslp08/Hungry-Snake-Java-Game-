package game.controller;

import game.characters.Element;
import game.characters.Snake;
import game.main.GameManagment;
import game.ui.PauseMenu;
import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PauseMenuInputHandler implements KeyListener {

    public static Canvas canvas;
    public static int PAUSE_KEY;
    public static int DEFAULT_PAUSE_KEY = KeyEvent.VK_P;
    private static GameManagment gameManagment;
    public PauseMenuInputHandler( GameManagment gameManagment){
        this.gameManagment = gameManagment;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if( e.getKeyCode() == PAUSE_KEY){
            gameManagment.pauseGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e
    ) {

    }
}
