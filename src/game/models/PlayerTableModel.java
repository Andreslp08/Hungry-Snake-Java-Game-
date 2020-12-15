/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.models;

import game.databases.GameDB;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PlayerTableModel extends DefaultTableModel implements TableModel {

    private final String PLAYER_ID = "ID", PLAYER_NICK = "Nick", PLAYER_SCORE = "Score", PLAYER_DIFFICULTY_MODE = "Difficulty";
    public static ArrayList players;
    public GameDB gameDB;

    public PlayerTableModel() {
        gameDB = new GameDB();
        gameDB.connect();
        addColumn(PLAYER_ID);
        addColumn(PLAYER_NICK);
        addColumn(PLAYER_SCORE);
        addColumn(PLAYER_DIFFICULTY_MODE);
        addRows();
    }

    public void addRows() {
        String[][] players = gameDB.getPlayers();
        int size = players.length;
        String[] playerInfo = new String[4];
        for (int i = 0; i < size; i++) {
            playerInfo[0] = players[i][0];
            playerInfo[1] = players[i][1];
            playerInfo[2] = players[i][2];
            playerInfo[3] = players[i][3];
            addRow(playerInfo);
        }

    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
    

}
