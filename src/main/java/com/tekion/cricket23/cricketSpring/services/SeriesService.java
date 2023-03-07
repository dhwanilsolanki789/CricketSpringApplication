package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.seriesbeans.Series;

public interface SeriesService {
    Series createSeries(String team1Id, String team2Id, int totalMatches);
    String startSeries(String team1Id, String team2Id, int totalMatches, int totalOvers);
    String playSeries(Series series, int totalOvers);
    boolean seriesEndCheck(Series series, int matchCount);
    void preMatchLog(Series series, int matchCount);
    void postMatchSeriesStats(Series series);
}
