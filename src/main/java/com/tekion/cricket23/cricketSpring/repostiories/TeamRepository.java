package com.tekion.cricket23.cricketSpring.repostiories;

import com.tekion.cricket23.cricketSpring.beans.teambeans.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team,String> {
    Team getTeamByTeamName(String teamName);
}
