package com.tekion.cricket23.cricketjava.beans.teamBeans;


public class Batter extends Player {

    public Batter(String name) {
        super(name);
        this.setHitProbability(new double[]{0.273, 0.612, 0.681, 0.683, 0.819, 0.929});
    }
}