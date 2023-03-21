package com.tekion.cricket23.cricketSpring.dtos.resdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "response")
public class PlayerStatsInMatch {
    @JsonProperty(value = "playerName")
    private String playerName;
    @JsonProperty(value = "battingStats")
    private String battingStats;
    @JsonProperty(value = "bowlingStats")
    private String bowlingStats;

    public PlayerStatsInMatch(String playerName) {
        this.playerName = playerName;
    }

    public void setBattingStats(String battingStats) {
        this.battingStats = battingStats;
    }
    public void setBowlingStats(String bowlingStats) {
        this.bowlingStats = bowlingStats;
    }

    @Override
    public String toString() {
        return "PlayerStatsInMatch{" +
                "playerName='" + playerName + '\'' +
                ", battingStats='" + battingStats + '\'' +
                ", bowlingStats='" + bowlingStats + '\'' +
                '}';
    }
}
