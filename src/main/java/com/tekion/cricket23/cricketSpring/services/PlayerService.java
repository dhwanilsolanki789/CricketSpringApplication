package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.teambeans.Player;

import java.util.List;

public interface PlayerService {
    Player createPlayer(String playerName, String type, String teamName);
    Player getPlayerById(String id);
    boolean checkIfPlayerExists(String playerId);
    int playBall(Player batter);
    void deleteAllGivenPlayers(List<String> playerIds);
}
