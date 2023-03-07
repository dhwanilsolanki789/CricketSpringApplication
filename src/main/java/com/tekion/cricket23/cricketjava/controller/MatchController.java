package com.tekion.cricket23.cricketjava.controller;

import com.tekion.cricket23.cricketjava.beans.matchBeans.Match;
import com.tekion.cricket23.cricketjava.beans.statsBeans.MatchStats;
import com.tekion.cricket23.cricketjava.beans.teamBeans.Team;
import com.tekion.cricket23.cricketjava.launcher.Utility;

public class MatchController {
    private final Match match;
    private Team battingTeam;
    private Team bowlingTeam;

    public MatchController(int overs, Team team1, Team team2) {
        this.match = new Match(overs, team1, team2);
    }

    public Team playGame() {
        setupGame();
        int inningScore = match.playInning(1, battingTeam, bowlingTeam);
        changeInning(inningScore);
        inningScore = match.playInning(2, battingTeam, bowlingTeam);
        match.printScoreboard(battingTeam,bowlingTeam);
        return match.getWinner();
    }

    public void setupGame() {
        Team[] toss = match.flipCoin();
        System.out.println("Coin flipped!");
        // batting & bowling team assigned
        battingTeam = toss[0];
        bowlingTeam = toss[1];
        match.createScorecard(battingTeam,bowlingTeam);
        //Inning commencement
        System.out.println(battingTeam.getTeamName() + " bats first!");
        Utility.printDottedLine();
    }

    public void changeInning(int inningScore) {
        //Set match target and second inning setup
        match.setMatchTarget(inningScore + 1);
        Team temp = battingTeam;     //bowling & batting team reassigned
        battingTeam = bowlingTeam;
        bowlingTeam = temp;
        System.out.println((inningScore + 1) + " is the target for " + battingTeam.getTeamName());
        Utility.printDottedLine();
    }

    public MatchStats getMatchStats(){
        return match.giveMatchStats();
    }
}