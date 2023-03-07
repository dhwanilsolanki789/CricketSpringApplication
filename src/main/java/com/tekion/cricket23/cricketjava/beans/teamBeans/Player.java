package com.tekion.cricket23.cricketjava.beans.teamBeans;

public class Player {
    private final String playerName;
    private double[] hitProbability;

    public Player(String name){
        this.playerName = name;
    }

    public int playBall(){
        double probability = Math.random();
        int ballScore;

        //Run scoring probability
        if (probability <= this.hitProbability[0]) {
            System.out.println("Good delivery! Dot ball.");
            ballScore = 0;
        } else if (probability <= this.hitProbability[1]) {
            System.out.println(this.getName() + " takes a single, 1 run.");
            ballScore = 1;
        } else if (probability <= this.hitProbability[2]) {
            System.out.println(this.getName() + " takes a quick double, 2 runs.");
            ballScore = 2;
        } else if (probability <= this.hitProbability[3]) {
            System.out.println(this.getName() + " takes 3 runs.");
            ballScore = 3;
        } else if (probability <= this.hitProbability[4]) {
            System.out.println(this.getName() + " hits a Boundary! 4 runs.");
            ballScore = 4;
        } else if (probability <= this.hitProbability[5]) {
            System.out.println(this.getName() + " hits a Maximum! 6 runs.");
            ballScore = 6;
        } else {
            System.out.print("Wicket! " + this.getName() + " had to go back ");
            return 7;
        }
        return ballScore;
    }

    public String getName(){
        return playerName;
    }
    public void setHitProbability(double[] hitProbability) {
        this.hitProbability = hitProbability;
    }

    @Override
    public String toString() {
        return "Player{" +
                " playerName = '" + playerName + '\'' +
                '}';
    }
}