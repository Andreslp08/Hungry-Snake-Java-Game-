package game.databases;

import game.controller.PauseMenuInputHandler;
import game.controller.SnakeInputHandler;
import game.main.SoundManagment;
import game.ui.ScreenManagment;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameDB {

    public static final String SCREEN_TABLE = "screen", SCREEN_ID_ATTR = "id", FPS_ATTR = "fps", SCREEN_WIDTH_ATTR = "screen_width", SCREEN_HEIGHT_ATTR = "screen_height",
            SOUND_TABLE = "sound", SOUND_ID_ATTR = "id", GAME_SOUND_ATTR = "game_sound", UI_SOUND_ATTR = "ui_sound",
            CONTROLS_TABLE = "controls", CONTROLS_ID_ATTR = "id", UP_CONTROL_ATTR = "upControl", DOWN_CONTROL_ATTR = "downControl", LEFT_CONTROL_ATTR = "leftControl", RIGHT_CONTROL_ATTR = "rightControl", PAUSE_CONTROL_ATTR = "pauseControl",
            PLAYERS_TABLE = "players", PLAYERS_ID_ATTR = "id", PLAYER_NICK_ATTR = "player_nick", PLAYER_SCORE_ATTR = "player_score", PLAYER_DIFFICULTY_ATTR = "player_difficulty";
    public static Connection connection;
    public static final String URL = "jdbc:sqlite:databases/game.db";

    public void connect() {
        // create directory
        try {
            Files.createDirectories(Paths.get("databases"));
        } catch (IOException ex) {
            System.out.println("Directory is alredy created");
        }

        try {
            connection = DriverManager.getConnection(URL);
            createScreenTable();
            createSoundTable();
            createControlsTable();
            createPlayersTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void createScreenTable() {
        // Queries
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + SCREEN_TABLE + " (\n"
                + SCREEN_ID_ATTR + " integer NOT NULL,"
                + SCREEN_WIDTH_ATTR + " integer NOT NULL, \n"
                + SCREEN_HEIGHT_ATTR + " integer NOT NULL, \n"
                + FPS_ATTR + " integer NOT NULL\n"
                + "); ";
        String isEmptyQuery = "SELECT COUNT(*) AS total FROM " + SCREEN_TABLE;
        String insertDefaultQuery = "INSERT INTO " + SCREEN_TABLE + "(" + SCREEN_ID_ATTR + "," + SCREEN_WIDTH_ATTR + "," + SCREEN_HEIGHT_ATTR + "," + FPS_ATTR + ") VALUES(?,?,?, ?)";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            // create table
            stmt.execute(createTableQuery);
            // verify if screen table is empty
            PreparedStatement pstmt = connection.prepareStatement(isEmptyQuery);
            ResultSet result = pstmt.executeQuery();
            int size = result.getInt("total");
            if (size == 0) {
                // create default values
                pstmt = connection.prepareStatement(insertDefaultQuery);
                pstmt.setInt(1, 1);
                pstmt.setInt(2, ScreenManagment.DEFAULT_WIDTH);
                pstmt.setInt(3, ScreenManagment.DEFAULT_HEIGHT);
                pstmt.setInt(4, ScreenManagment.DEFAULT_FPS_REQUERIED);
                pstmt.executeUpdate();
            }
            stmt.close();
            result.close();
        } catch (SQLException ex) {
            System.out.println("Cannot create screen table " + ex);
        }

    }

    public void createSoundTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + SOUND_TABLE + " (\n"
                + SOUND_ID_ATTR + " integer NOT NULL, \n"
                + GAME_SOUND_ATTR + " integer NOT NULL, \n"
                + UI_SOUND_ATTR + " integer NOT NULL \n"
                + "); ";
        String isEmptyQuery = "SELECT COUNT(*) AS total FROM " + SOUND_TABLE;
        String insertDefaultQuery = "INSERT INTO " + SOUND_TABLE + "(" + SOUND_ID_ATTR + "," + GAME_SOUND_ATTR + "," + UI_SOUND_ATTR + ") VALUES(?,?,?)";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            // create table
            stmt.execute(createTableQuery);
            // verify if screen table is empty
            PreparedStatement pstmt = connection.prepareStatement(isEmptyQuery);
            ResultSet result = pstmt.executeQuery();
            int size = result.getInt("total");
            if (size == 0) {
                // create default values
                pstmt = connection.prepareStatement(insertDefaultQuery);
                pstmt.setInt(1, 1);
                pstmt.setInt(2, SoundManagment.DEFAULT_GAME_VOLUME);
                pstmt.setInt(3, SoundManagment.DEFAULT_UI_VOLUME);
                pstmt.executeUpdate();
            }
            stmt.close();
            result.close();
        } catch (SQLException ex) {
            System.out.println("Cannot create sound table " + ex);
        }
    }

    public void createControlsTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + CONTROLS_TABLE + " (\n"
                + CONTROLS_ID_ATTR + " integer NOT NULL, \n"
                + UP_CONTROL_ATTR + " integer NOT NULL, \n"
                + DOWN_CONTROL_ATTR + " integer NOT NULL, \n"
                + LEFT_CONTROL_ATTR + " integer NOT NULL, \n"
                + RIGHT_CONTROL_ATTR + " integer NOT NULL, \n"
                + PAUSE_CONTROL_ATTR + " integer NOT NULL \n"
                + "); ";
        String isEmptyQuery = "SELECT COUNT(*) AS total FROM " + CONTROLS_TABLE;
        String insertDefaultQuery = "INSERT INTO " + CONTROLS_TABLE + "(" + CONTROLS_ID_ATTR + "," + UP_CONTROL_ATTR + "," + DOWN_CONTROL_ATTR + "," + LEFT_CONTROL_ATTR + "," + RIGHT_CONTROL_ATTR + "," + PAUSE_CONTROL_ATTR + ") VALUES(?,?,?,?,?,?)";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            // create table
            stmt.execute(createTableQuery);
            // verify if screen table is empty
            PreparedStatement pstmt = connection.prepareStatement(isEmptyQuery);
            ResultSet result = pstmt.executeQuery();
            int size = result.getInt("total");
            if (size == 0) {
                // create default values
                pstmt = connection.prepareStatement(insertDefaultQuery);
                pstmt.setInt(1, 1);
                pstmt.setInt(2, SnakeInputHandler.DEFAULT_SNAKE_UP);
                pstmt.setInt(3, SnakeInputHandler.DEFAULT_SNAKE_DOWN);
                pstmt.setInt(4, SnakeInputHandler.DEFAULT_SNAKE_LEFT);
                pstmt.setInt(5, SnakeInputHandler.DEFAULT_SNAKE_RIGHT);
                pstmt.setInt(6, PauseMenuInputHandler.DEFAULT_PAUSE_KEY);
                pstmt.executeUpdate();
            }
            stmt.close();
            result.close();
        } catch (SQLException ex) {
            System.out.println("Cannot create sound table " + ex);
        }

    }

    public void createPlayersTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + PLAYERS_TABLE + " (\n"
                + PLAYERS_ID_ATTR + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n"
                + PLAYER_NICK_ATTR + " varchar NOT NULL, \n"
                + PLAYER_SCORE_ATTR + " integer NOT NULL, \n"
                + PLAYER_DIFFICULTY_ATTR + " varchar NOT NULL \n"
                + "); ";
        String isEmptyQuery = "SELECT COUNT(*) AS total FROM " + SOUND_TABLE;
        String insertDefaultQuery = "INSERT INTO " + SOUND_TABLE + "(" + SOUND_ID_ATTR + "," + GAME_SOUND_ATTR + "," + UI_SOUND_ATTR + ") VALUES(?,?,?)";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            // create table
            stmt.execute(createTableQuery);
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Cannot create sound table " + ex);
        }
    }

    public void savePlayerScore(String name, int score, String difficulty) {
        String updateQuery = "INSERT INTO " + PLAYERS_TABLE + "(" + PLAYER_NICK_ATTR + "," + PLAYER_SCORE_ATTR + "," + PLAYER_DIFFICULTY_ATTR + ") VALUES(?,?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateQuery);
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.setString(3, difficulty);
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateScreenTable(int width, int height, int fps) {
        String updateQuery = "UPDATE " + SCREEN_TABLE + " SET " + SCREEN_WIDTH_ATTR + " = ?, " + SCREEN_HEIGHT_ATTR + " = ?, " + FPS_ATTR + " = ? WHERE id = 1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateQuery);
            pstmt.setInt(1, width);
            pstmt.setInt(2, height);
            pstmt.setInt(3, fps);
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateSoundTable(int gameVolume, int uiVolume) {
        String updateQuery = "UPDATE " + SOUND_TABLE + " SET " + GAME_SOUND_ATTR + " = ?, " + UI_SOUND_ATTR + " = ? WHERE id = 1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateQuery);
            pstmt.setInt(1, gameVolume);
            pstmt.setInt(2, uiVolume);
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateControlsTable(int snakeUp, int snakeDown, int snakeLeft, int snakeRight, int pauseKey) {
        String updateQuery = "UPDATE " + CONTROLS_TABLE + " SET "
                + UP_CONTROL_ATTR + " = ?, "
                + DOWN_CONTROL_ATTR + " = ?, "
                + LEFT_CONTROL_ATTR + " = ?, "
                + RIGHT_CONTROL_ATTR + " = ?, "
                + PAUSE_CONTROL_ATTR + " = ? WHERE id = 1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateQuery);
            pstmt.setInt(1, snakeUp);
            pstmt.setInt(2, snakeDown);
            pstmt.setInt(3, snakeLeft);
            pstmt.setInt(4, snakeRight);
            pstmt.setInt(5, pauseKey);
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getScreenWidth() {
        int width = 0;
        String consultQuery = "SELECT " + SCREEN_WIDTH_ATTR + " FROM " + SCREEN_TABLE + " WHERE " + SCREEN_ID_ATTR + " = 1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(consultQuery);
            ResultSet result = pstmt.executeQuery();
            width = Integer.parseInt(result.getString(SCREEN_WIDTH_ATTR));
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return width;
    }

    public int getScreenHeight() {
        int height = 0;
        String consultQuery = "SELECT " + SCREEN_HEIGHT_ATTR + " FROM " + SCREEN_TABLE + " WHERE " + SCREEN_ID_ATTR + " = 1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(consultQuery);
            ResultSet result = pstmt.executeQuery();
            height = Integer.parseInt(result.getString(SCREEN_HEIGHT_ATTR));
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return height;
    }

    public int getFPS() {
        int fps = 0;
        String consultQuery = "SELECT " + FPS_ATTR + " FROM " + SCREEN_TABLE + " WHERE " + SCREEN_ID_ATTR + " = 1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(consultQuery);
            ResultSet result = pstmt.executeQuery();
            fps = Integer.parseInt(result.getString(FPS_ATTR));
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return fps;
    }

    public int getGameVolume() {
        int gameVolume = 0;
        String consultQuery = "SELECT " + GAME_SOUND_ATTR + " FROM " + SOUND_TABLE + " WHERE " + SCREEN_ID_ATTR + " = 1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(consultQuery);
            ResultSet result = pstmt.executeQuery();
            gameVolume = Integer.parseInt(result.getString(GAME_SOUND_ATTR));
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return gameVolume;
    }

    public int getUIVolume() {
        int uiVolume = 0;
        String consultQuery = "SELECT " + UI_SOUND_ATTR + " FROM " + SOUND_TABLE + " WHERE " + SCREEN_ID_ATTR + " = 1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(consultQuery);
            ResultSet result = pstmt.executeQuery();
            uiVolume = Integer.parseInt(result.getString(UI_SOUND_ATTR));
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return uiVolume;
    }

    public int getKey(String keyAttr) {
        int key = 0;
        String consultQuery = "SELECT " + keyAttr + " FROM " + CONTROLS_TABLE + " WHERE " + CONTROLS_ID_ATTR + " = 1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(consultQuery);
            ResultSet result = pstmt.executeQuery();
            key = Integer.parseInt(result.getString(keyAttr));
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return key;
    }

    public int getPlayersCount() {
        int size = 0;
        String consultQuery = "SELECT COUNT(*) AS total FROM " + PLAYERS_TABLE;
        try {
            PreparedStatement pstmt = connection.prepareStatement(consultQuery);
            ResultSet result = pstmt.executeQuery();
            size = result.getInt("total");
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return size;
    }

    public String[][] getPlayers() {
        int size = getPlayersCount();
        int i = 0;
        String[][] players = new String[size][4];
        String consultQuery = "SELECT * FROM " + PLAYERS_TABLE + " ORDER BY " + PLAYER_DIFFICULTY_ATTR + "," + PLAYER_SCORE_ATTR + " DESC";
        try {
            PreparedStatement pstmt = connection.prepareStatement(consultQuery);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
               
                players[i][0] = result.getInt(PLAYERS_ID_ATTR) + "";
                players[i][1] = result.getString(PLAYER_NICK_ATTR) + "";
                players[i][2] = result.getInt(PLAYER_SCORE_ATTR) + "";
                players[i][3] = result.getString(PLAYER_DIFFICULTY_ATTR)+"";
                 i++;
            }
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return players;
    }

}
