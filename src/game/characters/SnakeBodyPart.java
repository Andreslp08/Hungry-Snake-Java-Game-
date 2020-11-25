/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.characters;

import game.characters.Snake.SNAKE_PARTS;
import game.main.Sprite;
import java.awt.Canvas;

public class SnakeBodyPart extends Element {

    private SNAKE_PARTS snakePart;
    private Sprite sprite;

    public SnakeBodyPart(Canvas canvas, int x, int y, int width, int height, String texturePath, SNAKE_PARTS snakePart) {
        super(canvas, x, y, width, height, texturePath);
        this.snakePart = snakePart;
    }

    public SNAKE_PARTS getSnakePart() {
        return snakePart;
    }

    public void setSnakePart(SNAKE_PARTS snakePart) {
        this.snakePart = snakePart;
    }

}
