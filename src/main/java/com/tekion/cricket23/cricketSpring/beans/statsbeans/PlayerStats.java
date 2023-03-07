package com.tekion.cricket23.cricketSpring.beans.statsbeans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;

@Document(collection = "playerStats")
public class PlayerStats {
    @Id
    private String id;
    private String playerId;
    private String playerName;
    LinkedHashMap<String,StatsInMatch> statsInMatches;

    public PlayerStats(){}
    public PlayerStats(String id, String name, LinkedHashMap<String,StatsInMatch> statsInMatches){
        this.playerId = id;
        this.playerName = name;
        this.statsInMatches = statsInMatches;
    }
    public PlayerStats(String id, String name){
        this.playerId = id;
        this.playerName = name;
        this.statsInMatches = new LinkedHashMap<>();
    }

    public String getPlayerId() {
        return playerId;
    }
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public StatsInMatch findStatsInGivenMatch(String matchId){
        return statsInMatches.get(matchId);
    }
    public void addStatsInMatch(String matchId,StatsInMatch statsInMatch){
        statsInMatches.put(matchId,statsInMatch);
    }
    public void removeStatsOfAMatch(String matchId){
        statsInMatches.remove(matchId);
    }
}
