package com.tekion.cricket23.cricketjava.beans.teamBeans;


public class Bowler extends Player {
    private int runsConceded;
    private int wicketsTaken;
    private int ballsBowled;

    public Bowler(String name) {
        super(name);
        this.ballsBowled = 0;
        this.wicketsTaken = 0;
        this.runsConceded = 0;
        this.setHitProbability(new double[]{0.376, 0.625, 0.672, 0.673, 0.816, 0.884});
    }

    public int getRunsConceded() {
        return runsConceded;
    }
    public void addRunsConceded(int runs) {
        this.runsConceded += runs;
    }
    public int getWicketsTaken() {
        return wicketsTaken;
    }
    public void incrementWicketsTaken() {
        this.wicketsTaken += 1;
    }
    public int getBallsBowled() {
        return ballsBowled;
    }
    public void addBallsBowled(int balls) {
        this.ballsBowled += balls;
    }
}