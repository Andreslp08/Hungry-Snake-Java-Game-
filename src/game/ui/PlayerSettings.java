package game.ui;

import game.models.PlayerTableModel;
import game.main.GameManagment;
import game.ui.components.GameTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.REMAINDER;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class PlayerSettings extends JPanel {

    private static GameTextField playerNickField;
    private static JLabel playerNickLabel;
    private static JTable playerList;
    private static PlayerTableModel playerTableModel;
    private static GridBagConstraints constraints;
    private static JScrollPane tableScrollPane;

    private static final int TABLE_WIDTH = 400, TABLE_HEIGHT = 300, TABLE_ROW_HEIGHT = 30;

    public PlayerSettings() {
        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        // player nick label 
        playerNickLabel = new JLabel("Player Nick");
        playerNickLabel.setForeground(new Color(20, 20, 20, 255));
        playerNickLabel.setFont(new Font("Impact", 0, 20));
        this.add(playerNickLabel, constraints);
        // player nick label and field
        playerNickField = new GameTextField();
        playerNickField.setPreferredSize(new Dimension(150, 35));
        playerNickField.setText(GameManagment.USER_NICK);
        this.add(playerNickField, constraints);
        //player list
        constraints.gridy = 1;
        constraints.gridwidth = REMAINDER;
        tableScrollPane = new JScrollPane();
        tableScrollPane.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        tableScrollPane.setViewportBorder(null);

        tableScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0, 255, 0, 255);
                this.trackColor = new Color(255, 255, 255, 255);
                this.scrollBarWidth = 5;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setOpaque(false);
                button.setVisible(false);
                return button;
            }
            

            @Override
            protected JButton createIncreaseButton(int orientation) {
                   JButton button = new JButton();
                button.setOpaque(false);
                button.setVisible(false);
                return button;
            }

        });
        this.add(tableScrollPane, constraints);

        playerTableModel = new PlayerTableModel();
        playerList = new JTable(playerTableModel);
        playerList.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        playerList.setRowHeight(TABLE_ROW_HEIGHT);
        playerList.getTableHeader().setPreferredSize(new Dimension(TABLE_WIDTH, 40));
        playerList.getTableHeader().setBackground(new Color(0, 255, 0, 255));
        playerList.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        playerList.getTableHeader().setFont(new Font("Impact", 0, 20));
        playerList.getTableHeader().setReorderingAllowed(false);
        playerList.getTableHeader().setResizingAllowed(false);
        playerList.setFont(new Font("Arial Narrow", 1, 15));
        playerList.setEnabled(false);
        playerList.setShowGrid(false);
        playerList.setBorder(null);
        playerList.setBackground(new Color(215, 215, 215, 255));
        UIManager.getDefaults().put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(0, 0, 0, 0));
        playerList.setIntercellSpacing(new Dimension(0, 0));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        playerList.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        playerList.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        playerList.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        playerList.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableScrollPane.setViewportView(playerList);

        // handler to update player field and player name
        updatePlayerNickField();
    }

    
    public void updatePlayerTable(){
        playerList.removeAll();
        playerTableModel.setRowCount(0);
        playerTableModel.addRows();
        playerList.setPreferredSize(new Dimension(TABLE_WIDTH,playerTableModel.gameDB.getPlayersCount()*TABLE_ROW_HEIGHT ));
        
    }
    
    public void updatePlayerNickField() {
        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFieldState();
            }

            protected void updateFieldState() {
                GameManagment.USER_NICK = playerNickField.getText();
            }
        };
        playerNickField.getDocument().addDocumentListener(dl);

    }

}
