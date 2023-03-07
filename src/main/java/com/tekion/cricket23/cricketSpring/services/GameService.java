package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.teambeans.Team;
import com.tekion.cricket23.cricketSpring.dtos.*;

import java.util.List;

public interface GameService {
    String initiateGame(GameDto gameDto);
    Team saveTeamData(TeamDto teamDto);
    List<String> saveAllPlayerData(List<PlayerDto> playerDtos, String teamName);
    String getMatchStats(MatchReqDto matchReqDto); //todo think about this responsibility
    String getPlayerStats(PlayerStatsReqDto reqDto); //todo think about this responsibility
    String deleteMatch(MatchReqDto matchReqDto);
}
