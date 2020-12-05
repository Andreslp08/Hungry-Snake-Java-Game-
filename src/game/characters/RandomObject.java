package game.characters;

import game.characters.Element;
import game.main.Sprite;
import java.awt.Canvas;
import java.awt.event.KeyEvent;

public class RandomObject extends Element {

    private Sprite sprite;
    public RandomObject(Canvas canvas, int x, int y, int width, int height, String texturePath) {
        super(canvas, x, y, width, height, texturePath);
          // sprites
           sprite = new Sprite();
           
          
    }
        
    public void anim(){
    
    }


}
