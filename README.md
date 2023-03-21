# CricketSpringApplication
This is a Cricket Game application using SpringBoot and Mongo.

- You can create Team with the player names and types.
- You can start a series with a given number of matches and overs by providing any 2 teams from the teams that you create.

- You can also get match details with player and team stats details by providing the matchId

- You can also extract a player stats from a given match by providing the playerId and matchId


## List of Apis
Following are the list of apis exposed to play and manage game

* POST /team -> Takes team data in TeamDto format and creates a team
* POST /startgame -> Takes the team name, no of overs and no of matches to play series
* GET /match/{matchId} -> Takes the matchId and outputs the match details
* GET /player-stats/{playerId}/match/{matchId} -> Takes the player and match id to output player stats of a given player in given match
* DELETE /series/{seriesId} -> Takes the series id of the series to be deleted and deletes relevant matches' data and their data from players stats
* DELETE /match/{matchId} -> Takes the match id of the match to be deleted and deletes its data from player stats. (**Not recommended! Only delete single match  not associated with any series**)
* DELETE /team/{teamId} -> Takes the team id of the team to be deleted and deletes the players and their data as well. (**Not recommended! Delete respective team's matches and series too**)