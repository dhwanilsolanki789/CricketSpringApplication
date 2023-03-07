package com.tekion.cricket23.cricketSpring.beans.matchbeans;

import com.tekion.cricket23.cricketSpring.beans.statsbeans.PlayerBattingStats;
import com.tekion.cricket23.cricketSpring.beans.statsbeans.PlayerBowlingStats;
import org.springframework.data.annotation.Transient;

import java.util.LinkedHashMap;

public class Inning {
    private int runsScored;
    private int wicketsFell;
    private int oversBowled;
    private int excessBallsBowled;
    private final String battingTeam;
    private final String bowlingTeam;
    private LinkedHashMap<String, PlayerBattingStats> battingStats;
    private LinkedHashMap<String, PlayerBowlingStats> bowlingStats;
    @Transient
    private boolean inningEnded;

    public Inning(String battingTeam, String bowlingTeam, LinkedHashMap<String, PlayerBattingStats> battingStats,
                  LinkedHashMap<String, PlayerBowlingStats> bowlingStats) {
        this.runsScored = this.wicketsFell = 0;
        this.oversBowled = this.excessBallsBowled = 0;
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.battingStats = battingStats;
        this.bowlingStats = bowlingStats;
        this.inningEnded = false;
    }

    public int getRunsScored() {
        return runsScored;
    }
    public void addRunsScored(int runsHit) {
        this.runsScored += runsHit;
    }

    public int getWicketsFell() {
        return wicketsFell;
    }
    public void addWicketsFell() {
        this.wicketsFell += 1;
    }

    public PlayerBattingStats getBatterStats(String batterId){
        return battingStats.get(batterId);
    }
    public PlayerBowlingStats getBowlerStats(String bowlerId){
        return bowlingStats.get(bowlerId);
    }

    public boolean checkIfInningEnded(){
        return this.inningEnded;
    }
    public void setInningEnded(){
        this.inningEnded = true;
    }

    public int getOversBowled() {
        return oversBowled;
    }
    public void setOversBowled(int oversBowled) {
        this.oversBowled = oversBowled;
    }

    public int getExcessBallsbowled() {
        return excessBallsBowled;
    }
    public void setExcessBallsbowled(int excessBallsbowled) {
        this.excessBallsBowled = excessBallsbowled;
    }

    public String getBattingTeam() {
        return battingTeam;
    }
    public LinkedHashMap<String,PlayerBattingStats> getBattingStats(){
        return battingStats;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }
    public LinkedHashMap<String,PlayerBowlingStats> getBowlingStats(){
        return bowlingStats;
    }
}
