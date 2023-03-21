package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.Inning;
import com.tekion.cricket23.cricketSpring.beans.matchbeans.Scoreboard;
import com.tekion.cricket23.cricketSpring.beans.statsbeans.*;
import com.tekion.cricket23.cricketSpring.beans.teambeans.Player;
import com.tekion.cricket23.cricketSpring.beans.teambeans.Team;
import com.tekion.cricket23.cricketSpring.dtos.resdtos.PlayerStatsInMatch;
import com.tekion.cricket23.cricketSpring.repostiories.PlayerStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class StatsServiceImpl implements  StatsService{
    private final PlayerStatsRepository playerStatsRepo;
    private final TeamService teamService;

    @Autowired
    public StatsServiceImpl(PlayerStatsRepository playerStatsRepository, TeamService teamService){
        this.playerStatsRepo = playerStatsRepository;
        this.teamService = teamService;
    }

    public void createPlayerStats(String playerId, String playerName){
        PlayerStats playerStats = new PlayerStats(playerId,playerName);
        playerStatsRepo.save(playerStats);
    }

    private void storePlayerStatsInMatch(String matchId,String playerId,
                                 PlayerBattingStats battingStats, PlayerBowlingStats bowlingStats){
        PlayerStats playerStats = playerStatsRepo.findPlayerStatsByPlayerId(playerId);
        StatsInMatch statsInMatch = new StatsInMatch(battingStats,bowlingStats);
        playerStats.addStatsInMatch(matchId,statsInMatch);
        playerStatsRepo.save(playerStats);
    }


    private void storeAllPlayerStatsOfATeam(String matchId, Team team,
                                           LinkedHashMap<String, PlayerBattingStats> teamBattingStats,
                                           LinkedHashMap<String, PlayerBowlingStats> teamBowlingStats) {
        List<String> playerIds = team.getPlayerIds();
        List<String> batterIds = playerIds.subList(0,6);
        List<String> bowlerIds = playerIds.subList(6,11);
        for(String batterId : batterIds){
            storePlayerStatsInMatch(matchId,batterId,teamBattingStats.get(batterId),null);
        }
        for(String bowlerId : bowlerIds){
            storePlayerStatsInMatch(matchId,bowlerId,teamBattingStats.get(bowlerId),teamBowlingStats.get(bowlerId));
        }
    }

    public void storeAllPlayersStats(Scoreboard scoreboard, Team[] teamOrder){
        Inning[] innings = {scoreboard.getInning(1),scoreboard.getInning(2)};
        String matchId = scoreboard.getMatchId();
        storeAllPlayerStatsOfATeam(matchId,teamOrder[0],
                innings[0].getBattingStats(),innings[1].getBowlingStats());
        storeAllPlayerStatsOfATeam(matchId,teamOrder[1],
                innings[1].getBattingStats(),innings[0].getBowlingStats());
    }

    public LinkedHashMap<String, PlayerBattingStats> createTeamBattingStats(Team battingTeam) {
        LinkedHashMap<String, PlayerBattingStats> battingStats = new LinkedHashMap<>();
        List<Player> players = teamService.getPlayers(battingTeam);
        for(Player player : players){
            battingStats.put(player.getPlayerId(),new PlayerBattingStats(player.getName()));
        }
        return battingStats;
    }

    public LinkedHashMap<String, PlayerBowlingStats> createTeamBowlingStats(Team bowlingTeam) {
        LinkedHashMap<String, PlayerBowlingStats> bowlingStats = new LinkedHashMap<>();
        List<Player> bowlers = teamService.getBowlers(bowlingTeam);
        for(Player bowler : bowlers){
            bowlingStats.put(bowler.getPlayerId(),new PlayerBowlingStats(bowler.getName()));
        }
        return bowlingStats;
    }

    public PlayerStatsInMatch obtainPlayerStats(String playerId, String matchId) {
        PlayerStats playerStats = playerStatsRepo.findPlayerStatsByPlayerId(playerId);
        StatsInMatch statsInGivenMatch = playerStats.findStatsInGivenMatch(matchId);
        PlayerStatsInMatch playerStatsInMatch = new PlayerStatsInMatch(playerStats.getPlayerName());
        playerStatsInMatch.setBattingStats(statsInGivenMatch.getBattingStats().toString());
        if(statsInGivenMatch.getBowlingStats() != null){
            playerStatsInMatch.setBowlingStats(statsInGivenMatch.getBowlingStats().toString());
        }
        return playerStatsInMatch;
    }

    public void deleteMatchStatsFromPlayerStats(String matchId,List<Player> players){
        for(Player player : players){
            PlayerStats playerStats = playerStatsRepo.findPlayerStatsByPlayerId(player.getPlayerId());
            playerStats.removeStatsOfAMatch(matchId);
            playerStatsRepo.save(playerStats);
        }
    }

    public void deleteAllGivenPlayersStats(List<String> playerIds){
        for(String playerId : playerIds){
            playerStatsRepo.deleteByPlayerId(playerId);
        }
    }
}
