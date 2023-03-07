package com.tekion.cricket23.cricketSpring.beans.teambeans;

public class Batter extends Player{
    public Batter(){
        this.setHIT_PROBABILITY(new double[]{0.273, 0.612, 0.681, 0.683, 0.819, 0.929});
    }
    public Batter(String name, String type,String teamName){
        super(name,type,teamName);
        this.setHIT_PROBABILITY(new double[]{0.273, 0.612, 0.681, 0.683, 0.819, 0.929});
    }
}
