package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.*;
import com.tekion.cricket23.cricketSpring.beans.statsbeans.*;
import com.tekion.cricket23.cricketSpring.beans.teambeans.*;
import com.tekion.cricket23.cricketSpring.utils.CricketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Service
public class ScoreboardServiceImpl implements ScoreboardService{
    TeamService teamService;
    StatsService statsService;

    @Autowired
    public ScoreboardServiceImpl(TeamService teamService, StatsService statsService){
        this.teamService = teamService;
        this.statsService = statsService;
    }

    public Scoreboard createScoreboard(String matchId){
        return new Scoreboard(matchId);
    }

    public Inning createInning(Scoreboard scoreboard,int inningNo,Team battingTeam, Team bowlingTeam) {
        LinkedHashMap<String, PlayerBattingStats> battingStats =
                statsService.createTeamBattingStats(battingTeam);
        LinkedHashMap<String, PlayerBowlingStats> bowlingStats =
                statsService.createTeamBowlingStats(bowlingTeam);
        Inning inning = new Inning(battingTeam.getTeamName(), bowlingTeam.getTeamName(),
                battingStats,bowlingStats);
        if(inningNo == 1){
            scoreboard.setFirstInning(inning);
        } else {
            scoreboard.setSecondInning(inning);
        }
        return inning;
    }

    public void updateRuns(Inning currInning, int runsHit,Player currBatter, Bowler currBowler) {
        currInning.addRunsScored(runsHit);
        updatePlayers(currInning,currBatter,currBowler,runsHit,false);
    }

    public void updateWickets(Inning currInning, Player currBatter, Bowler currBowler) {
        currInning.addWicketsFell();
        updatePlayers(currInning,currBatter,currBowler,0,true);
        logWicketStats(currInning,currBowler.getName());
    }

    public void updatePlayers(Inning currInning, Player batter, Bowler bowler, int runs, boolean wicketFell) {
        PlayerBattingStats batterStats = currInning.getBatterStats(batter.getPlayerId());
        batterStats.updateBattingStats(runs);
        PlayerBowlingStats bowlerStats = currInning.getBowlerStats(bowler.getPlayerId());
        bowlerStats.updateBowlingStats(runs,wicketFell);
    }

    public void updateInning(Inning currInning, int currOver, int currBall) {
        if(currBall == 6){
            currInning.setOversBowled(currOver+1);
        } else {
            currInning.setOversBowled(currOver);
            currInning.setExcessBallsbowled(currBall);
        }
    }

    public void postInningUpdation(Scoreboard scoreboard){
        Inning firstInning = scoreboard.getInning(1);
        scoreboard.setMatchTarget(firstInning.getRunsScored());
        logMatchTarget(firstInning);
    }

    public void storeScoreboardData(Scoreboard scoreboard, Team[] teams) {
       statsService.storeAllPlayersStats(scoreboard,teams);
    }

    public void logWicketStats(Inning currInning, String currBowlerName){
        System.out.println(currInning.getBattingTeam() + " are " + (currInning.getWicketsFell())
                + " down as " + currBowlerName + " strikes!");
    }

    public void printPostOverStats(Inning currInning, Player[] currBatters, Bowler currBowler) {
        PlayerBattingStats[] activeBatterStats = { currInning.getBatterStats(currBatters[0].getPlayerId()),
                currInning.getBatterStats(currBatters[1].getPlayerId())};
        PlayerBowlingStats activeBowlerStats = currInning.getBowlerStats(currBowler.getPlayerId());
        System.out.print(currBowler.getName() + " - " +
                activeBowlerStats.getWicketsTaken() + "/" + activeBowlerStats.getRunsConceded());
        System.out.print(" | " + currInning.getBattingTeam() + " "
                + currInning.getRunsScored() + "/" + currInning.getWicketsFell());
        System.out.print(" ( " + currBatters[0].getName() + " - " + activeBatterStats[0].getRuns() + "*");
        System.out.println("  " + currBatters[1].getName() + " - " + activeBatterStats[1].getRuns() + " )");
    }

    public void printPostInningStats(Inning currInning) {
        System.out.println(currInning.getBattingTeam()
                + " - " + currInning.getRunsScored() + "/" + currInning.getWicketsFell()
                + " (" + currInning.getOversBowled() + "." + currInning.getExcessBallsbowled() + " Overs)");
    }

    public void logMatchTarget(Inning inning){
        System.out.println((inning.getRunsScored() + 1) + " is the target for " + inning.getBattingTeam());
        CricketUtils.printDottedLine();
    }

    public void printInningStats(Inning inning){
        System.out.println(inning.getBattingTeam() + " Innings - "
                + inning.getRunsScored() + "/" + inning.getWicketsFell()
                + " (" + inning.getOversBowled() + "." + inning.getExcessBallsbowled()+ " Overs)");
        CricketUtils.printBlankLine();
        printInningBattingStats(inning);
        System.out.println("Total - " + inning.getRunsScored() + " (" + inning.getWicketsFell() + " wickets)");
        CricketUtils.printBlankLine();
        printInningBowlingStats(inning);
        CricketUtils.printDottedLine();
    }

    public String printInningBattingStats(Inning inning) {
        String battingStatsString = "";
        LinkedHashMap<String,PlayerBattingStats> battingStats = inning.getBattingStats();
        Set<String> batterIds = battingStats.keySet();
        for(String batterId : batterIds){
            PlayerBattingStats batterStats = battingStats.get(batterId);
            battingStatsString += batterStats.getPlayerName() + " " + batterStats.toString() + "\n";
        }
        System.out.println(battingStatsString);
        return battingStatsString;
    }

    public String printInningBowlingStats(Inning inning) {
        String bowlingStatsString = "";
        LinkedHashMap<String,PlayerBowlingStats> bowlingStats = inning.getBowlingStats();
        Set<String> bowlerIds = bowlingStats.keySet();
        for(String bowlerId : bowlerIds){
            PlayerBowlingStats bowlerStats = bowlingStats.get(bowlerId);
            bowlingStatsString += bowlerStats.getPlayerName() + " " + bowlerStats.toString() + "\n";
        }
        System.out.println(bowlingStatsString);
        return bowlingStatsString;
    }

    public String getMatchDetails(Scoreboard scoreboard){
        String matchDetails = "Inning 1 { \n";
        matchDetails += getInningString(scoreboard.getInning(1));
        matchDetails += "}\nInning 2 { \n";
        matchDetails += getInningString(scoreboard.getInning(2)) + "}\n";
        return matchDetails;
    }

    public String getInningString(Inning inning){
        String inningString = "RunsScored : " + inning.getRunsScored() + "\n"
                + "WicketsFell : " + inning.getWicketsFell() + "\n"
                + "OversBowled : " + inning.getOversBowled() + "\n"
                + "ExcessBallsBowled : " + inning.getExcessBallsbowled() + "\n"
                + "BattingTeam : " + inning.getBattingTeam() + "\n"
                + "BowlingTeam : " + inning.getBowlingTeam() + "\n"
                + "InningBattingStats : {\n";
        inningString += printInningBattingStats(inning);
        inningString += "}\nInningBowlingStats : {\n";
        inningString += printInningBowlingStats(inning) + "}\n";
        return inningString;
    }

    public void printScoreboard(Scoreboard scoreboard){
        System.out.println("ScoreCard");
        printInningStats(scoreboard.getInning(1));
        printInningStats(scoreboard.getInning(2));
    }
}
