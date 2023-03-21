package com.tekion.cricket23.cricketSpring.beans.matchbeans;

import com.tekion.cricket23.cricketSpring.beans.teambeans.Bowler;
import com.tekion.cricket23.cricketSpring.beans.teambeans.Player;
import com.tekion.cricket23.cricketSpring.beans.teambeans.Team;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "matches")
public class Match {
    @Id
    private String matchId;
    private final String team1Id;
    private final String team2Id;
    private final int totalOvers;
    private Scoreboard scoreboard;
    private String matchResult;
    private String winner;

    @Transient
    private static int TOTAL_WICKETS;
    @Transient
    private Player currBatterOnStrike;
    @Transient
    private Player currBatterOffStrike;
    @Transient
    private Bowler currBowler;
    @Transient
    private Inning currInning;


    public Match(String team1Id, String team2Id, int totalOvers){
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.totalOvers = totalOvers;
        TOTAL_WICKETS = Team.getPlayerCount() - 1;
    }

    public Player getCurrBatterOnStrike() {
        return currBatterOnStrike;
    }
    public void setCurrBatterOnStrike(Player currBatterOnStrike) {
        this.currBatterOnStrike = currBatterOnStrike;
    }

    public Player getCurrBatterOffStrike() {
        return currBatterOffStrike;
    }
    public void setCurrBatterOffStrike(Player currBatterOffStrike) {
        this.currBatterOffStrike = currBatterOffStrike;
    }

    public Bowler getCurrBowler() {
        return currBowler;
    }
    public void setCurrBowler(Bowler currBowler) {
        this.currBowler = currBowler;
    }

    public Inning getCurrInning(){
        return currInning;
    }
    public void setCurrInning(Inning inning){
        this.currInning = inning;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getTeam1Id(){
        return this.team1Id;
    }
    public String getTeam2Id(){
        return this.team2Id;
    }

    public int getTotalOvers() {
        return totalOvers;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public int getTarget(){
        return scoreboard.getMatchTarget();
    }

    public String getWinner(){
        return winner;
    }
    public void setWinner(String team){
        winner = team;
    }

    public String getMatchResult(){
        return this.matchResult;
    }
    public void setMatchResult(String result){
        this.matchResult = result;
    }

    public void setBattersNull() {
        currBatterOnStrike = null;
        currBatterOffStrike = null;
    }

    public static int getTotalWickets(){
        return TOTAL_WICKETS;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId='" + matchId + '\'' +
                ", team1Id='" + team1Id + '\'' +
                ", team2Id='" + team2Id + '\'' +
                ", totalOvers=" + totalOvers +
                ", scoreboard=" + scoreboard +
                ", matchResult='" + matchResult + '\'' +
                '}';
    }
}
