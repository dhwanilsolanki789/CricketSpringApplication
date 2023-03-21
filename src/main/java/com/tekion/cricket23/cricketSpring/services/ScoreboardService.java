package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.Inning;
import com.tekion.cricket23.cricketSpring.beans.matchbeans.Scoreboard;
import com.tekion.cricket23.cricketSpring.beans.teambeans.*;
import com.tekion.cricket23.cricketSpring.dtos.resdtos.InningRes;

import java.util.List;


public interface ScoreboardService {
    Scoreboard createScoreboard(String matchId);
    Inning createInning(Scoreboard scoreboard, int inningNo, Team battingTeam, Team bowlingTeam);
    void updateRuns(Inning inning, int runs, Player batter, Bowler bowler);
    void updateWickets(Inning inning, Player batter, Bowler bowler);
    void updateInning(Inning inning, int over, int ball);
    void postInningUpdation(Scoreboard scoreboard);
    void storeScoreboardData(Scoreboard scoreboard, Team[] teams);
    void printPostOverStats(Inning inning, Player[] activeBatters, Bowler bowler);
    void printPostInningStats(Inning inning);
    List<InningRes> getMatchDetails(Scoreboard scoreboard);
    void printScoreboard(Scoreboard scoreboard);
}
