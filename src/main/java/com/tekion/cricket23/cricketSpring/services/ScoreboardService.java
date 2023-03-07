package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.Inning;
import com.tekion.cricket23.cricketSpring.beans.matchbeans.Scoreboard;
import com.tekion.cricket23.cricketSpring.beans.teambeans.*;


public interface ScoreboardService {
    Scoreboard createScoreboard(String matchId);
    Inning createInning(Scoreboard scoreboard, int inningNo, Team battingTeam, Team bowlingTeam);
    void updateRuns(Inning inning, int runs, Player batter, Bowler bowler);
    void updateWickets(Inning inning, Player batter, Bowler bowler);
    void updatePlayers(Inning inning, Player batter, Bowler bowler, int runs, boolean wicketFell);
    void updateInning(Inning inning, int over, int ball);
    void postInningUpdation(Scoreboard scoreboard);
    void storeScoreboardData(Scoreboard scoreboard, Team[] teams);
    void logWicketStats(Inning inning, String bowlerName);
    void printPostOverStats(Inning inning, Player[] activeBatters, Bowler bowler);
    void printPostInningStats(Inning inning);
    void logMatchTarget(Inning inning);
    void printInningStats(Inning inning);
    String printInningBattingStats(Inning inning); //todo think about one responsibility for impl
    String printInningBowlingStats(Inning inning);  //todo think about one responsibility for impl
    String getMatchDetails(Scoreboard scoreboard);  //todo think about a better impl
    String getInningString(Inning inning);  //todo think about one responsibility for impl
    void printScoreboard(Scoreboard scoreboard);
}
