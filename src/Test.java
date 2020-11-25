import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.SwingUtilities;

public class Test {

    private static JFrame f;
    private static Canvas c;

    public static void main(String [] args) {
        init();
    }

    private static void init() {
        //Display.createDrawFrame(width, height);
        createDrawFrame(400, 400);
        //Display.createMenuFrame(width, height);
        createHUD();
        //this.f = Display.getDrawFrame();
        //getDrawFrame();
        //this.c = Display.getCanvas();
        //this.f = createHUD(this.f, this);
        //MapAssets.init();
    }

    public static void createDrawFrame(int width, int height) {
        f = new JFrame();
        c = new Canvas();
        c.setBackground(Color.blue);
        c.setPreferredSize(new Dimension(width, height));
        c.setFocusable(false);
        f.add(c, BorderLayout.CENTER); //f.add(c);
        f.pack();
        f.setVisible(true); // getDrawFrame()
        f.setSize(width, height);
        f.setLocationRelativeTo(null);
    }

    public static void createHUD() {
        Panel p = new Panel();
        p.setLayout(new FlowLayout());
        p.setSize(100, 100);
        p.setLocation(300, 0);
        Button b0 = new Button("Settings");
        Button b1 = new Button("Exit");
        //MenuListeners mListeners = new MenuListeners(game);
        //b0.addActionListener(mListeners);
        //b1.addActionListener(mListeners);
        p.add(b0);
        p.add(b1);
        f.add(p, BorderLayout.NORTH); //f.add(p);
    }
}