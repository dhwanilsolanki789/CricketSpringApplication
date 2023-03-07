package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.teambeans.*;
import com.tekion.cricket23.cricketSpring.repostiories.PlayerRepository;
import com.tekion.cricket23.cricketSpring.utils.CricketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    PlayerRepository playerRepo;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepo = playerRepository;
    }

    public Player createPlayer(String playerName, String type, String teamName){
        Player newPlayer;
        if(type.equals("Batter")){
            newPlayer = new Batter(playerName,type,teamName);
            newPlayer = playerRepo.save(newPlayer);
        } else {
            newPlayer = new Bowler(playerName,type,teamName);
            newPlayer = playerRepo.save(newPlayer);
        }
        return newPlayer;
    }

    public Player getPlayerById(String id) {
        Optional<Player> playerObj = playerRepo.findById(id);
        return playerObj.orElseThrow(() -> new EmptyResultDataAccessException("Player not found!", 50));
    }

    public int playBall(Player batter) {
        double probability = Math.random();
        int ballOutcome = CricketUtils.getBallOutcome(probability, batter.getHIT_PROBABILITY());
        if (ballOutcome == 0) {
            System.out.println("Good delivery! Dot ball.");
        } else if (ballOutcome == 1) {
            System.out.println(batter.getName() + " takes a single, 1 run.");
        } else if (ballOutcome == 2) {
            System.out.println(batter.getName() + " takes a quick double, 2 runs.");
        } else if (ballOutcome == 3) {
            System.out.println(batter.getName() + " takes 3 runs.");
        } else if (ballOutcome == 4) {
            System.out.println(batter.getName() + " hits a Boundary! 4 runs.");
        } else if (ballOutcome == 6) {
            System.out.println(batter.getName() + " hits a Maximum! 6 runs.");
        } else if (ballOutcome == 7) {
            System.out.println("Wicket! " + batter.getName() + " had to go back.");
        }
        return ballOutcome;
    }
}
