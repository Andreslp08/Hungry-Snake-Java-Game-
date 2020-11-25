package game.characters;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import game.main.GameManagment;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Element {

    protected Canvas canvas;
    protected int x, y, width, height, collisionX, collisionY, collisionWidth, collisionHeight;
    protected boolean right, left, up, down;
    protected int movementSpeed = 10;
    protected Image texture;
    protected BufferStrategy bs;
    protected Graphics2D graphics;
    protected String texturePath;
    public static final ArrayList<Element> elementList = new ArrayList();

    public Element(Canvas canvas, int x, int y, int width, int height, String texturePath) {
        this.canvas = canvas;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collisionX = x;
        this.collisionY = y;
        this.collisionWidth = width;
        this.collisionHeight = height;
        this.texturePath = texturePath;
        this.texture = new ImageIcon(getClass().getResource(texturePath)).getImage();
        right = false;
        left = false;
        up = false;
        down = false;
        elementList.add(this);
       
        
    }

    public void draw(BufferStrategy bs) {
        graphics = (Graphics2D) bs.getDrawGraphics();
        graphics.drawImage(texture, x, y, width, height, canvas);
    }

    public void drawCollisionBounds(BufferStrategy bs) {
        graphics = (Graphics2D) bs.getDrawGraphics();
        graphics.setColor(Color.GREEN);
        graphics.setStroke(new BasicStroke(2));
        graphics.drawRect(collisionX, collisionY, collisionWidth, collisionHeight);
    }

    public void setCollisionBounds(int collisionX, int collisionY, int collisionWidth, int collisionHeight) {
        this.collisionX = x + collisionX;
        this.collisionY = y + collisionY;
        this.collisionWidth = width + collisionWidth;
        this.collisionHeight = height + collisionHeight;
    }

    // GETTERS AND SETTERS
    public int getCollisionWidth() {
        return collisionWidth;
    }

    public void setCollisionWidth(int collisionWidth) {
        this.collisionWidth = collisionWidth;
    }

    public int getCollisionHeight() {
        return collisionHeight;
    }

    public void setCollisionHeight(int collisionHeight) {
        this.collisionHeight = collisionHeight;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;

    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCollisionX() {
        return collisionX;
    }

    public void setCollisionX(int collisionX) {
        this.collisionX = collisionX;
    }

    public int getCollisionY() {
        return collisionY;
    }

    public void setCollisionY(int collisionY) {
        this.collisionY = collisionY;
    }

    public Image getTexture() {
        return texture;
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    public BufferStrategy getBs() {
        return bs;
    }

    public void setBs(BufferStrategy bs) {
        this.bs = bs;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
        this.texture = new ImageIcon(getClass().getResource(texturePath)).getImage();
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

}
