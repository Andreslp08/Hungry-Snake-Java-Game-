package game.physics;

import game.characters.Element;

public class Collision {

    public static boolean basicCollision(Element element1, Element element2) {
        int element1Left = element1.getCollisionX();
        int element1Right = element1.getCollisionX() + element1.getCollisionWidth();
        int element1Top = element1.getCollisionY();
        int element1Bottom = element1.getCollisionY() + element1.getCollisionHeight();
        
        int element2Left = element2.getCollisionX();
        int element2Right = element2.getCollisionX() + element2.getCollisionWidth();
        int element2Top = element2.getCollisionY();
        int element2Bottom = element2.getCollisionY() + element2.getCollisionHeight();
        if( element1Right > element2Left && element1Left < element2Right && element1Bottom > element2Top && element1Top <  element2Bottom ){
           return true;
        }
        else{
            return false;
        }
    }
}

