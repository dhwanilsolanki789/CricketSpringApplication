package com.tekion.cricket23.cricketjava.beans.statsBeans;

import com.tekion.cricket23.cricketjava.beans.teamBeans.Player;

public class BattingStats {
    private final Player player;
    private int runs;
    private int ballsPlayed;
    private int foursHit;
    private int sixesHit;


    public BattingStats(Player player){
        this.player = player;
        this.runs = this.ballsPlayed = 0;
        this.foursHit = this.sixesHit = 0;
    }

    public void updateBattingStats(int runsHit){
        addRunsScored(runsHit);
        addBallsPlayed();
        if(runsHit == 4){
            addFour();
        }
        if(runsHit == 6){
            addSix();
        }
    }

    public void addRunsScored(int runsHit) {
        this.runs += runsHit;
    }

    public int getRunsScored() {
        return this.runs;
    }

    public void addBallsPlayed() {
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
        return player.getName() + " " +
                runs + "(" + ballsPlayed + "), " +
                foursHit + " Fours, " + sixesHit + " Sixes";
    }
}
