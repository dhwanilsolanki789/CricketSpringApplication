package com.tekion.cricket23.cricketSpring.beans.seriesbeans;

import com.tekion.cricket23.cricketSpring.beans.teambeans.Team;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "series")
public class Series {
    @Id
    String id;
    private String team1Id;
    private String team1;
    private String team2;
    private String team2Id;
    private int totalMatches;
    private List<MatchDao> matches;
    private String seriesResult;
    @Transient
    private int team1Wins;
    @Transient
    private int team2Wins;

    public Series(){}
    public Series(String team1Id, String team1Name, String team2Id, String team2Name,
                  int noOfMatches, List<MatchDao> matches){
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.team1 = team1Name;
        this.team2 = team2Name;
        this.totalMatches = noOfMatches;
        this.matches = matches;
    }
    public Series(String team1Id, String team1Name, String team2Id, String team2Name,int noOfMatches){
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.team1 = team1Name;
        this.team2 = team2Name;
        this.totalMatches = noOfMatches;
        this.matches = new ArrayList<>();
        this.team1Wins = this.team2Wins = 0;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public String getTeam1Id() {
        return team1Id;
    }
    public String getTeam2Id(){
        return team2Id;
    }

    public String getTeam1Name() {
        return team1;
    }
    public String getTeam2Name() {
        return team2;
    }

    public void incrementTeamWins(Team winner){
        if(Objects.equals(winner.getTeamName(), team1)){
            team1Wins++;
        } else {
            team2Wins++;
        }
    }

    public int getTeam1Wins() {
        return team1Wins;
    }
    public int getTeam2Wins() {
        return team2Wins;
    }

    public void addMatch(MatchDao match){
        matches.add(match);
    }

    public String getSeriesResult() {
        return seriesResult;
    }
    public void setSeriesResult(String seriesResult) {
        this.seriesResult = seriesResult;
    }
}
