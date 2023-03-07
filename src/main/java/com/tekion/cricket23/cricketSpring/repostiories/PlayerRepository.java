package com.tekion.cricket23.cricketSpring.repostiories;

import com.tekion.cricket23.cricketSpring.beans.teambeans.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player,String> {

}
