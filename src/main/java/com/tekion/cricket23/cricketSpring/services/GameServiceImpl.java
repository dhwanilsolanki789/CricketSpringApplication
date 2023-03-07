package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.Match;
import com.tekion.cricket23.cricketSpring.beans.seriesbeans.Series;
import com.tekion.cricket23.cricketSpring.beans.teambeans.*;
import com.tekion.cricket23.cricketSpring.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//todo have one interface layer and then have service layer
/**
 *
 * public interface GameService {
 *    DTO startGame();
 * }
 *
 * @Service
 *  public class GameServiceImpl implements GameService {
 *  *    DTO startGame() {
 *
 *  }
 *  * }
 *
 */
@Service
public class GameServiceImpl implements GameService{
    PlayerService playerService;
    TeamService teamService;
    SeriesService seriesService;
    MatchService matchService;
    StatsService statsService;

    @Autowired
    public GameServiceImpl(PlayerService playerService, TeamService teamService, SeriesService seriesService,
                           MatchService matchService, StatsService statsService){
        this.playerService = playerService;
        this.teamService =  teamService;
        this.seriesService = seriesService;
        this.matchService = matchService;
        this.statsService = statsService;
    }

    public String initiateGame(GameDto gameDto){
        String team1Name = gameDto.getTeam1Name();
        String team1Id = teamService.getTeamByName(team1Name).getTeamId();
        String team2Name = gameDto.getTeam2Name();
        String team2Id = teamService.getTeamByName(team2Name).getTeamId();
        int totalOvers = gameDto.getNoOfOvers();
        int noOfMatches = gameDto.getNoOfMatches();
        String result;
        if(noOfMatches == 1){
            Match newMatch = matchService.startMatch(team1Id,team2Id,totalOvers);
            result =  newMatch.getWinner().getTeamName() + " wins!";
        } else {
            result = seriesService.startSeries(team1Id,team2Id,noOfMatches,totalOvers);
        }
        return result;
    }

    public Team saveTeamData(TeamDto teamDto){
        String teamName = teamDto.getTeamName();
        List<PlayerDto> playerDtos = teamDto.getPlayerDtos();
        List<String> playerIds = saveAllPlayerData(playerDtos,teamName);
        return teamService.createTeam(teamName,playerIds);
    }

    public List<String> saveAllPlayerData(List<PlayerDto> playerDtos, String teamName){
        List<String> playerIds = new ArrayList<>();
        for(PlayerDto playerDto : playerDtos){
            Player newPlayer = playerService.createPlayer(playerDto.getPlayerName(),playerDto.getType(),teamName);
            playerIds.add(newPlayer.getPlayerId());
            statsService.createPlayerStats(newPlayer.getPlayerId(),newPlayer.getName());
        }
        return playerIds;
    }

    public String getMatchStats(MatchReqDto matchReqDto) {
        String matchId = matchReqDto.getMatchId();
        return matchService.obtainMatchDetails(matchId);
    }

    public String getPlayerStats(PlayerStatsReqDto reqDto) {
        String playerId = reqDto.getPlayerId();
        String matchId = reqDto.getMatchId();
        return statsService.obtainPlayerStats(playerId, matchId).toString();
    }

    public String deleteMatch(MatchReqDto matchReqDto){
        matchService.deleteMatchData(matchReqDto.getMatchId());
        return "Match and match data deleted from players!";
    }
}
