package com.tekion.cricket23.cricketjava.beans.statsBeans;

import com.tekion.cricket23.cricketjava.beans.teamBeans.Bowler;
import com.tekion.cricket23.cricketjava.beans.teamBeans.Player;

public class PlayerStats {
    private final Player player;
    private final BattingStats battingStats;
    private BowlingStats bowlingStats;

    public PlayerStats(Player player){
        this.player = player;
        this.battingStats = new BattingStats(player);
        if(player instanceof Bowler){
            this.bowlingStats = new BowlingStats(player);
        }
    }

    public BattingStats getBattingStats() {
        return this.battingStats;
    }
    public String printBattingStats(){
        return battingStats.toString();
    }

    public BowlingStats getBowlingStats() {
        return this.bowlingStats;
    }

    public String printBowlingStats(){
        return bowlingStats.toString();
    }

    public int getRuns(){
        return battingStats.getRunsScored();
    }

    public int getWickets(){
        return bowlingStats.getWicketsTaken();
    }

    public int getRunsConceded(){
        return bowlingStats.getRunsConceded();
    }

    @Override
    public String toString() {
        String toString =  "PlayerStats{\n" + "battingStats = " + battingStats.toString();
        if(this.player instanceof Bowler){
            toString += ", \n bowlingStats = " + bowlingStats.toString();
        } else {
            toString += "\n}";
        }
        return toString;
    }
}
