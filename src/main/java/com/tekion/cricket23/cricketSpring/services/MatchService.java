package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.Match;
import com.tekion.cricket23.cricketSpring.dtos.resdtos.MatchRes;

public interface MatchService {
    Match createMatch(String team1Id, String team2Id, int totalOvers);
    Match startMatch(String team1Id, String team2Id, int totalOvers);
    void playMatch(Match match);
    boolean checkIfMatchExists(String matchId);
    MatchRes obtainMatchDetails(String matchId);
    void deleteMatchData(String matchId);
}