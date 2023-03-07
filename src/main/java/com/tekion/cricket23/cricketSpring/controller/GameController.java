package com.tekion.cricket23.cricketSpring.controller;

import com.tekion.cricket23.cricketSpring.beans.teambeans.Team;
import com.tekion.cricket23.cricketSpring.dtos.*;
import com.tekion.cricket23.cricketSpring.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/teams")
    public Team saveTeam(@RequestBody TeamDto teamDto){
        return gameService.saveTeamData(teamDto);
    }

    //todo take dto
    @PostMapping("/startGame")
    public String startGame(@RequestBody GameDto gameDto) {
        return gameService.initiateGame(gameDto);
    }

    @PostMapping("/fetchMatchDetails")
    public Object getMatchDetails(@RequestBody MatchReqDto matchReqDto) {
        return gameService.getMatchStats(matchReqDto);
    }

    @PostMapping("/playerStatsInAMatch")
    public String getPlayerStatsInAMatch(@RequestBody PlayerStatsReqDto reqDto) {
        return gameService.getPlayerStats(reqDto);
    }

    @PostMapping("/deleteMatchWithData")
    public String deleteMatch(@RequestBody MatchReqDto matchReqDto){ return  gameService.deleteMatch(matchReqDto); }
}
