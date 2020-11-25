/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.physics;

import game.characters.Element;

public class Distance {

    static int xDistance(Element firstElement, Element secondElement) {
        int firstElementXcenter = firstElement.getCollisionX() + firstElement.getCollisionWidth()/2;
        int secondElementXcenter = secondElement.getCollisionX() + secondElement.getCollisionWidth()/2;
        return (firstElementXcenter)-(secondElementXcenter);
    }
        static int xDistance( int elementX, int secondElementX) {
        return (elementX)-(secondElementX);
    }
}
