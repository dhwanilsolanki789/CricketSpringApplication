package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.Match;
import com.tekion.cricket23.cricketSpring.beans.seriesbeans.MatchDao;
import com.tekion.cricket23.cricketSpring.beans.seriesbeans.Series;
import com.tekion.cricket23.cricketSpring.beans.teambeans.Team;
import com.tekion.cricket23.cricketSpring.repostiories.SeriesRepository;
import com.tekion.cricket23.cricketSpring.utils.CricketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService{
    private final SeriesRepository seriesRepo;
    private final MatchService matchService;
    private final TeamService teamService;

    @Autowired
    public SeriesServiceImpl(SeriesRepository seriesRepository, MatchServiceImpl matchService,
                             TeamService teamService){
        this.seriesRepo = seriesRepository;
        this.matchService = matchService;
        this.teamService = teamService;
    }

    public Series createSeries(String team1Id, String team2Id, int noOfMatches) {
        Team team1 = teamService.getTeamById(team1Id);
        Team team2 = teamService.getTeamById(team2Id);
        Series newSeries = new Series(team1Id,team1.getTeamName(),team2Id,team2.getTeamName(),noOfMatches);
        return seriesRepo.save(newSeries);
    }
    public String startSeries(String team1Id, String team2Id, int noOfMatches, int totalOvers){
        Series newSeries = createSeries(team1Id,team2Id,noOfMatches);
        return playSeries(newSeries,totalOvers);
    }


    public String playSeries(Series series, int totalOvers) {
        String team1Id = series.getTeam1Id();
        String team2Id = series.getTeam2Id();
        for(int matchCount = 1; matchCount <= series.getTotalMatches(); matchCount++){
            preMatchLog(series,matchCount);
            Match newMatch = matchService.startMatch(team1Id,team2Id,totalOvers);
            postMatchSeriesUpdation(series,newMatch);
            if(seriesEndCheck(series,matchCount)){
                postSeriesStats(series);
                break;
            }
        }
        return series.getSeriesResult();
    }

    private void postSeriesStats(Series series) {
        System.out.println("Series Ended!");
        System.out.println(series.getSeriesResult());
    }

    public void postMatchSeriesUpdation(Series currSeries,Match newMatch) {
        currSeries.incrementTeamWins(newMatch.getWinner());
        MatchDao matchDao = new MatchDao(newMatch.getMatchId(),newMatch.getMatchResult());
        currSeries.addMatch(matchDao);
        postMatchSeriesStats(currSeries);
    }

    private boolean seriesEndCheck(Series currSeries, int matchCount) {
        int totalMatches = currSeries.getTotalMatches();
        int team1Wins = currSeries.getTeam1Wins();
        int team2Wins = currSeries.getTeam2Wins();
        if(team1Wins > totalMatches/2 || team2Wins > totalMatches/2){
            calculateSeriesResult(currSeries,team1Wins,team2Wins);
            return true;
        }
        if(totalMatches == matchCount){
            calculateSeriesResult(currSeries,team1Wins,team2Wins);
            return true;
        }
        seriesRepo.save(currSeries);
        return false;
    }

    private void calculateSeriesResult(Series currSeries, int team1Wins, int team2Wins) {
        int totalMatches = currSeries.getTotalMatches();
        String result;
        if(team1Wins > totalMatches/2){
            result = currSeries.getTeam1Name() + " beat " + currSeries.getTeam2Name() + " by "
                    + team1Wins + "-" + team2Wins;
        } else if(team2Wins > totalMatches/2){
            result = currSeries.getTeam2Name() + " beat " + currSeries.getTeam1Name() + " by "
                    + team2Wins + "-" + team1Wins;
        } else {
            result = "Series tied between " + currSeries.getTeam1Name() + " and "
                    + currSeries.getTeam2Name() + " by " + team1Wins + "-" + team2Wins;
        }
        currSeries.setSeriesResult(result);
        seriesRepo.save(currSeries);
    }

    private void preMatchLog(Series series, int matchCount) {
        System.out.println("Match " + matchCount + " | " +
                series.getTeam1Name() + " vs " + series.getTeam2Name());
    }

    private void postMatchSeriesStats(Series series) {
        System.out.println(series.getTeam1Name() + " " + series.getTeam1Wins() + " - "
                + series.getTeam2Name() + " " + series.getTeam2Wins());
        CricketUtils.printBlankLine();
    }

    public boolean checkIfSeriesExists(String seriesId) {
        return seriesRepo.findById(seriesId).isPresent();
    }

    public void deleteSeriesData(String seriesId){
        Series series = seriesRepo.findById(seriesId).orElse(null);
        List<MatchDao> matches = series.getMatches();
        for(MatchDao match : matches){
            matchService.deleteMatchData(match.getMatchId());
        }
        seriesRepo.deleteById(seriesId);
    }
}
