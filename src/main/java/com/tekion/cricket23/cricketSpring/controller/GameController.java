package com.tekion.cricket23.cricketSpring.controller;

import com.tekion.cricket23.cricketSpring.dtos.reqdtos.GameDto;
import com.tekion.cricket23.cricketSpring.dtos.reqdtos.TeamDto;
import com.tekion.cricket23.cricketSpring.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/team")
    public ResponseEntity<Object> saveTeam(@RequestBody TeamDto teamDto){
        return gameService.saveTeamData(teamDto);
    }

    @PostMapping("/startgame")
    public ResponseEntity<Object> startGame(@RequestBody GameDto gameDto) {
        return gameService.initiateGame(gameDto);
    }

    @GetMapping("/match/{matchId}")
    public ResponseEntity<Object> getMatchDetails(@PathVariable String matchId) {
        return gameService.getMatchStats(matchId);
    }

    @GetMapping("/player-stats/{playerId}/match/{matchId}")
    public ResponseEntity<Object> getPlayerStatsInAMatch(@PathVariable String playerId, @PathVariable String matchId) {
        return gameService.getPlayerStats(playerId,matchId);
    }

    @DeleteMapping("/series/{seriesId}")
    public ResponseEntity<Object> deleteSeries(@PathVariable String seriesId){
        return gameService.deleteSeries(seriesId);
    }

    @DeleteMapping("/match/{matchId}")
    public ResponseEntity<Object> deleteMatch(@PathVariable String matchId){
        return  gameService.deleteMatch(matchId);
    }

    @DeleteMapping("/team/{teamId}")
    public ResponseEntity<Object> deleteTeam(@PathVariable String teamId){ return gameService.deleteTeam(teamId); }
}
