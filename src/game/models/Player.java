/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.models;

public class Player {
    private String playerNick, playerDifficultyMode;
    private int playerId, playerScore;
    
    public Player( int playerId, String playerNick, int playerScore, String playerDifficultyMode ){
        this.playerId = playerId;
        this.playerNick = playerNick;
        this.playerScore = playerScore;
        this.playerDifficultyMode = playerDifficultyMode;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public void setPlayerNick(String playerNick) {
        this.playerNick = playerNick;
    }

    public String getPlayerDifficultyMode() {
        return playerDifficultyMode;
    }

    public void setPlayerDifficultyMode(String playerDifficultyMode) {
        this.playerDifficultyMode = playerDifficultyMode;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
    
    
}

