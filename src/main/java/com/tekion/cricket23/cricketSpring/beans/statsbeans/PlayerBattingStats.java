package com.tekion.cricket23.cricketSpring.beans.statsbeans;

import org.springframework.data.annotation.Transient;

public class PlayerBattingStats {
    @Transient
    private String playerName;
    private int runs;
    private int ballsPlayed;
    private int foursHit;
    private int sixesHit;

    public PlayerBattingStats(){}
    public PlayerBattingStats(String playerName) {
        this.playerName = playerName;
        this.runs = this.ballsPlayed = 0;
        this.foursHit = this.sixesHit = 0;
    }

    public void updateBattingStats(int runsHit){
        addRunsScored(runsHit);
        addBallPlayed();
        if(runsHit == 4){
            addFour();
        }
        if(runsHit == 6){
            addSix();
        }
    }

    public int getRuns() {
        return runs;
    }
    public void addRunsScored(int runsHit) {
        this.runs += runsHit;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }
    public void addBallPlayed() {
        this.ballsPlayed += 1;
    }

    public void addFour() {
        this.foursHit += 1;
    }
    public void addSix() {
        this.sixesHit += 1;
    }

    @Override
    public String toString() {
        return runs + "(" + ballsPlayed + "), " +
                foursHit + " Fours, " + sixesHit + " Sixes";
    }
}
