package game.ui.components;

import game.main.GameSound;
import game.main.SoundManagment;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class GameButton extends JButton implements MouseListener {

    private Color buttonColorEntered = new Color(45, 255, 45, 255);
    private Color buttonColorPressed = new Color(20, 150, 20, 255);
    private Color buttonColorReleased = new Color(45, 255, 45, 255);
    private Color buttonColorExited = new Color(55, 255, 55, 255);
    private Color buttonColor = new Color(55, 255, 55, 255);
    private Color textColor = new Color(255, 255, 255, 255);
    private int radius = 50;
    private GameSound clickSound;

    public GameButton(String text) {
        this.setText(text);
        addMouseListener(this);
        this.setBorder(null);
        this.setFocusPainted(false);
        this.setFocusable(false);
        this.setContentAreaFilled(false);
        this.setForeground(textColor);
        this.setFont(new Font("Arial", 1, 40));
        clickSound = new GameSound("/game/assets/sounds/Click.wav", SoundManagment.SoundType.UI);
        clickSound.setDefaultVolume(50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        RenderingHints qualityHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        final Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setRenderingHints(qualityHints);
        graphics2D.setPaint(buttonColor);
        graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        graphics2D.dispose();
        super.paintComponent(g);
    }

    // OVERRIDE METHODS
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttonColor = buttonColorPressed;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttonColor = buttonColorReleased;
        clickSound.start();
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (getParent().isVisible() == true) {
            setLocation(getX() - 5, getY() - 5);
            buttonColor = buttonColorEntered;
            setSize(getWidth() + 10, getHeight() + 10);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (getParent().isVisible() == true) {
           
            buttonColor = buttonColorExited;
            // return to original size
            if( getWidth() > getPreferredSize().width && getHeight() > getPreferredSize().height){
                 setLocation(getX() + 5, getY() + 5);
                setSize(getWidth() - 10, getHeight() - 10);
            }
            
        }
    }

    // GETTERS AND SETTERS
    public Color getButtonColorEntered() {
        return buttonColorEntered;
    }

    public void setButtonColorEntered(Color buttonColorEntered) {
        this.buttonColorEntered = buttonColorEntered;
    }

    public Color getButtonColorPressed() {
        return buttonColorPressed;
    }

    public void setButtonColorPressed(Color buttonColorPressed) {
        this.buttonColorPressed = buttonColorPressed;
    }

    public Color getButtonColorReleased() {
        return buttonColorReleased;
    }

    public void setButtonColorReleased(Color buttonColorReleased) {
        this.buttonColorReleased = buttonColorReleased;
    }

    public Color getButtonColorExited() {
        return buttonColorExited;
    }

    public void setButtonColorExited(Color buttonColorExited) {
        this.buttonColorExited = buttonColorExited;
    }

    public Color getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}
