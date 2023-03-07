package com.tekion.cricket23.cricketSpring.beans.teambeans;

public class Bowler extends Player{
    public Bowler(){
        this.setHIT_PROBABILITY(new double[]{0.376, 0.625, 0.672, 0.673, 0.816, 0.884});
    }
    public Bowler(String name, String type,String teamName){
        super(name,type,teamName);
        this.setHIT_PROBABILITY(new double[]{0.376, 0.625, 0.672, 0.673, 0.816, 0.884});
    }
}
