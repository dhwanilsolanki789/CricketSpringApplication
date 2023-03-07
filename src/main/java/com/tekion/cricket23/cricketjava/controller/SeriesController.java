package com.tekion.cricket23.cricketjava.controller;

import com.tekion.cricket23.cricketjava.beans.teamBeans.Team;
import com.tekion.cricket23.cricketjava.launcher.Utility;

public class SeriesController {
    private final Team team1, team2;
    private int matchCount;
    private final int totalMatches;
    private int team1Wins, team2Wins;
    private Team winner;

    public SeriesController(Team team1, Team team2, int matchCount){
        this.team1 = team1;
        this.team2 = team2;
        this.team1Wins = this.team2Wins = 0;
        this.matchCount = 1;
        this.totalMatches = matchCount;
    }

    public void playSeries(){
        for( ;matchCount <= totalMatches; matchCount++){
            System.out.println("Match " + matchCount + " | " +
                    team1.getTeamName() + " vs " + team2.getTeamName());
            playMatch();
            printPostMatchStats();
            if(seriesEndCheck()){
                printWinner();
                break;
            }
        }
    }

    private void printWinner() {
        if(winner != null){
            System.out.println(winner.getTeamName() + " wins the series!");
        } else {
            System.out.println("Series tied!");
        }
    }

    private void printPostMatchStats() {
        System.out.println(team1.getTeamName() + " " + team1Wins + " - "
                + team2.getTeamName() + " " + team2Wins);
        Utility.printBlankLine();
    }

    public void playMatch(){
        MatchController newMatch = new MatchController(20, team1, team2);
        Team winner = newMatch.playGame();
        incrementTeamWins(winner);
    }

    private void incrementTeamWins(Team winner) {
        if(winner == team1){
            team1Wins++;
        } else {
            team2Wins++;
        }
    }

    public boolean seriesEndCheck(){
        if(team1Wins > totalMatches/2 || team2Wins > totalMatches/2){
            if(team1Wins >= totalMatches/2){
                this.winner = team1;
            } else {
                this.winner = team2;
            }
            return true;
        }
        return this.matchCount == totalMatches;
    }

    public int getTeamWins(Team team){
        if(team == team1){
            return team1Wins;
        } else {
            return team2Wins;
        }
    }
}
