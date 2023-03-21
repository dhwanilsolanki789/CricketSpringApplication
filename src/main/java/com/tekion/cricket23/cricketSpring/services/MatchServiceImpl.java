package com.tekion.cricket23.cricketSpring.services;

import com.tekion.cricket23.cricketSpring.beans.matchbeans.*;
import com.tekion.cricket23.cricketSpring.beans.teambeans.*;
import com.tekion.cricket23.cricketSpring.dtos.resdtos.InningRes;
import com.tekion.cricket23.cricketSpring.dtos.resdtos.MatchRes;
import com.tekion.cricket23.cricketSpring.utils.CricketUtils;
import com.tekion.cricket23.cricketSpring.repostiories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService{
    private final MatchRepository matchRepo;
    private final ScoreboardService scoreboardService;
    private final TeamService teamService;
    private final PlayerService playerService;
    private final StatsService statsService;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, ScoreboardService scoreboardService,
                            TeamService teamService, PlayerService playerService, StatsService statsService){
        this.matchRepo = matchRepository;
        this.scoreboardService = scoreboardService;
        this.teamService = teamService;
        this.playerService = playerService;
        this.statsService = statsService;
    }


    public Match createMatch(String team1Id, String team2Id, int totalOvers) {
        final Match newMatch = new Match(team1Id, team2Id, totalOvers);
        return matchRepo.save(newMatch);
    }


    public Match startMatch(String team1Id, String team2Id, int totalOvers) {
        final Match match = createMatch(team1Id, team2Id, totalOvers);
        playMatch(match);
        return match;
    }

    private Team[] initiateMatch(Match currMatch) {
        final Team[] teamOrder = toss(currMatch);
        final Scoreboard scoreboard = scoreboardService.createScoreboard(currMatch.getMatchId());
        currMatch.setScoreboard(scoreboard);
        return teamOrder;
    }

    private Team[] toss(Match currMatch) {
        int toss = (int) (Math.random() * 2);
        final Team team1 = teamService.getTeamById(currMatch.getTeam1Id());
        final Team team2 = teamService.getTeamById(currMatch.getTeam2Id());
        final Team[] teamOrder = new Team[2];

        if (toss == 1) {
            teamOrder[0] = team1;
            teamOrder[1] = team2;
        } else {
            teamOrder[0] = team2;
            teamOrder[1] = team1;
        }

        System.out.println("Coin flipped for toss!");
        System.out.println(teamOrder[0].getTeamName() + " bats first!");
        return teamOrder;
    }

    public void playMatch(Match match) {
        final Team[] teamOrder = initiateMatch(match);
        playInning(match, 1, teamOrder[0], teamOrder[1]);
        changeInning(match);
        playInning(match, 2, teamOrder[1], teamOrder[0]);
        postMatchUpdation(match, teamOrder);
        printResults(match);
        scoreboardService.printScoreboard(match.getScoreboard());
    }

    private void playInning(Match currMatch, int inningNo, Team battingTeam, Team bowlingTeam) {
        initiateInning(currMatch, inningNo, battingTeam, bowlingTeam);
        implementInning(inningNo, currMatch, battingTeam, bowlingTeam);
        scoreboardService.printPostInningStats(currMatch.getCurrInning());
        CricketUtils.printDottedLine();
    }

    private void initiateInning(Match currMatch, int inningNo, Team battingTeam, Team bowlingTeam) {
        final Inning inning = scoreboardService
                .createInning(currMatch.getScoreboard(), inningNo, battingTeam, bowlingTeam);
        currMatch.setCurrInning(inning);
        final Player[] openers = teamService.assignOpeners(battingTeam);
        currMatch.setCurrBatterOnStrike(openers[0]);
        currMatch.setCurrBatterOffStrike(openers[1]);
    }

    private void implementInning(int inningNo, Match currMatch, Team battingTeam, Team bowlingTeam) {
        final Inning currInning = currMatch.getCurrInning();
        for (int currOver = 0; currOver < currMatch.getTotalOvers(); currOver++) {
            Bowler currBowler = teamService.assignBowler(bowlingTeam, currOver);
            currMatch.setCurrBowler(currBowler);
            implementOver(currMatch, battingTeam, currOver, inningNo);
            if (currInning.checkIfInningEnded()) {
                break;
            } else {
                changeOver(currMatch, currOver + 1);
            }
        }
    }

    private void changeInning(Match match) {
        scoreboardService.postInningUpdation(match.getScoreboard());
        updateMatchInstance(match);
    }

    private void implementOver(Match currMatch, Team battingTeam, int currOver, int inningNo) {
        Inning currInning = currMatch.getCurrInning();
        for (int currBall = 1; currBall <= 6; currBall++) {
            System.out.print("(" + currOver + "." + currBall + ") ");
            int ballOutcome = bowlBall(currMatch);
            updateMatchAfterBall(currMatch, ballOutcome, battingTeam);
            if (checkInningEnded(currMatch, inningNo, currOver, currBall)) {
                scoreboardService.updateInning(currInning, currOver, currBall);
                break;
            }
        }
    }

    private void changeOver(Match currMatch, int overNo) {
        Inning currInning = currMatch.getCurrInning();
        scoreboardService.updateInning(currInning, overNo, 0);
        changeStrike(currMatch);
        Player[] currBatters = {currMatch.getCurrBatterOnStrike(),
                currMatch.getCurrBatterOffStrike()};
        Bowler currBowler = currMatch.getCurrBowler();
        scoreboardService.printPostOverStats(currInning, currBatters, currBowler);
    }

    private int bowlBall(Match currMatch) {
        final Player currBatter = currMatch.getCurrBatterOnStrike();
        return playerService.playBall(currBatter);
    }

    private void updateMatchAfterBall(Match currMatch, int ballOutcome, Team battingTeam) {
        Inning currInning = currMatch.getCurrInning();
        Player[] currBatters = {currMatch.getCurrBatterOnStrike(), currMatch.getCurrBatterOffStrike()};
        Bowler currBowler = currMatch.getCurrBowler();
        if (ballOutcome != 7) {
            scoreboardService.updateRuns(currInning, ballOutcome, currBatters[0], currBowler);
            if (ballOutcome % 2 == 1) {
                changeStrike(currMatch);
            }
        } else {
            scoreboardService.updateWickets(currMatch.getCurrInning(), currBatters[0], currBowler);
            if (!checkIfAllWicketsDown(currInning)) {
                Player newBatter = teamService.assignBatter(battingTeam, currInning.getWicketsFell());
                currMatch.setCurrBatterOnStrike(newBatter);
            }
        }
    }

    private boolean checkInningEnded(Match currMatch, int inningNo, int oversBowled, int ballsBowled) {
        Inning currInning = currMatch.getCurrInning();
        if (currInning.getWicketsFell() == Match.getTotalWickets()) {
            currMatch.setBattersNull();
            currInning.setInningEnded();
            System.out.println("Innings ended!");
            return true;
        }
        if (oversBowled == 19 && ballsBowled == 6) {
            currInning.setInningEnded();
            System.out.println("Innings ended!");
            return true;
        }
        if (inningNo == 2) {
            if (currInning.getRunsScored() >= currMatch.getTarget()) {
                currInning.setInningEnded();
                System.out.println("Match ended!");
                return true;
            }
        }
        return false;
    }

    private boolean checkIfAllWicketsDown(Inning currInning) {
        return currInning.getWicketsFell() >= Match.getTotalWickets();
    }

    private void changeStrike(Match currMatch) {
        Player currBatter = currMatch.getCurrBatterOnStrike();
        currMatch.setCurrBatterOnStrike(currMatch.getCurrBatterOffStrike());
        currMatch.setCurrBatterOffStrike(currBatter);
    }

    private void updateMatchInstance(Match currMatch) {
        matchRepo.save(currMatch);
    }

    private void postMatchUpdation(Match currMatch, Team[] teams) {
        calculateResults(currMatch);
        scoreboardService.storeScoreboardData(currMatch.getScoreboard(),teams);
        currMatch.setCurrInning(null);
        updateMatchInstance(currMatch);
    }

    private void calculateResults(Match currMatch) {
        Scoreboard scoreboard = currMatch.getScoreboard();
        Inning currInning = currMatch.getCurrInning();
        int target = scoreboard.getMatchTarget();
        String winner = null;
        String matchResult = "";
        if (currInning.getRunsScored() >= target) {
            winner = currInning.getBattingTeam();
            matchResult = currInning.getBattingTeam() + " won by "
                    + (Match.getTotalWickets() - currInning.getWicketsFell()) + " wickets "
                    + "from " + currInning.getBowlingTeam() + ".";
        }
        if (currInning.getRunsScored() < target - 1) {
            winner = currInning.getBowlingTeam();
            matchResult = currInning.getBowlingTeam() + " won by "
                    + (target - currInning.getRunsScored() - 1) + " runs "
                    + "from " + currInning.getBattingTeam() + ".";
        }
        // Check if match tied or print game results
        if (currInning.getRunsScored() == target - 1) {
            matchResult = "Match tied!";
        }
        currMatch.setMatchResult(matchResult);
        currMatch.setWinner(winner);
    }

    private void printResults(Match currMatch) {
        System.out.println(currMatch.getMatchResult());
        CricketUtils.printDottedLine();
    }

    public boolean checkIfMatchExists(String matchId){
        return matchRepo.findById(matchId).isPresent();
    }

    public MatchRes obtainMatchDetails(String matchId) {
        Match foundMatch = matchRepo.findById(matchId).orElse(null);
        MatchRes matchRes = new MatchRes(foundMatch.getTotalOvers());
        List<InningRes> inningsRes = scoreboardService.getMatchDetails(foundMatch.getScoreboard());
        matchRes.setInning1(inningsRes.get(0));
        matchRes.setInning2(inningsRes.get(1));
        matchRes.setMatchResult(foundMatch.getMatchResult());
        return matchRes;
    }

    public void deleteMatchData(String matchId){
        Match match = matchRepo.findById(matchId).orElse(null);
        deletePlayerStatsOfTeam(matchId,match.getTeam1Id());
        deletePlayerStatsOfTeam(matchId,match.getTeam2Id());
        matchRepo.deleteById(matchId);
    }

    private void deletePlayerStatsOfTeam(String matchId, String teamId){
        Team team = teamService.getTeamById(teamId);
        List<Player> teamPlayers = teamService.getPlayers(team);
        statsService.deleteMatchStatsFromPlayerStats(matchId,teamPlayers);
    }
}
