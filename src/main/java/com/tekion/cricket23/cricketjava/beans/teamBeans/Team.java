package com.tekion.cricket23.cricketjava.beans.teamBeans;

import com.tekion.cricket23.cricketjava.launcher.Utility;

import java.util.Arrays;

public class Team {
    private final String teamName;
    private final int playerCount;
    private int battersCount;
    private int bowlersCount;
    private Player[] players;

    public Team(String name, int playerCount) {
        this.teamName = name;
        this.playerCount = playerCount;
        createTeam();
    }

    public void createTeam(){
        players = new Player[this.playerCount];
        this.battersCount = 6;
        this.bowlersCount = 5;
        System.out.println("Team " + this.teamName);
        for(int i=1; i<=battersCount; i++){
            System.out.print("Enter batter " + i + " name: ");
            players[i-1] = new Batter(Utility.getNameInput());
        }
        for(int i=1; i<=bowlersCount; i++){
            System.out.print("Enter bowler " + i + " name: ");
            players[(battersCount-1)+i] = new Bowler(Utility.getNameInput());
        }
    }


    public Player[] assignOpeners(){
        return new Player[]{ this.players[0], this.players[1] };
    }

    public Player assignBatter(int wickets){
        return this.players[wickets+1];
    }
    public Bowler assignBowler(int over){
        over = (over)%bowlersCount;
        return (Bowler) players[battersCount+over];
    }

    public Player[] getPlayers(){
        return this.players;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    @Override
    public String toString() {
        return "Team{\n" +
                "teamName='" + teamName + '\'' +
                ",\nplayerCount=" + playerCount +
                ",\nplayers=" + Arrays.toString(players) +
                '}';
    }

}
