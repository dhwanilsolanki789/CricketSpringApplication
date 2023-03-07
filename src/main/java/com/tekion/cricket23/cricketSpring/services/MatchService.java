package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.Inning;
import com.tekion.cricket23.cricketSpring.beans.matchbeans.Match;
import com.tekion.cricket23.cricketSpring.beans.teambeans.Team;

public interface MatchService {
    Match createMatch(String team1Id, String team2Id, int totalOvers);
    Match startMatch(String team1Id, String team2Id, int totalOvers);
    Team[] initiateMatch(Match match);
    Team[] toss(Match match);
    void playMatch(Match match);
    void playInning(Match match, int inningNo, Team battingTeam, Team bowlingTeam);
    void initiateInning(Match match, int inningNo, Team battingTeam, Team bowlingTeam);
    void implementInning(int inningNo, Match match, Team battingTeam, Team bowlingTeam);
    void changeInning(Match match);
    void implementOver(Match match, Team battingTeam, int currOver, int inningNo);
    void changeOver(Match match, int overNo);
    int bowlBall(Match match);
    void updateMatchAfterBall(Match match, int ballOutcome, Team battingTeam);
    boolean checkInningEnded(Match match, int inningNo, int oversBowled, int ballsBowled);
    boolean checkIfAllWicketsDown(Inning inning);
    void changeStrike(Match match);
    void updateMatchInstance(Match match);
    void postMatchUpdation(Match match, Team[] teams);
    void calculateResults(Match match);
    void printResults(Match match);
    String obtainMatchDetails(String matchId);
    void deleteMatchData(String matchId);
    void deletePlayerStatsOfTeam(String matchId, String teamId);
}