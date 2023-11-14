package com.bnta.Tic_tac_toe.models;

public class PlayerDTO {

    private String playerName;

    public PlayerDTO(String playerName) {
        this.playerName = playerName;
    }

    public PlayerDTO() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
