package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.Scoreboard;
import com.tekion.cricket23.cricketSpring.beans.statsbeans.PlayerBattingStats;
import com.tekion.cricket23.cricketSpring.beans.statsbeans.PlayerBowlingStats;
import com.tekion.cricket23.cricketSpring.beans.statsbeans.PlayerStats;
import com.tekion.cricket23.cricketSpring.beans.teambeans.Player;
import com.tekion.cricket23.cricketSpring.beans.teambeans.Team;

import java.util.LinkedHashMap;
import java.util.List;

public interface StatsService {
    void createPlayerStats(String id, String name);
    void storePlayerStatsInMatch(String matchId, String playerId,
                          PlayerBattingStats battingStats, PlayerBowlingStats bowlingStats);
    void storeAllPlayerStatsOfATeam(String matchId, Team team,
                                    LinkedHashMap<String,PlayerBattingStats> teamBattingStats,
                                    LinkedHashMap<String,PlayerBowlingStats> teamBowlingStats);
    void storeAllPlayersStats(Scoreboard scoreboard, Team[] teams);
    LinkedHashMap<String, PlayerBattingStats> createTeamBattingStats(Team battingTeam);
    LinkedHashMap<String, PlayerBowlingStats> createTeamBowlingStats(Team bowlingTeam);
    String obtainPlayerStats(String playerId, String matchId);
    void deleteMatchStatsFromPlayerStats(String matchId, List<Player> players);
}
