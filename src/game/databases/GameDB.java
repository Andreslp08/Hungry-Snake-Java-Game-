package game.databases;

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

    private static final String SCREEN_TABLE = "screen", SCREEN_ID_ATTRIBUTE = "id", FPS_ATTRIBUTE = "fps", SCREEN_WIDTH_ATTRIBUTE = "screen_width", SCREEN_HEIGHT_ATTRIBUTE = "screen_height",
            SOUND_TABLE = "sound", GAME_SOUND_ATTRIBUTE = "game_sound", MUSIC_SOUND_ATTRIBUTE = "music_sound";
    private static Connection connection;
    private static final String URL = "jdbc:sqlite:databases/game.db";

    public void connect() {
        // create directory
        try {
            Files.createDirectories(Paths.get("databases"));
        } catch (IOException ex) {
            System.out.println("Directory is alredy created");
        }
        // Query to create tables
        String screenQuery = "CREATE TABLE IF NOT EXISTS " + SCREEN_TABLE + " (\n"
                + SCREEN_ID_ATTRIBUTE + " integer NOT NULL,"
                + SCREEN_WIDTH_ATTRIBUTE + " integer NOT NULL, \n"
                + SCREEN_HEIGHT_ATTRIBUTE + " integer NOT NULL, \n"
                + FPS_ATTRIBUTE + " integer NOT NULL\n"
                + "); ";
        String soundQuery = "CREATE TABLE IF NOT EXISTS " + SOUND_TABLE + " (\n"
                + GAME_SOUND_ATTRIBUTE + " integer NOT NULL, \n"
                + MUSIC_SOUND_ATTRIBUTE + " integer NOT NULL \n"
                + "); ";
        String emptyQuery = "SELECT COUNT(*) AS total FROM " + SCREEN_TABLE;
        String createQuery = "INSERT INTO " + SCREEN_TABLE + "(" + SCREEN_ID_ATTRIBUTE + "," + SCREEN_WIDTH_ATTRIBUTE + "," + SCREEN_HEIGHT_ATTRIBUTE + "," + FPS_ATTRIBUTE + ") VALUES(?,?,?, ?)";

        try {
            connection = DriverManager.getConnection(URL);
            Statement stmt = connection.createStatement();
            // create tables
            stmt.execute(screenQuery);
            stmt.execute(soundQuery);
            stmt.close();
            // verify if screen table is empty
            PreparedStatement pstmt = connection.prepareStatement(emptyQuery);
            ResultSet result = pstmt.executeQuery();
            int size = result.getInt("total");
            if (size == 0) {
                pstmt = connection.prepareStatement(createQuery);
                pstmt.setInt(1, 1);
                pstmt.setInt(2, ScreenManagment.DEFAULT_WIDTH);
                pstmt.setInt(3, ScreenManagment.DEFAULT_HEIGHT);
                pstmt.setInt(4, ScreenManagment.DEFAULT_FPS_REQUERIED);
                pstmt.executeUpdate();
            }

            result.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void updateScreenTable(int width, int height, int fps) {
        String updateQuery = "UPDATE " + SCREEN_TABLE + " SET " + SCREEN_WIDTH_ATTRIBUTE + " = ?, " + SCREEN_HEIGHT_ATTRIBUTE + " = ?, " + FPS_ATTRIBUTE + " = ? WHERE id = 1";
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

    public int getScreenWidth() {
        int width = 0;
        String consultQuery = "SELECT " + SCREEN_WIDTH_ATTRIBUTE + " FROM " + SCREEN_TABLE + " WHERE ID = " + SCREEN_ID_ATTRIBUTE;
        try {
            PreparedStatement pstmt = connection.prepareStatement(consultQuery);
            ResultSet result = pstmt.executeQuery();
            width = Integer.parseInt(result.getString(SCREEN_WIDTH_ATTRIBUTE));
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return width;
    }

    public int getScreenHeight() {
        int height = 0;
        String consultQuery = "SELECT " + SCREEN_HEIGHT_ATTRIBUTE + " FROM " + SCREEN_TABLE + " WHERE ID = " + SCREEN_ID_ATTRIBUTE;
        try {
            PreparedStatement pstmt = connection.prepareStatement(consultQuery);
            ResultSet result = pstmt.executeQuery();
            height = Integer.parseInt(result.getString(SCREEN_HEIGHT_ATTRIBUTE));
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return height;
    }

    public int getFPS() {
        int fps = 0;
        String consultQuery = "SELECT " + FPS_ATTRIBUTE + " FROM " + SCREEN_TABLE + " WHERE ID = " + SCREEN_ID_ATTRIBUTE;
        try {
            PreparedStatement pstmt = connection.prepareStatement(consultQuery);
            ResultSet result = pstmt.executeQuery();
            fps = Integer.parseInt(result.getString(FPS_ATTRIBUTE));
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return fps;
    }
}
