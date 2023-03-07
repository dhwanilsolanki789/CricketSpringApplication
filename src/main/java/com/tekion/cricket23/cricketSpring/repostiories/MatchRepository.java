package com.tekion.cricket23.cricketSpring.repostiories;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends MongoRepository<Match,String> {
}
