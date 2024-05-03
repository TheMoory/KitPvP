package de.themoory.kitpvp.utils;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Arena {
    private UUID uuid;

    private MAP map;

    private int playerCount;

    private int teamCount;

    private int minPlayers;

    private STATE state;

    private HashMap<String, Location> spawns;

    public Arena(MAP map, int playerCount, int teamCount, int minPlayers, HashMap<String, Location> spawns){
        this.map = map;
        this.playerCount = playerCount;
        this.teamCount = teamCount;
        this.minPlayers = minPlayers;
        this.uuid = UUID.randomUUID();
        this.spawns = spawns;
        this.state = STATE.WAITING;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Location getSpawnLocation(int teamID, int playerID){
        return spawns.get(teamID+"_"+playerID);
    }


    public int getPlayerCount() {
        return playerCount;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public HashMap<String, Location> getSpawns() {
        return spawns;
    }

    public STATE getState() {
        return state;
    }


    public MAP getMapType() {
        return map;
    }


    public enum MAP{
        DEFAULT,
        GLADIATOR,
        BAUM,
        EIS;
    }

    public enum MODE{
        one_vs_one(2, 2),
        two_vs_two(2, 4),
        infinite(1,Integer.MAX_VALUE);


        private int teamCount;
        private int playerCount;

        MODE(int teamCount, int playerCount){
            this.teamCount = teamCount;
            this.playerCount = playerCount;
        }

        public int getTeamCount() {
            return teamCount;
        }

        public int getPlayerCount() {
            return playerCount;
        }
    }

    public enum STATE{
        WAITING,
        RUNNING,
        RESTARTING;
    }



        /*public enum Type{
        DEFAULT(2, 2, 2),
        GLADIATOR(2, 2, 2),
        WATWEISICHWASFUERMAPSESNOCHGEBENWIRD(234345434, -7, -645654564);


        private final int playerCount;

        private final int teamCount;

        private final int minPlayers;
        Type(int playerCount, int teamCount, int minPlayers) {
            this.playerCount = playerCount;
            this.teamCount = teamCount;
            this.minPlayers = minPlayers;
        }

        public int getPlayerCount(){
            return playerCount;
        }

        public int getTeamCount() {
            return teamCount;
        }

        public int getMinPlayers() {
            return minPlayers;
        }

    }*/
}
