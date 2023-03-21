package com.tekion.cricket23.cricketSpring.beans.matchbeans;

import org.springframework.data.annotation.Id;

public class Scoreboard {
    @Id
    private String id;
    private final String matchId;
    private Inning inning1;
    private Inning inning2;
    private int matchTarget;

    public Scoreboard(String matchId) {
        this.matchId = matchId;
    }

    public Inning getInning(int inningNo) {
        if(inningNo == 1){
            return inning1;
        } else {
            return inning2;
        }
    }

    public void setFirstInning(Inning inning){
        this.inning1 = inning;
    }
    public void setSecondInning(Inning inning) {
        this.inning2 = inning;
    }

    public int getMatchTarget(){
        return this.matchTarget;
    }
    public void setMatchTarget(int score){
        this.matchTarget = score;
    }

    public String getMatchId() {
        return matchId;
    }

    @Override
    public String toString() {
        return "Scoreboard{" +
                "id='" + id + '\'' +
                ", matchId='" + matchId + '\'' +
                ", inning1=" + inning1 +
                ", inning2=" + inning2 +
                '}';
    }
}
