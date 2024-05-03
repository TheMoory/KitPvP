package de.themoory.kitpvp.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class Team {

    private ArrayList<Player> players;

    private int maxPlayers;
    private int teamID;

    private HashMap<UUID, Integer> deaths;
    public Team(Player player, int teamID, int maxPlayers){
        this.deaths = new HashMap<>();
        this.players = new ArrayList<>();
        if(player != null){
            this.players.add(player);
            this.deaths.put(player.getUniqueId(), 0);
        }
        this.teamID = teamID;
        this.maxPlayers = maxPlayers;
        this.deaths = new HashMap<>();
    }

    public void addPlayer(Player player){
        this.players.add(player);
        this.deaths.put(player.getUniqueId(), 0);
    }

    public void removePlayer(Player player){
        this.players.remove(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getTeamID(){
        return teamID;
    }

    public boolean isFull(){
        return maxPlayers == this.players.size();
    }

    public HashMap<UUID, Integer> getDeaths() {
        return deaths;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}