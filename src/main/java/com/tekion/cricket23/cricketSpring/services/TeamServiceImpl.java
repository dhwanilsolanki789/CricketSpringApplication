package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.teambeans.*;
import com.tekion.cricket23.cricketSpring.repostiories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService{
    private final TeamRepository teamRepo;
    private final PlayerService playerService;
    private static final int BATTER_COUNT = 6;
    private static final int BOWLER_COUNT = 5;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, PlayerService playerService){
        this.teamRepo = teamRepository;
        this.playerService = playerService;
    }

    public Team createTeam(String teamName, List<String> playerIds){
        Team team = new Team(teamName, playerIds);
        return teamRepo.save(team);
    }

    public Team getTeamById(String teamId){
        Optional<Team> teamObj = teamRepo.findById(teamId);
        return teamObj.orElseThrow(() -> new RuntimeException("Team Not found!"));
    }

    public Team getTeamByName(String teamName){
        return teamRepo.getTeamByTeamName(teamName);
    }

    public boolean checkIfTeamExists(String teamId) {
        return teamRepo.findById(teamId).isPresent();
    }

    public Player[] assignOpeners(Team battingTeam) {
        Player[] openers = new Player[2];
        openers[0] = playerService.getPlayerById(battingTeam.getPlayerIdAt(0));
        openers[1] = playerService.getPlayerById(battingTeam.getPlayerIdAt(1));
        return openers;
    }

    public Bowler assignBowler(Team bowlingTeam, int over) {
        over = over%BOWLER_COUNT;
        Player bowler = playerService.getPlayerById(bowlingTeam.getPlayerIdAt(BATTER_COUNT+over));
        return (Bowler) bowler;
    }

    public Player assignBatter(Team battingTeam, int wicketsFell) {
        return playerService.getPlayerById(battingTeam.getPlayerIdAt(wicketsFell+1));
    }

    public List<Player> getPlayers(Team team){
        List<Player> players = new ArrayList<>();
        for(String id : team.getPlayerIds()){
            players.add(playerService.getPlayerById(id));
        }
        return players;
    }

    public List<Player> getBowlers(Team team){
        List<Player> bowlers = new ArrayList<>();
        List<String> playerIds = team.getPlayerIds();
        for(String id : playerIds.subList(6,11)){
            bowlers.add(playerService.getPlayerById(id));
        }
        return bowlers;
    }

    public void deleteTeam(String teamId){
        Team team = getTeamById(teamId);
        List<String> playerIds = team.getPlayerIds();
        playerService.deleteAllGivenPlayers(playerIds);
        teamRepo.deleteById(teamId);
    }
}
