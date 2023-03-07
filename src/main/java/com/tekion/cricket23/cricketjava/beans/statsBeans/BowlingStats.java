package com.tekion.cricket23.cricketjava.beans.statsBeans;

import com.tekion.cricket23.cricketjava.beans.teamBeans.Player;

public class BowlingStats {
    private final Player player;
    private int wicketsTaken;
    private int runsConceded;
    private int ballsBowled;

    public BowlingStats(Player player){
        this.player = player;
        this.wicketsTaken = this.runsConceded = 0;
        this.ballsBowled = 0;
    }

    public void updateBowlingStats(int runsConceded, boolean wicketFell){
        addRunsConceded(runsConceded);
        addBallsBowled();
        if(wicketFell){
            addWicketsTaken();
        }
    }

    public int getRunsConceded() {
        return this.runsConceded;
    }

    public void addRunsConceded(int runs) {
        this.runsConceded += runs;
    }

    public void addBallsBowled(){
        this.ballsBowled += 1;
    }

    public int getWicketsTaken() {
        return this.wicketsTaken;
    }

    public void addWicketsTaken(){
        this.wicketsTaken += 1;
    }

    @Override
    public String toString() {
        return player.getName() + " " +
                wicketsTaken + "/" + runsConceded +
                " (" + (ballsBowled/6) + "." + (ballsBowled%6)+ " Ovs)";
    }
}
