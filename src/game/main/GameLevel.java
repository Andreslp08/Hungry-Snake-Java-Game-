package game.main;

import game.main.Game;
import game.characters.Snake;
import game.characters.Element;
import game.characters.RandomObject;
import game.characters.SnakeBodyPart;
import game.ui.ScreenManagment;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class GameLevel {

    static enum DifficultyMode {
        EASY, NORMAL, HARD
    }
    private DifficultyMode difficulty;
    public static Snake snake;
    private static Game game;
    private static RandomObject randomObjects[];
    private Image background;
    private String[] backgroundPath = {
        "game/assets/images/background.png"
    };
    private static int SCREEN_WIDTH = ScreenManagment.WIDTH;
    private static int SCREEN_HEIGHT = ScreenManagment.HEIGHT-ScreenManagment.HUD_HEIGHT;
    private static int SQUARE_SIZE = (ScreenManagment.WIDTH + ScreenManagment.HEIGHT)/50;
    private static int UNIT_X = SCREEN_WIDTH / SQUARE_SIZE;
    private static int UNIT_Y = SCREEN_HEIGHT / SQUARE_SIZE;
    private static int[] coorX = new int[UNIT_X];
    private static int[] coorY = new int[UNIT_Y];

    Element elements[][];

    public GameLevel(Game game, DifficultyMode difficulty) {
        background = new ImageIcon(getClass().getClassLoader().getResource(backgroundPath[0])).getImage();
        this.game = game;
        this.difficulty = difficulty;
        createCoordenates();
        configLevel();
    }

    public void drawBackground() {
        Graphics2D g = (Graphics2D) game.getBs().getDrawGraphics();
        g.drawImage(background, 0, 0, ScreenManagment.WIDTH, ScreenManagment.HEIGHT, null);

    }

    public void configLevel() {
        // create snake
        switch (difficulty) {
            case EASY:
                snake = new Snake(game.getCanvas(), SQUARE_SIZE, coorX, coorY, 6);
                randomObjects = new RandomObject[1];
                createRandomObjects();
                break;
            case NORMAL:
                snake = new Snake(game.getCanvas(), SQUARE_SIZE, coorX, coorY, 4);
                randomObjects = new RandomObject[1];
                createRandomObjects();
                break;
            case HARD:
                snake = new Snake(game.getCanvas(), SQUARE_SIZE, coorX, coorY, 0);
                randomObjects = new RandomObject[2];
                createRandomObjects();
                break;
        }

    }

    public void createCoordenates() {
        for (int i = 0; i < coorX.length; i++) {
            coorX[i] = i * SQUARE_SIZE;
        }
        for (int i = 0; i < coorY.length; i++) {
            coorY[i] = i * SQUARE_SIZE;
        }
    }

    public void createRandomObjects() {
        int numRandomO = 0;
        for (int i = 0; i < Element.elementList.size(); i++) {
            if (Element.elementList.get(i).getClass().getSimpleName().equals("RandomObject")) {
                numRandomO++;
            }
        }

        if (numRandomO == 0) {
            for (int i = 0; i < randomObjects.length; i++) {
                int randomX = (int) (Math.random() * coorX.length);
                int randomY = (int) (Math.random() * coorY.length);
                randomObjects[i] = new RandomObject(game.getCanvas(), coorX[randomX], coorY[randomY], SQUARE_SIZE, SQUARE_SIZE, "game/assets/images/apple.png");
            }
        }

    }

    public void renderLevel() {
        drawBackground();
        snake.movement();
//        for (int i = 0; i < SCREEN_HEIGHT / SQUARE_SIZE; i++) {
//            game.getGraphics().drawLine(i * SQUARE_SIZE, 0, i * SQUARE_SIZE, SCREEN_HEIGHT);
//            game.getGraphics().drawLine(0, i * SQUARE_SIZE, SCREEN_WIDTH, i * SQUARE_SIZE);
//        }

        for (int i = 0; i < Element.elementList.size(); i++) {
//            Element.elementList.get(i).draw(game.getBs());
//            Element.elementList.get(i).drawCollisionBounds(game.getBs());
            if (Element.elementList.get(i).getClass().getSimpleName().equals("RandomObject")) {
                RandomObject randomObject = (RandomObject) Element.elementList.get(i);
                randomObject.draw(game.getBs());
                randomObject.setCollisionBounds(0, 0, 0, 0);
                randomObject.anim();

            }
            if (Element.elementList.get(i).getClass().getSimpleName().equals("SnakeBodyPart")) {
                SnakeBodyPart snakeBodyPart = (SnakeBodyPart) Element.elementList.get(i);
                snakeBodyPart.draw(game.getBs());
            }
        }
    }

}
