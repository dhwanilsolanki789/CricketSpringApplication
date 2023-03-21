package com.tekion.cricket23.cricketSpring.dtos.reqdtos;

import jakarta.validation.constraints.NotBlank;

public class PlayerDto {
    @NotBlank(message = "Player Name is required")
    String playerName;
    @NotBlank(message =  "Batter or Bowler type is required")
    String type;

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "playerName='" + playerName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
