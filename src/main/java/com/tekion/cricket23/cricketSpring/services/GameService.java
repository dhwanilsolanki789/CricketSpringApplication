package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.dtos.reqdtos.GameDto;
import com.tekion.cricket23.cricketSpring.dtos.reqdtos.TeamDto;
import org.springframework.http.ResponseEntity;

public interface GameService {
    ResponseEntity<Object> initiateGame(GameDto gameDto);
    ResponseEntity<Object> saveTeamData(TeamDto teamDto);
    ResponseEntity<Object> getMatchStats(String matchId); //todo think about this responsibility
    ResponseEntity<Object> getPlayerStats(String playerId, String matchId); //todo think about this responsibility
    ResponseEntity<Object> deleteTeam(String id);
    ResponseEntity<Object> deleteMatch(String id);
    ResponseEntity<Object> deleteSeries(String id);
}
