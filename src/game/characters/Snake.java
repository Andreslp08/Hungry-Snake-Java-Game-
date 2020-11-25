
package game.characters;

import game.characters.Element;
import game.main.Delay;
import game.main.GameManagment;
import game.main.Sprite;
import game.ui.GameWindow;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Snake {

    public static enum SNAKE_DIRECTION {
        RIGHT, LEFT, UP, DOWN;
    }

    public static enum SNAKE_PARTS {
        HEAD, BODY, TAIL
    }

    private static ArrayList<SnakeBodyPart> bodyParts;
    private static int size;
    private int[] coorX, coorY;
    private int gridRow, gridCol;
    public static SNAKE_DIRECTION direction, lastDirection;
    private Canvas canvas;
    public static int speed;
    private Delay movementDelay;

    public Snake(Canvas canvas, int size, int[] coorX, int[] coorY, int speed) {
        this.canvas = canvas;
        this.size = size;
        this.coorX = coorX;
        this.coorY = coorY;
        this.gridRow = 0;
        this.gridCol = 0;
        bodyParts = new ArrayList();
        bodyParts.add(new SnakeBodyPart(canvas, coorX[gridRow], coorY[gridCol], size, size, "/game/assets/images/SnakeBody.png", Snake.SNAKE_PARTS.HEAD));
        this.gridRow++;
        bodyParts.add(new SnakeBodyPart(canvas, coorX[gridRow], coorY[gridCol], size, size, "/game/assets/images/SnakeBody.png", Snake.SNAKE_PARTS.BODY));
        this.gridRow++;
        bodyParts.add(new SnakeBodyPart(canvas, coorX[gridRow], coorY[gridCol], size, size, "/game/assets/images/SnakeBody.png", Snake.SNAKE_PARTS.BODY));
        direction = SNAKE_DIRECTION.RIGHT;
        this.speed = speed;
        movementDelay = new Delay();
    }

    public void movement() {

        movementDelay.start(speed, new Delay.DelayListener() {
            @Override
            public void run() {
                if (bodyParts.size() > 0) {
                    SnakeBodyPart lastSnakePart = bodyParts.get(0);
                    switch (direction) {
                        case RIGHT:
                            if (gridRow < coorX.length - 1) {
                                gridRow++;
                            } else {
                                gridRow = 0;
                            }

                            break;
                        case LEFT:
                            if (gridRow > 0) {
                                gridRow--;
                            } else {
                                gridRow = coorX.length - 1;
                            }
                            break;
                        case UP:
                            if (gridCol > 0) {
                                gridCol--;
                            } else {
                                gridCol = coorY.length - 1;
                            }
                            break;
                        case DOWN:
                            if (gridCol < coorY.length - 1) {
                                gridCol++;
                            } else {
                                gridCol = 0;
                            }
                            break;

                    }

                    removeBodyPart(lastSnakePart);
                    bodyParts.add(new SnakeBodyPart(canvas, coorX[gridRow], coorY[gridCol], size, size, "/game/assets/images/SnakeBody.png", Snake.SNAKE_PARTS.HEAD));

                }

                // set snake body type
                for (SnakeBodyPart bodyPart : bodyParts) {
                    bodyPart.setSnakePart(SNAKE_PARTS.BODY);
                }
                bodyParts.get(bodyParts.size() - 1).setSnakePart(SNAKE_PARTS.HEAD);
                bodyParts.get(0).setSnakePart(SNAKE_PARTS.TAIL);
                // show current body type
 changeSprite();
            }
        });

    }

    public void changeSprite() {
        // show current body type
        for (SnakeBodyPart bodyPart : bodyParts) {
            if (bodyPart.getSnakePart().equals(SNAKE_PARTS.HEAD)) {
                switch (direction) {
                    case RIGHT:
                        bodyPart.setTexturePath("/game/assets/images/SnakeHeadRight.png");
                        break;
                    case LEFT:
                        bodyPart.setTexturePath("/game/assets/images/SnakeHeadLeft.png");
                        break;
                    case UP:
                        bodyPart.setTexturePath("/game/assets/images/SnakeHeadUp.png");
                        break;
                    case DOWN:
                        bodyPart.setTexturePath("/game/assets/images/SnakeHeadDown.png");
                        break;

                }

            } else if (bodyPart.getSnakePart().equals(SNAKE_PARTS.TAIL)) {
                 bodyPart.setTexturePath("/game/assets/images/SnakeBody.png");
//                switch (direction) {
//                    case RIGHT:
//                        bodyPart.setTexturePath("/game/assets/images/SnakeTailRight.png");
//                        break;
//                    case LEFT:
//                        bodyPart.setTexturePath("/game/assets/images/SnakeTailLeft.png");
//                        break;
//                    case UP:
//                        bodyPart.setTexturePath("/game/assets/images/SnakeTailUp.png");
//                        break;
//                    case DOWN:
//                        bodyPart.setTexturePath("/game/assets/images/SnakeTailDown.png");
//                        break;
//
//                }
            } else {
                bodyPart.setTexturePath("/game/assets/images/SnakeBody.png");
            }

        }

    }

    public void enlarge() {
        // set snake body type
        for (SnakeBodyPart bodyPart : bodyParts) {
            bodyPart.setSnakePart(SNAKE_PARTS.BODY);
        }
        bodyParts.add(new SnakeBodyPart(canvas, coorX[gridRow], coorY[gridCol], size, size, "/game/assets/images/SnakeBody.png", Snake.SNAKE_PARTS.BODY));
    }

    private void removeBodyPart(SnakeBodyPart snakeBodyPart) {
        for (int i = 0; i < Element.elementList.size(); i++) {
            if (Element.elementList.get(i).equals(snakeBodyPart)) {
                Element.elementList.remove(i);
            }
        }
        for (int j = 0; j < bodyParts.size(); j++) {
            if (bodyParts.get(j).equals(snakeBodyPart)) {
                bodyParts.remove(j);
            }
        }
    }

    // GETTERS AND SETTERS
    public ArrayList<SnakeBodyPart> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(ArrayList<SnakeBodyPart> bodyParts) {
        this.bodyParts = bodyParts;
    }

    public int[] getCoorX() {
        return coorX;
    }

    public void setCoorX(int[] coorX) {
        this.coorX = coorX;
    }

    public int[] getCoorY() {
        return coorY;
    }

    public void setCoorY(int[] coorY) {
        this.coorY = coorY;
    }

}
