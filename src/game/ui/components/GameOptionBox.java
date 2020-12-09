package game.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.OptionalInt;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOptionBox extends JPanel {

    private JLabel titleOptionLabel;
    private JTextField optionField;
    private JButton leftButton, rightButton;
    private String title;
    private String[] options;
    private String option;
    private int optionIndex;

    public GameOptionBox(String title, String[] options) {
        optionIndex = 0;
        this.title = title;
        this.options = options;
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        // label
        constraints.ipadx = 100;
        titleOptionLabel = new JLabel(this.title);
        titleOptionLabel.setFont(new Font("Impact", 1, 20));
        titleOptionLabel.setPreferredSize(new Dimension(200,30));
        this.add(titleOptionLabel, constraints);
        // left button
        constraints.ipadx = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        leftButton = new JButton("<");
        leftButton.setForeground(new Color(0, 255, 0, 255));
        leftButton.setBorder(null);
        leftButton.setFocusPainted(false);
        leftButton.setFocusable(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setFont(new Font("Impact", 1, 30));
        this.add(leftButton, constraints);
        // field
        optionField = new JTextField("option");
        optionField.setPreferredSize(new Dimension(150, 30));
        optionField.setFont(new Font("Arial Narrow", 1, 20));
        optionField.setEditable(false);
        optionField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0,255,0,255)));
        optionField.setHorizontalAlignment(JTextField.CENTER);
        this.add(optionField, constraints);
        // right Button
        rightButton = new JButton(">");
        rightButton.setForeground(new Color(0, 255, 0, 255));
        rightButton.setBorder(null);
        rightButton.setFocusPainted(false);
        rightButton.setFocusable(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFont(new Font("Impact", 1, 30));
        this.add(rightButton, constraints);
        // navigate 
        navigate();
    }
    
    
    public void navigate(){
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                if( optionIndex < options.length - 1 ){
                    optionIndex++;
                    optionField.setText(options[optionIndex]);
                }
            }
         
        });
                leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                if( optionIndex > 0){
                    optionIndex--;
                    optionField.setText(options[optionIndex]);
                }
            }
         
        });
        
    }
    
    public String getSelectedOption(){
        return options[optionIndex];
    }
    
    public void setDefaultOption(int index){
        optionIndex = index;
        optionField.setText(options[optionIndex]);
    }
    
    
}
