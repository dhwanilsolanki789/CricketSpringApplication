package com.tekion.cricket23.cricketSpring.dtos.resdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "response")
public class MatchRes {
    @JsonProperty(value = "totalOvers")
    private int totalOvers;
    @JsonProperty(value = "inning1")
    private InningRes inning1;
    @JsonProperty(value = "inning2")
    private InningRes inning2;
    @JsonProperty(value = "matchResult")
    private String matchResult;

    public MatchRes(int totalOvers) {
        this.totalOvers = totalOvers;
    }

    public void setInning1(InningRes inning1) {
        this.inning1 = inning1;
    }
    public void setInning2(InningRes inning2) {
        this.inning2 = inning2;
    }

    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }

    @Override
    public String toString() {
        return "MatchRes{" +
                "totalOvers=" + totalOvers +
                ", inning1=" + inning1 +
                ", inning2=" + inning2 +
                ", matchResult='" + matchResult + '\'' +
                '}';
    }
}
