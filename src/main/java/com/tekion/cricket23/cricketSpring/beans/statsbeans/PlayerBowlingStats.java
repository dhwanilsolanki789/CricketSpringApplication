package com.tekion.cricket23.cricketSpring.beans.statsbeans;

public class PlayerBowlingStats {
    private String playerName;
    private int wicketsTaken;
    private int runsConceded;
    private int ballsBowled;

    public PlayerBowlingStats(){}
    public PlayerBowlingStats(String playerName){
        this.playerName = playerName;
        this.wicketsTaken = this.runsConceded = 0;
        this.ballsBowled = 0;
    }

    public void updateBowlingStats(int runsConceded, boolean wicketFell){
        addRunsConceded(runsConceded);
        addBallBowled();
        if(wicketFell){
            addWicketsTaken();
        }
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }
    public void addWicketsTaken() {
        this.wicketsTaken += 1;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRunsConceded() {
        return runsConceded;
    }
    public void addRunsConceded(int runs) {
        this.runsConceded += runs;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }
    public void addBallBowled() {
        this.ballsBowled += 1;
    }

    @Override
    public String toString() {
        return  wicketsTaken + "/" + runsConceded +
                " (" + (ballsBowled/6) + "." + (ballsBowled%6)+ " Ovs)";
    }
}
