package game.main;

import game.characters.Element;
import game.ui.GameCanvas;
import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Sprite {

    private Canvas canvas;
    private Element element;
    private int spriteWidth, spriteHeight, rows, cols, firstFrame, lastFrame;
    private int framesLength;
    private int timeDelay = 0;
    private Delay delay;
    private boolean loop;
    private int currentFrame = 0;
    private String imagePath;
    private BufferedImage spriteImage;
    private int timeToRead = 0;
    private int currentRow = 1, currentCol = 1;
    private int[][] spriteCoordinates;
    private boolean isAnim = false;

    public Sprite() {
        delay = new Delay();
    }

    public void newAnimation(Canvas canvas, Element element, String imagePath, int spriteWidth, int spriteHeight, int rows, int cols, int firstFrame, int lastFrame, int timeDelay, boolean loop) {
        this.canvas = canvas;
        this.element = element;
        this.imagePath = imagePath;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.rows = rows;
        this.cols = cols;
        this.firstFrame = firstFrame;
        this.lastFrame = lastFrame;
        this.timeDelay = timeDelay;
        this.loop = loop;
        framesLength = rows * cols;
        this.timeDelay = timeDelay;
        spriteCoordinates = new int[framesLength][2];
        if (spriteImage == null) {
            try {
                spriteImage = ImageIO.read(new File(getClass().getResource(imagePath).toURI()));
            } catch (URISyntaxException ex) {
                Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void startAnimation() {
        int r = 0;
        int c = 0;
        for (int i = 0; i < spriteCoordinates.length; i++) {
            spriteCoordinates[i][0] = c * spriteWidth;
            spriteCoordinates[i][1] = r * spriteHeight;
            if (c < cols - 1) {
                c++;
            } else {
                c = 0;
                if (r < rows) {
                    r++;
                }
            }
        }
        delay.start(timeDelay, new Delay.DelayListener() {
            @Override
            public void run() {
                // check frame bounds
                if (firstFrame >= 0 && lastFrame <= framesLength - 1) {
                    // check if current frame is not out bounds of total frames
                    if (currentFrame < lastFrame) {
                        // set the first frame posiiton
                        if (!isAnim) {
                            if (firstFrame == 0) {
                                currentFrame = 0;
                            } else {
                                currentFrame = firstFrame - 1;
                            }
                            isAnim = true;
                        } else {
                            currentFrame++;
                        }
                        Image img = spriteImage.getSubimage(spriteCoordinates[currentFrame][0], spriteCoordinates[currentFrame][1], spriteWidth, spriteHeight);
                        element.setTexture(img);
                    } else {
                        // check loop
                        if (loop == true) {
                            isAnim = false;
                            if (firstFrame == 0) {
                                currentFrame = 0;
                            } else {
                                currentFrame = firstFrame - 1;
                            }
                        }

                    }
                    System.out.println("current frame: " + currentFrame + " current row: " + currentRow + " current col: " + currentCol);
                } else {
                    System.err.println("[SPRITES] frame out of bounds");
                }

            }
        });
    }

}
