package com.tekion.cricket23.cricketjava.beans.matchBeans;

import com.tekion.cricket23.cricketjava.beans.statsBeans.MatchStats;
import com.tekion.cricket23.cricketjava.beans.teamBeans.Bowler;
import com.tekion.cricket23.cricketjava.beans.teamBeans.Player;
import com.tekion.cricket23.cricketjava.beans.teamBeans.Team;
import com.tekion.cricket23.cricketjava.launcher.Utility;

public class Match {
    private final Team team1, team2;
    private final int matchOvers, maxWickets;
    private Scorecard scorecard;
    private Inning currInning;
    private Bowler currBowler;
    private Player currBatterOnStrike, currBatterOffStrike;
    private int matchTarget;

    public Match(int overs, Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.maxWickets = team1.getPlayerCount()-1;
        matchOvers = overs;
    }

    public int playInning(int inningNo, Team battingTeam, Team bowlingTeam) {
        initiateInning(battingTeam);
        implementInning(inningNo,battingTeam,bowlingTeam);
        //Print inning stats
        currInning.printInningScore();
        Utility.printDottedLine();
        return currInning.getRunsScored();
    }

    public void initiateInning(Team battingTeam){
        //Inning setup
        Player[] openers = battingTeam.assignOpeners();
        this.currBatterOnStrike = openers[0];
        this.currBatterOffStrike = openers[1];
    }

    public void implementInning(int inningNo,Team battingTeam, Team bowlingTeam){
        this.currInning = scorecard.getInning(inningNo);
        int currOver, currBall = 0;
        for (currOver = 0; currOver < this.matchOvers; currOver++) {
            currBall = 1;
            this.currBowler = bowlingTeam.assignBowler(currOver);
            for (; currBall <= 6; currBall++) {
                // Bowl ball & inning end checked
                System.out.print("(" + currOver + "." + currBall + ") ");
                int ballOutcome = currBatterOnStrike.playBall();
                updateMatch(ballOutcome, battingTeam, bowlingTeam);
                if (inningEndCheck(inningNo)) {
                    currBowler.addBallsBowled(currBall);
                    currInning.setExcessBallsBowled(currBall);
                    break;
                }
            }
            if(currInning.checkInningEnded()) {
                break;
            } else {
                changeOver(currOver+1);
            }
        }
        //Store inning stats
        currInning.setOversBowled(currOver);
    }

    public void updateMatch(int ballOutcome, Team battingTeam, Team bowlingTeam) {
        // 7 -> wicket
        if (ballOutcome != 7) {
            //update score and log ball result
            currInning.updateRuns(ballOutcome,currBatterOnStrike,currBowler);
            currBowler.addRunsConceded(ballOutcome);
            //Switch Strike when needed
            if (ballOutcome % 2 == 1) {
                changeStrike();
            }
        } else {
            //update wickets and log ball result
            currInning.updateWickets(currBatterOnStrike,currBowler);
            currBowler.incrementWicketsTaken();
            System.out.println(battingTeam.getTeamName() + " are " + (currInning.getWicketsFell())
                    + " down as " + currBowler.getName() + " strikes!");
            if (currInning.getWicketsFell() < this.maxWickets) {
                this.currBatterOnStrike = battingTeam.assignBatter(currInning.getWicketsFell());
            }
        }
    }

    private boolean inningEndCheck(int inningNo) {
        //End inning if all wickets down
        if (currInning.getWicketsFell() == maxWickets) {
            currInning.setInningEnded();
            currBatterOnStrike = null;
            System.out.println("Innings ended!");
            return true;
        }
        // End inning if target reached
        if (inningNo == 2) {
            if (currInning.getRunsScored() >= matchTarget) {
                currInning.setInningEnded();
                System.out.println("Match Ended!");
                return true;
            }
        }
        return false;
    }

    public void changeOver(int overNo){
        //Update bowler and print over stats
        currBowler.addBallsBowled(6);
        currInning.setOversBowled(overNo);
        changeStrike();
        currInning.printPostOverStats(currBowler,currBatterOnStrike,currBatterOffStrike);
    }

    public void changeStrike() {
        Player temp = this.currBatterOnStrike;
        this.currBatterOnStrike = this.currBatterOffStrike;
        this.currBatterOffStrike = temp;
    }

    public void printScoreboard(Team battingTeam, Team bowlingTeam) {
        scorecard.printResults(battingTeam,bowlingTeam);
        System.out.println("ScoreCard");
        scorecard.print();
    }

    public Team[] flipCoin() {
        int toss = (int) (Math.random() * 2);
        Team[] teamOrder = new Team[2];

        // Shuffled random team order for toss
        if (toss == 1) {
            teamOrder[0] = team1;
            teamOrder[1] = team2;
        } else {
            teamOrder[0] = team2;
            teamOrder[1] = team1;
        }
        return teamOrder;
    }

    public void createScorecard(Team battingTeam, Team bowlingTeam){
        this.scorecard = new Scorecard(battingTeam,bowlingTeam,maxWickets);
    }

    public Team getWinner(){
        return scorecard.getWinner();
    }

    public Team getTeam1() {
        return team1;
    }
    public Team getTeam2() {
        return team2;
    }

    public int getMatchOvers() {
        return matchOvers;
    }
    public int getMatchTarget() {
        return matchTarget;
    }

    public MatchStats giveMatchStats(){
        return scorecard.getCurrentMatchStats();
    }

    public void setMatchTarget(int matchTarget) {
        this.matchTarget = matchTarget;
        scorecard.setMatchTarget(matchTarget);
    }
}