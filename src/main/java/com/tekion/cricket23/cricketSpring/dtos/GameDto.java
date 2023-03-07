package com.tekion.cricket23.cricketSpring.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GameDto {
    @NotNull(message = "Team 1 Name cannot be null")
    String team1;
    @NotNull(message = "Team 2 Name cannot be null")
    String team2;
    @NotBlank(message = "Number of Matches is required")
    int noOfMatches;
    @NotBlank(message = "Number of Overs is required")
    int noOfOvers;

    public String getTeam1Name() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2Name() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getNoOfMatches() {
        return noOfMatches;
    }

    public void setNoOfMatches(int noOfMatches) {
        this.noOfMatches = noOfMatches;
    }

    public int getNoOfOvers() {
        return noOfOvers;
    }

    public void setNoOfOvers(int noOfOvers) {
        this.noOfOvers = noOfOvers;
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", noOfMatches=" + noOfMatches +
                ", noOfOvers=" + noOfOvers +
                '}';
    }
}
