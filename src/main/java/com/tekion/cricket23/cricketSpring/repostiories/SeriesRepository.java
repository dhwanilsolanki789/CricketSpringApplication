package com.tekion.cricket23.cricketSpring.repostiories;

import com.tekion.cricket23.cricketSpring.beans.seriesbeans.Series;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends MongoRepository<Series,String> {
}
