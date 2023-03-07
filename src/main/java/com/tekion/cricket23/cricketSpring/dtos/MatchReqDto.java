package com.tekion.cricket23.cricketSpring.dtos;

import jakarta.validation.constraints.NotBlank;

public class MatchReqDto {
    @NotBlank(message = "Match id cannot be blank")
    String matchId;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
