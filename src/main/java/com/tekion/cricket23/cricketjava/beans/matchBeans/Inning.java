package com.tekion.cricket23.cricketjava.beans.matchBeans;

import com.tekion.cricket23.cricketjava.beans.statsBeans.BattingStats;
import com.tekion.cricket23.cricketjava.beans.statsBeans.BowlingStats;
import com.tekion.cricket23.cricketjava.beans.teamBeans.Bowler;
import com.tekion.cricket23.cricketjava.beans.teamBeans.Player;
import com.tekion.cricket23.cricketjava.beans.teamBeans.Team;
import com.tekion.cricket23.cricketjava.launcher.Utility;

import java.util.LinkedHashMap;
import java.util.Set;

public class Inning {
    private int runsScored;
    private int wicketsFell;
    private final Team battingTeam;
    private final Team bowlingTeam;
    private final LinkedHashMap<Player, BattingStats> battingStats;
    private final LinkedHashMap<Player, BowlingStats> bowlingStats;
    private int oversBowled;
    private int ExcessBallsBowled;
    private boolean inningEnded;

    public Inning(Team battingTeam, Team bowlingTeam,LinkedHashMap<Player,
            BattingStats> battingStats, LinkedHashMap<Player, BowlingStats> bowlingStats){
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.runsScored = 0;
        this.wicketsFell =0;
        this.oversBowled = this.ExcessBallsBowled = 0;
        this.inningEnded = false;
        this.battingStats = battingStats;
        this.bowlingStats = bowlingStats;
    }

    public void printInningScore(){
        System.out.println(battingTeam.getTeamName() + " - " + this.runsScored + "/" + this.wicketsFell + " ("
                + this.oversBowled + "." + this.ExcessBallsBowled + " Overs)");
    }

    public void printPostOverStats(Bowler currBowler, Player currBatterOnStrike, Player currBatterOffStrike){
        BattingStats[] activeBatterStats = { battingStats.get(currBatterOnStrike),
                battingStats.get(currBatterOffStrike)};
        BowlingStats activeBowlerStats = bowlingStats.get(currBowler);
        System.out.print(currBowler.getName() + " - " +
                activeBowlerStats.getWicketsTaken() + "/" + activeBowlerStats.getRunsConceded());
        System.out.print(" | " + battingTeam.getTeamName() + " " + this.runsScored + "/" + this.wicketsFell);
        System.out.print(" ( " + currBatterOnStrike.getName() + " - " + activeBatterStats[0].getRunsScored() + "*");
        System.out.println("  " + currBatterOffStrike.getName() + " - " + activeBatterStats[1].getRunsScored() + " )");
        Utility.printBlankLine();
    }

    public void updateRuns(int runsHit, Player batter, Bowler bowler){
        addRunsScored(runsHit);
        updatePlayers(batter,bowler,runsHit,false);
    }

    public void updateWickets(Player batter,Bowler bowler){
        System.out.println("after making " + battingStats.get(batter).getRunsScored() + " runs!");
        addWicketsFell();
        updatePlayers(batter,bowler,0,true);
    }

    public void printInningStats(){
        //Print Inning Summary
        System.out.println(battingTeam.getTeamName() + " Innings - " + this.runsScored + "/" + this.wicketsFell + " ("
                + this.oversBowled + "." + this.ExcessBallsBowled + " Overs)");
        Utility.printBlankLine();
        printBattingStats();
        //Print Team Total
        System.out.println("Total - " + this.runsScored + " (" + this.wicketsFell + " wickets)");
        Utility.printBlankLine();
        printBowlingStats();
        Utility.printDottedLine();
    }
    public void printBattingStats(){
        //Print each player batting stats
        Set<Player> batters = battingStats.keySet();
        for(Player batter : batters){
            BattingStats batterStats = battingStats.get(batter);
            System.out.println(batterStats.toString());
        }
    }

    public void printBowlingStats(){
        Set<Player> bowlers = bowlingStats.keySet();
        for(Player bowler : bowlers){
            BowlingStats bowlerStats = bowlingStats.get(bowler);
            System.out.println(bowlerStats.toString());
        }
    }

    public void updatePlayers(Player batter, Bowler bowler, int runs, boolean wicketFell){
        BattingStats batterStats = battingStats.get(batter);
        batterStats.updateBattingStats(runs);
        BowlingStats bowlerStats = bowlingStats.get(bowler);
        bowlerStats.updateBowlingStats(runs,wicketFell);
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void addRunsScored(int runsHit) {
        this.runsScored += runsHit;
    }

    public int getWicketsFell() {
        return wicketsFell;
    }

    public void addWicketsFell() {
        this.wicketsFell += 1;
    }

    public int getOversBowled() {
        return oversBowled;
    }

    public void setOversBowled(int oversBowled) {
        this.oversBowled = oversBowled;
    }

    public boolean checkInningEnded() {
        return this.inningEnded;
    }

    public void setInningEnded(){
        this.inningEnded = true;
    }

    public int getExcessBallsBowled() {
        return ExcessBallsBowled;
    }

    public void setExcessBallsBowled(int excessBallsBowled) {
        if(excessBallsBowled == 6){
            int ovs = getOversBowled();
            setOversBowled(ovs+1);
            return;
        }
        this.ExcessBallsBowled = excessBallsBowled;
    }

    @Override
    public String toString() {
        return "Inning {" +
                "runsScored =" + runsScored +
                ", wicketsFell =" + wicketsFell +
                ", battingTeamStats =" + battingStats.toString() +
                ", bowlingTeamStats =" + bowlingStats.toString() +
                ", oversBowled =" + oversBowled +
                ", ExcessBallsBowled =" + ExcessBallsBowled +
                '}';
    }
}