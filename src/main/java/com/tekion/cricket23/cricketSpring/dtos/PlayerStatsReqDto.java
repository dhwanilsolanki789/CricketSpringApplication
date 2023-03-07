package com.tekion.cricket23.cricketSpring.dtos;

import jakarta.validation.constraints.NotBlank;

public class PlayerStatsReqDto {
    @NotBlank(message = "Player id cannot be blank")
    String playerId;
    @NotBlank(message = "Match id cannot be null")
    String matchId;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
