package com.tekion.cricket23.cricketSpring.beans.seriesbeans;

public class MatchDao {
    private String matchId;
    private String matchResult;

    public MatchDao(String id, String result){
        this.matchId = id;
        this.matchResult = result;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getMatchResult() {
        return matchResult;
    }
}
