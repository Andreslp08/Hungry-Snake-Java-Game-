package game.controller;

import game.characters.Element;
import game.characters.Snake;
import game.main.GameManagment;
import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeInputHandler implements KeyListener {

    public static Canvas canvas;
    public static int snakeUp = KeyEvent.VK_W, snakeDown = KeyEvent.VK_S, snakeLeft = KeyEvent.VK_A, snakeRight = KeyEvent.VK_D;
    public static int pauseKey = KeyEvent.VK_P;

    public static GameManagment gameManagment;

    public SnakeInputHandler(GameManagment gameManagment) {
        this.gameManagment = gameManagment;
    }

    private void snakeHandler(KeyEvent e) {
        if (e.getKeyCode() == snakeUp) {
            if (!Snake.direction.equals(Snake.SNAKE_DIRECTION.DOWN)) {
                Snake.direction = Snake.SNAKE_DIRECTION.UP;
            }

        } else if (e.getKeyCode() == snakeDown) {
            if (!Snake.direction.equals(Snake.SNAKE_DIRECTION.UP)) {
                Snake.direction = Snake.SNAKE_DIRECTION.DOWN;
            }
        } else if (e.getKeyCode() == snakeLeft) {
            if (!Snake.direction.equals(Snake.SNAKE_DIRECTION.RIGHT)) {
                Snake.direction = Snake.SNAKE_DIRECTION.LEFT;
            }
        } else if (e.getKeyCode() == snakeRight) {
            if (!Snake.direction.equals(Snake.SNAKE_DIRECTION.LEFT)) {
                Snake.direction = Snake.SNAKE_DIRECTION.RIGHT;
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
