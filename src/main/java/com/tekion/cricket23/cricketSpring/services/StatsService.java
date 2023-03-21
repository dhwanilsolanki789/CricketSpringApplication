package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.Scoreboard;
import com.tekion.cricket23.cricketSpring.beans.statsbeans.PlayerBattingStats;
import com.tekion.cricket23.cricketSpring.beans.statsbeans.PlayerBowlingStats;
import com.tekion.cricket23.cricketSpring.beans.teambeans.Player;
import com.tekion.cricket23.cricketSpring.beans.teambeans.Team;
import com.tekion.cricket23.cricketSpring.dtos.resdtos.PlayerStatsInMatch;

import java.util.LinkedHashMap;
import java.util.List;

public interface StatsService {
    void createPlayerStats(String id, String name);
    void storeAllPlayersStats(Scoreboard scoreboard, Team[] teams);
    LinkedHashMap<String, PlayerBattingStats> createTeamBattingStats(Team battingTeam);
    LinkedHashMap<String, PlayerBowlingStats> createTeamBowlingStats(Team bowlingTeam);
    PlayerStatsInMatch obtainPlayerStats(String playerId, String matchId);
    void deleteMatchStatsFromPlayerStats(String matchId, List<Player> players);
    void deleteAllGivenPlayersStats(List<String> playerIds);
}
