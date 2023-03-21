package com.tekion.cricket23.cricketSpring.dtos.resdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Map;

@JsonRootName(value = "response")
public class InningRes {
    @JsonProperty(value = "runsScored")
    private int runsScored;
    @JsonProperty(value = "wicketsFell")
    private int wicketsFell;
    @JsonProperty(value = "oversBowled")
    private int oversBowled;
    @JsonProperty(value = "excessBallsBowled")
    private int excessBallsBowled;
    @JsonProperty(value = "battingTeam")
    private String battingTeam;
    @JsonProperty(value = "bowlingTeam")
    private String bowlingTeam;
    @JsonProperty(value = "battingStats")
    private Map<String,String> battingStats;
    @JsonProperty(value = "bowlingStats")
    private Map<String,String> bowlingStats;

    public InningRes(int runsScored, int wicketsFell, int oversBowled, int excessBallsBowled,
                     String battingTeam, String bowlingTeam) {
        this.runsScored = runsScored;
        this.wicketsFell = wicketsFell;
        this.oversBowled = oversBowled;
        this.excessBallsBowled = excessBallsBowled;
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
    }

    public void setBattingStats(Map<String, String> battingStats) {
        this.battingStats = battingStats;
    }

    public void setBowlingStats(Map<String, String> bowlingStats) {
        this.bowlingStats = bowlingStats;
    }

    @Override
    public String toString() {
        return "InningRes{" +
                "runsScored=" + runsScored +
                ", wicketsFell=" + wicketsFell +
                ", oversBowled=" + oversBowled +
                ", excessBallsBowled=" + excessBallsBowled +
                ", battingTeam='" + battingTeam + '\'' +
                ", bowlingTeam='" + bowlingTeam + '\'' +
                ", battingStats=" + battingStats +
                ", bowlingStats=" + bowlingStats +
                '}';
    }
}
