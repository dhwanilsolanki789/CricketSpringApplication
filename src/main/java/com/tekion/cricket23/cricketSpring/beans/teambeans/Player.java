package com.tekion.cricket23.cricketSpring.beans.teambeans;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
public class Player {
    @Id
    private String id;
    private String playerName;
    private String playerType;
    private String teamName;
    @Transient
    private double[] HIT_PROBABILITY;

    public Player(){}
    public Player(String name, String type,String teamName) {
        this.playerName = name;
        this.playerType = type;
        this.teamName = teamName;
    }

    public String getPlayerId() {
        return id;
    }

    public String getName() {
        return this.playerName;
    }
    public void setName(String name) { this.playerName = name; } //NOTE remove after test over
    public String getPlayerType() {
        return playerType;
    }
    public String getTeamName() { return teamName; }

    public void setHIT_PROBABILITY(double[] HIT_PROBABILITY) {
        this.HIT_PROBABILITY = HIT_PROBABILITY;
    }
    public double[] getHIT_PROBABILITY() {
        return HIT_PROBABILITY;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", playerName='" + playerName + '\'' +
                ", playerType='" + playerType + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
