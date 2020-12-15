package game.controller;

import game.characters.Element;
import game.characters.Snake;
import game.main.Delay;
import game.main.GameManagment;
import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeInputHandler implements KeyListener {

    public static Canvas canvas;
    public static GameManagment gameManagment;
    public static int DEFAULT_SNAKE_UP = KeyEvent.VK_W, DEFAULT_SNAKE_DOWN = KeyEvent.VK_S, DEFAULT_SNAKE_LEFT = KeyEvent.VK_A, DEFAULT_SNAKE_RIGHT = KeyEvent.VK_D;
    public static int SNAKE_UP, SNAKE_DOWN, SNAKE_LEFT, SNAKE_RIGHT;
    public static Delay movementDelay;

    public SnakeInputHandler(GameManagment gameManagment) {
        this.gameManagment = gameManagment;  
    }
    
    private void snakeHandler(KeyEvent e) {
        if (Snake.direction != null) {
            if (e.getKeyCode() == SNAKE_UP) {
                if (!Snake.direction.equals(Snake.SNAKE_DIRECTION.DOWN)) {
                    Snake.direction = Snake.SNAKE_DIRECTION.UP;
                }

            } else if (e.getKeyCode() == SNAKE_DOWN) {
                if (!Snake.direction.equals(Snake.SNAKE_DIRECTION.UP)) {
                    Snake.direction = Snake.SNAKE_DIRECTION.DOWN;
                }
            } else if (e.getKeyCode() == SNAKE_LEFT) {
                if (!Snake.direction.equals(Snake.SNAKE_DIRECTION.RIGHT)) {
                    Snake.direction = Snake.SNAKE_DIRECTION.LEFT;
                }
            } else if (e.getKeyCode() == SNAKE_RIGHT) {
                if (!Snake.direction.equals(Snake.SNAKE_DIRECTION.LEFT)) {
                    Snake.direction = Snake.SNAKE_DIRECTION.RIGHT;
                }
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        snakeHandler(e);
    }

    @Override
    public void keyReleased(KeyEvent e
    ) {

    }
}
