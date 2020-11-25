
package game.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.PopupMenu;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class GameCanvas extends Canvas{
        public GameCanvas(){

        this.setBounds(0, 0, ScreenManagment.WIDTH, ScreenManagment.HEIGHT);
    }
//        /**
//    * Crea un AffineTransform y rellena un array de puntos con la función
//    * sin(x)
//    */
//    public GameCanvas(){
//          t = new AffineTransform();
//        ponSeno (puntos);  
//    }
//    
//   
//    
//    /**
//    * Dibuja el sin(x) en el Canvas.
//    * Recalcula la AffineTransform por si ha habido un cambio en el tamaño
//    * del Cavnas, transforma los puntos de coordenadas de sin(x) a pixels y
//    * los dibuja.
//    */
//    public void paint(Graphics g)
//    {
//        Point2D [] puntosTransformados;
//        
//        // Recalcula la AffineTransform
//        ajustaAffineTransform();
//
//        // Transforma los puntos, cambio de coordenadas de los mismos
//        puntosTransformados = transformaPuntos(puntos);
//        
//        // Dibuja los puntos.
//        g.setColor (Color.RED);
//        for (int i = 1; i<500; i++)
//        {
//            g.drawLine (
//                (int)puntosTransformados[i-1].getX(), 
//                (int)puntosTransformados[i-1].getY(),
//                (int)puntosTransformados[i].getX(), 
//                (int)puntosTransformados[i].getY());
//        }
//    }
//    
//    /**
//    * Rellena un array de puntos con la función sin(x).
//    * El array de puntos que se le pasa debe tener dimensión, pero no hace
//    * falta que tenga los items creados.
//    */
//    protected void ponSeno (Point2D [] puntos)
//    {
//        double incremento = (xMax - xMin)/puntos.length;
//        double x = xMin;
//        
//        for (int i = 0; i<puntos.length; i++)
//        {
//            puntos[i] = new Point2D.Double (x, Math.sin(x));
//            x = x + incremento;
//        }
//    }
//    
//    /**
//    * Transforma las coordenadas de los puntos en pixels.
//    */
//    protected Point2D[] transformaPuntos (Point2D [] puntos)
//    {
//        Point2D [] puntosTransformados = new Point2D[puntos.length];
//        for (int i=0; i<puntos.length; i++)
//            puntosTransformados[i] = new Point2D.Double();
//        t.transform (puntos, 0, puntosTransformados, 0, puntos.length);
//        return puntosTransformados;
//    }
//    
//    /**
//    * Recalcula la AffineTransform con el tamaño del Canvas y los extremos
//    * de las coordenadas del sin(x).
//    */
//    public void ajustaAffineTransform()
//    {
//        t.setToIdentity();
//        t.translate (0.0, getHeight());
//        t.scale (getWidth()/(xMax-xMin), -getHeight()/(yMax-yMin));
//        t.translate (-xMin, -yMin);        
//    }
//    
//    
//    /** AffineTransform para el cambio de coordenadas a pixels */
//    private AffineTransform t;
//
//    /** Valor minimo de x para el dibujo del sin(x) */
//    private double xMin = -Math.PI/2.0;
//
//    /** Valor máximo de x para el dibujo del sin(x) */
//    private double xMax = 3*Math.PI/2.0;
//
//    /** Valor minimo de y para el dibujo del sin(x) */
//    private double yMin = -1.5;
//
//    /** Valor máximo de y para el dibujo del sin(x) */
//    private double yMax = 2.0;
//
//    /** Número de puntos que queremos dibujar */
//    private Point2D [] puntos = new Point2D[500];
}
