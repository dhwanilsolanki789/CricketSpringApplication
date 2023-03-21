package com.tekion.cricket23.cricketSpring.beans.statsbeans;

public class StatsInMatch {
    private PlayerBattingStats battingStats;
    private PlayerBowlingStats bowlingStats;

    public StatsInMatch(){}
    public StatsInMatch(PlayerBattingStats battingStats, PlayerBowlingStats bowlingStats){
        this.battingStats = battingStats;
        this.bowlingStats = bowlingStats;
    }

    public PlayerBattingStats getBattingStats() {
        return battingStats;
    }

    public PlayerBowlingStats getBowlingStats() {
        return bowlingStats;
    }

    @Override
    public String toString() {
        return "StatsInMatch{" +
                "battingStats=" + battingStats +
                ", bowlingStats=" + bowlingStats +
                '}';
    }
}
