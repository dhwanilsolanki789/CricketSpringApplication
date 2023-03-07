package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.teambeans.Bowler;
import com.tekion.cricket23.cricketSpring.beans.teambeans.Player;
import com.tekion.cricket23.cricketSpring.beans.teambeans.Team;

import java.util.List;

public interface TeamService {
    Team createTeam(String teamName, List<String> playerIds);
    Team getTeamById(String id);
    Team getTeamByName(String name);
    Player[] assignOpeners(Team battingTeam);
    Bowler assignBowler(Team bowlingTeam, int over);
    Player assignBatter(Team battingTeam, int wicketsFell);
    List<Player> getPlayers(Team team);
    List<Player> getBowlers(Team team);
}
