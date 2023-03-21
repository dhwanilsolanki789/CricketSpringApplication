package com.tekion.cricket23.cricketSpring.repostiories;

import com.tekion.cricket23.cricketSpring.beans.statsbeans.PlayerStats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStatsRepository extends MongoRepository<PlayerStats,String> {
    PlayerStats findPlayerStatsByPlayerId(String playerId);
    void deleteByPlayerId(String playerId);
}
