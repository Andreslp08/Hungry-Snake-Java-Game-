package game.main;

import game.characters.Element;
import game.characters.RandomObject;
import game.characters.Snake;
import game.characters.Snake.SNAKE_PARTS;
import game.characters.SnakeBodyPart;
import game.physics.Collision;
import game.ui.GameCanvas;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.accessibility.AccessibleContext;
import javax.swing.*;

public class Game implements Runnable {

    public static Thread mainThread;
    private static boolean isRunning = false;
    private static boolean isPaused = false;
    private static int aps = 0;
    private static int fps = 0;
    private GameCanvas canvas;
    private static BufferStrategy bs;
    private static Graphics2D graphics;
    private GameLevel gameLevel;
    private boolean gameOver = false;
    private int score = 0;

    public Game(GameCanvas canvas, GameLevel.DifficultyMode difficultyMode) {
        this.canvas = canvas;
        gameLevel = new GameLevel(this,difficultyMode);
        score = 0;
    }

    public synchronized void init() {
        isRunning = true;
        mainThread = new Thread(this);
        mainThread.setDaemon(true);
        mainThread.start();
    }

    public synchronized void stop() {
        isPaused = false;
        isRunning = false;
        mainThread.interrupt();
    }

    public void killThread() {
        try {
            mainThread.join();
        } catch (InterruptedException ex) {

        }
    }

    public void clear() {
        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
        graphics = (Graphics2D) bs.getDrawGraphics();
        // clean canvas
        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void snakeCollision() {
        for (int i = 0; i < Element.elementList.size(); i++) {
            if (Element.elementList.get(i).getClass().getSimpleName().equals("SnakeBodyPart")) {
                SnakeBodyPart snakeBodyPart = (SnakeBodyPart) Element.elementList.get(i);
                for (int o = 0; o < Element.elementList.size(); o++) {
                    if (Element.elementList.get(o).getClass().getSimpleName().equals("RandomObject")) {
                        RandomObject randomObject = (RandomObject) Element.elementList.get(o);
                        if (Collision.basicCollision(snakeBodyPart, randomObject) == true) {
                            Element.elementList.remove(o);
                            gameLevel.createRandomObjects();
                            gameLevel.snake.enlarge();
                            score++;
                            System.out.println(score);
                        }
                    }
                    if (Element.elementList.get(o).getClass().getSimpleName().equals("SnakeBodyPart")) {
                        SnakeBodyPart snakeBodyPart2 = (SnakeBodyPart) Element.elementList.get(o);
                        if (snakeBodyPart2.getSnakePart().equals(SNAKE_PARTS.TAIL) || snakeBodyPart2.getSnakePart().equals(SNAKE_PARTS.BODY)) {
                            if (Collision.basicCollision(snakeBodyPart, snakeBodyPart2) == true && snakeBodyPart.getSnakePart().equals(SNAKE_PARTS.HEAD) && !snakeBodyPart.equals(snakeBodyPart2)) {
                                gameOver = true;
                                GameManagment.gameOver.setVisible(true);
                                isRunning = false;
                                System.out.println("Game Over!");
                            }
                        }
                    }
                }
            }

        }
    }

    public void render() {
        gameLevel.renderLevel();
        snakeCollision();
        bs.show();
    }

    @Override
    public void run() {
        int fpsRequeried = 60;
        double tickPerSecond = 1000000000 / fpsRequeried;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long fpsCounter = System.nanoTime();
        while (true) {
            now = System.nanoTime();
            delta += (now - lastTime) / tickPerSecond;
            lastTime = now;
            if (delta >= 1) {
                if (isRunning == true && isPaused == false) {
                    clear();
                    render();
                    delta--;
                }
                if (isRunning == false) {
                    killThread();
                }
                fps++;
            }
            // show fps
            if (now - fpsCounter > 1000000000) {
                if (fps > fpsRequeried) {
                    fps = fpsRequeried;
                }
                GameManagment.gameWindow.setTitle("Snake Game | FPS: " + fps);
                fps = 0;
                fpsCounter = System.nanoTime();
            }
        }
    }

    // GETTERS AND SETTERS
    public static Thread getMainThread() {
        return mainThread;
    }

    public static void setMainThread(Thread mainThread) {
        Game.mainThread = mainThread;
    }

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static void setIsRunning(boolean isRunning) {
        Game.isRunning = isRunning;
    }

    public static boolean isIsPaused() {
        return isPaused;
    }

    public static void setIsPaused(boolean isPaused) {
        Game.isPaused = isPaused;
    }

    public static int getAps() {
        return aps;
    }

    public static void setAps(int aps) {
        Game.aps = aps;
    }

    public static int getFps() {
        return fps;
    }

    public static void setFps(int fps) {
        Game.fps = fps;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(GameCanvas canvas) {
        this.canvas = canvas;
    }

    public BufferStrategy getBs() {
        return bs;
    }

    public void setBs(BufferStrategy bs) {
        this.bs = bs;
    }

    public Graphics2D getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    

}
