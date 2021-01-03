package game.main;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                 GameManagment gameManagment = new GameManagment();
                gameManagment.init();
            }
        });
       
    }
}
