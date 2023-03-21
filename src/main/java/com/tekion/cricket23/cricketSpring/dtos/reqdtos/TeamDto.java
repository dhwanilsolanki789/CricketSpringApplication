package com.tekion.cricket23.cricketSpring.dtos.reqdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class TeamDto {
    @NotBlank(message = "Team name is required")
    String teamName;
    @NotNull(message = "Player data cannot be null")
    List<PlayerDto> players;

    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<PlayerDto> getPlayerDtos() {
        return players;
    }
    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "teamName='" + teamName + '\'' +
                ", playerDtos=" + players +
                '}';
    }
}
