package com.tekion.cricket23.cricketjava.beans.statsBeans;

import com.tekion.cricket23.cricketjava.beans.matchBeans.Scorecard;
import com.tekion.cricket23.cricketjava.beans.teamBeans.Bowler;
import com.tekion.cricket23.cricketjava.beans.teamBeans.Player;
import com.tekion.cricket23.cricketjava.beans.teamBeans.Team;

import java.util.LinkedHashMap;
import java.util.Map;

public class MatchStats {
    private final Scorecard matchScoreboard;
    private final Team team1;
    private final Team team2;
    LinkedHashMap<Player,PlayerStats> team1Stats;
    LinkedHashMap<Player,PlayerStats> team2Stats;

    public MatchStats(Scorecard matchScoreboard, Team team1, Team team2) {
        this.matchScoreboard = matchScoreboard;
        this.team1 = team1;
        this.team2 = team2;
        createStats(team1,team2);
    }

    private void createStats(Team team1, Team team2) {
        this.team1Stats = createTeamStats(team1.getPlayers());
        this.team2Stats = createTeamStats(team2.getPlayers());
    }

    private LinkedHashMap<Player, PlayerStats> createTeamStats(Player[] players) {
        LinkedHashMap<Player,PlayerStats> teamStats = new LinkedHashMap<>();
        for(Player player : players){
            teamStats.put(player,new PlayerStats(player));
        }
        return teamStats;
    }

    public LinkedHashMap<Player,BattingStats> getTeamBattingStats(Team team){
        LinkedHashMap<Player,BattingStats> battingStats = new LinkedHashMap<>();
        for(Map.Entry<Player,PlayerStats> entry : getTeamStats(team).entrySet()){
            PlayerStats playerStats = entry.getValue();
            battingStats.put(entry.getKey(),playerStats.getBattingStats());
        }
        return battingStats;
    }

    public LinkedHashMap<Player,BowlingStats> getTeamBowlingStats(Team team){
        LinkedHashMap<Player,BowlingStats> bowlingStats = new LinkedHashMap<>();
        for(Map.Entry<Player,PlayerStats> entry : getTeamStats(team).entrySet()){
            if(entry.getKey() instanceof Bowler){
                PlayerStats playerStats = entry.getValue();
                bowlingStats.put(entry.getKey(),playerStats.getBowlingStats());
            }
        }
        return bowlingStats;
    }

    public LinkedHashMap<Player,PlayerStats> getTeamStats(Team team) {
        if(team == team1){
            return this.team1Stats;
        } else {
            return this.team2Stats;
        }
    }

    @Override
    public String toString() {
        return "MatchStats{\n" +
                "team1 = " + team1 +
                ",\nteam2 = " + team2 +
                ",\nteam1Stats = " + team1Stats +
                ",\nteam2Stats = " + team2Stats +
                '}';
    }
}
