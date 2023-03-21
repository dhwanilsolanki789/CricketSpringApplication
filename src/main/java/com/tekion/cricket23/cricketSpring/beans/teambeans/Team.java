package com.tekion.cricket23.cricketSpring.beans.teambeans;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "teams")
public class Team {
    @Id
    private String id;
    private String teamName;
    private List<String> playerIds;
    @Transient
    private static final int PLAYER_COUNT = 11;

    public Team(){}
    public Team(String name, List<String> playerIds) {
        this.teamName = name;
        this.playerIds = playerIds;
    }

    public String getTeamId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<String> getPlayerIds(){
        return playerIds;
    }
    public String getPlayerIdAt(int index){
        return playerIds.get(index);
    }
    public static int getPlayerCount() {
        return PLAYER_COUNT;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", teamName='" + teamName + '\'' +
                ", playerIds=" + playerIds +
                '}';
    }
}
