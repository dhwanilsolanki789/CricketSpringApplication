package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.Match;
import com.tekion.cricket23.cricketSpring.beans.teambeans.*;
import com.tekion.cricket23.cricketSpring.dtos.reqdtos.GameDto;
import com.tekion.cricket23.cricketSpring.dtos.reqdtos.PlayerDto;
import com.tekion.cricket23.cricketSpring.dtos.reqdtos.TeamDto;
import com.tekion.cricket23.cricketSpring.dtos.resdtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GameServiceImpl implements GameService{
    private final PlayerService playerService;
    private final TeamService teamService;
    private final SeriesService seriesService;
    private final MatchService matchService;
    private final StatsService statsService;

    @Autowired
    public GameServiceImpl(PlayerService playerService, TeamService teamService, SeriesService seriesService,
                           MatchService matchService, StatsService statsService){
        this.playerService = playerService;
        this.teamService =  teamService;
        this.seriesService = seriesService;
        this.matchService = matchService;
        this.statsService = statsService;
    }

    public ResponseEntity<Object> initiateGame(GameDto gameDto){
        Object validationRes = validateTeams(gameDto.getTeam1Name(),gameDto.getTeam2Name());
        if(validationRes instanceof ErrorRes)
            return ResponseEntity.badRequest().body(validationRes);
        @SuppressWarnings("unchecked")
        List<String> teamIds = (List<String>) validationRes;
        int totalOvers = gameDto.getNoOfOvers();
        int noOfMatches = gameDto.getNoOfMatches();
        String result;
        if(noOfMatches == 1){
            Match newMatch = matchService.startMatch(teamIds.get(0),teamIds.get(1),totalOvers);
            result =  newMatch.getWinner() + " wins!";
        } else {
            result = seriesService.startSeries(teamIds.get(0),teamIds.get(1),noOfMatches,totalOvers);
        }
        return ResponseEntity.ok().body(new GameResultRes(result));
    }

    private Object validateTeams(String team1Name, String team2Name){
        Object res1 = validateAndGetTeamId(team1Name);
        if(res1 instanceof ErrorRes)
            return ResponseEntity.badRequest().body(res1);
        Object res2 = validateAndGetTeamId(team2Name);
        if(res2 instanceof ErrorRes)
            return res2;
        List<String> teamIds = new ArrayList<>();
        teamIds.add((String) res1);
        teamIds.add((String) res2);
        return teamIds;
    }

    private Object validateAndGetTeamId(String teamName) {
        Team team = teamService.getTeamByName(teamName);
        if(team != null){
            return team.getTeamId();
        } else {
            return new ErrorRes(teamName + " team doesn't exist, save it first!");
        }
    }

    public ResponseEntity<Object> saveTeamData(TeamDto teamDto){
        Object validationRes = validateTeamDto(teamDto);
        if(validationRes != null)
            return ResponseEntity.badRequest().body(validationRes);
        String teamName = teamDto.getTeamName();
        List<PlayerDto> playerDtos = teamDto.getPlayerDtos();
        List<String> playerIds = saveAllPlayerData(playerDtos,teamName);
        teamService.createTeam(teamName,playerIds);
        return ResponseEntity.accepted().body(new SuccessRes("Team saved."));
    }

    private List<String> saveAllPlayerData(List<PlayerDto> playerDtos, String teamName){
        List<String> playerIds = new ArrayList<>();
        for(PlayerDto playerDto : playerDtos){
            Player newPlayer = playerService.createPlayer(playerDto.getPlayerName(),playerDto.getType(),teamName);
            playerIds.add(newPlayer.getPlayerId());
            statsService.createPlayerStats(newPlayer.getPlayerId(),newPlayer.getName());
        }
        return playerIds;
    }

    private Object validateTeamDto(TeamDto teamDto) {
        String teamName = teamDto.getTeamName();
        if(teamName == null)
            return new ErrorRes("Team name is required!");
        if(teamService.getTeamByName(teamName) != null){
            return ResponseEntity.badRequest().body(new ErrorRes("Team already exists!"));
        }
        if(teamDto.getPlayerDtos() == null)
            return new ErrorRes("Players data cannot be null");
        return validatePlayers(teamDto.getPlayerDtos());
    }

    private Object validatePlayers(List<PlayerDto> playerDtos) {
        if(playerDtos.size() != 11)
            return new ErrorRes("There must be exactly 11 players!");
        for(PlayerDto playerDto : playerDtos){
            Object playerRes = validatePlayerDto(playerDto);
            if(playerRes != null)
                return playerRes;
        }
        return null;
    }

    private Object validatePlayerDto(PlayerDto playerDto) {
        if(playerDto.getPlayerName() == null)
            return new ErrorRes("Player name is required!");
        if(playerDto.getType() == null)
            return new ErrorRes("Player type is required!");
        return null;
    }

    public ResponseEntity<Object> getMatchStats(String matchId) {
        if(!matchService.checkIfMatchExists(matchId))
            return ResponseEntity.badRequest().body(new ErrorRes("Match doesnt exist with this id!"));
        MatchRes matchData = matchService.obtainMatchDetails(matchId);
        return ResponseEntity.accepted().body(matchData);
    }

    public ResponseEntity<Object> getPlayerStats(String playerId, String matchId) {
        if(!playerService.checkIfPlayerExists(playerId))
            return ResponseEntity.badRequest().body(new ErrorRes("Player doesnt exist with this id!"));
        if(!matchService.checkIfMatchExists(matchId))
            return ResponseEntity.badRequest().body(new ErrorRes("Match doesnt exist with this id!"));
        PlayerStatsInMatch playerStatsInMatch = statsService.obtainPlayerStats(playerId, matchId);
        return ResponseEntity.accepted().body(playerStatsInMatch);
    }

    public ResponseEntity<Object> deleteTeam(String teamId){
        if(!teamService.checkIfTeamExists(teamId))
            return ResponseEntity.badRequest().body(new ErrorRes("Team doesnt exist with this id!"));
        statsService.deleteAllGivenPlayersStats(teamService.getTeamById(teamId).getPlayerIds());
        teamService.deleteTeam(teamId);
        return ResponseEntity.accepted().body(new SuccessRes("Team and players data deleted!"));
    }

    public ResponseEntity<Object> deleteMatch(String matchId){
        if(!matchService.checkIfMatchExists(matchId))
            return ResponseEntity.badRequest().body(new ErrorRes("Match doesnt exist with this id!"));
        matchService.deleteMatchData(matchId);
        return ResponseEntity.accepted()
                .body(new SuccessRes("Match and match data deleted from players!"));
    }

    public ResponseEntity<Object> deleteSeries(String seriesId){
        if(!seriesService.checkIfSeriesExists(seriesId))
            return ResponseEntity.badRequest().body(new ErrorRes("Series doesnt exist with this id!"));
        seriesService.deleteSeriesData(seriesId);
        return ResponseEntity.accepted().body(new SuccessRes("Series and all matches data deleted!"));
    }
}
