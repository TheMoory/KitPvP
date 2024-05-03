package de.themoory.kitpvp.utils;

import de.themoory.kitpvp.KitPvP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Game {
    private ArrayList<Team> teams;
    private ArrayList<UUID> invites;

    private ArrayList<Player> spectators;
    private Kit kit;

    private Arena arena;

    private Arena.MAP map;

    private KitPvP instance;

    private GameState gameState;

    private Arena.MODE mode;

    private UUID uuid;


    public Game(KitPvP instance, Player firstPlayer, Arena.MODE mode){
        this.instance = instance;
        this.uuid = UUID.randomUUID();
        this.mode = mode;
        this.kit = null;
        this.arena = null;
        this.map = null;
        this.teams = new ArrayList<>();
        this.invites =new ArrayList<>();
        this.teams.add(new Team(firstPlayer, 0, mode.getPlayerCount()/mode.getTeamCount()));
        for (int i = 1; i <= mode.getPlayerCount()/mode.getTeamCount(); i++) {
            this.teams.add(new Team(null, i, mode.getPlayerCount()/mode.getTeamCount()));
        }
        this.spectators = new ArrayList<>();
        this.gameState = GameState.WAITING;
        instance.addToStagedGames(this);
    }

    public boolean addPlayer(Player player){
        for(Team team : teams){
            if(!team.isFull()){
                team.addPlayer(player);
                removeInvite(player);
                checkIfRoundFull();
                return true;
            }
        }
        return false;
    }

    public void checkIfRoundFull(){
        int playerCount = 0;
        for(Team team : teams){
            if(!team.isFull()){
                return;
            };
        }
        start();
    }

    public void setKit(Kit kit){
        this.kit = kit;
    }

    public void setArena(Arena arena){
        this.arena = arena;
    }

    public void addSpectator(Player player){
        spectators.add(player);
    }

    public void addInvite(Player player) {
        invites.add(player.getUniqueId());
    }

    public void removeInvite(Player player) {
        invites.remove(player.getUniqueId());
    }

    public UUID getUuid() {
        return uuid;
    }

    public Arena.MODE getMode() {
        return mode;
    }

    public void setMap(Arena.MAP map) {
        this.map = map;
    }



    public boolean isInvited(Player player) {return invites.contains(player.getUniqueId());}
    public GameStartResult start(){
        if(kit == null){
            return GameStartResult.NO_KIT_SELECTED;
        }
        if(map == null){
            return GameStartResult.NO_MAP_SELECTED;
        }


        //TODO Then check if the choosed Arena is free and set Spawns

        if((arena = ArenaPool.getFreeArea(map)) == null){
            return GameStartResult.NO_FREE_ARENA_FOUND;
        }
        this.setArena(arena);

        if(getPlayerCount() < arena.getMinPlayers()){
            return GameStartResult.NOT_ENOUGH_PLAYERS;
        }

        // Teleport players to the spawnpoints in the arena
        for(Team team : teams){
            int i = 0;
            for(Player player : team.getPlayers()){
                player.teleport(arena.getSpawnLocation(team.getTeamID(), i));
                for(int i1 : kit.getInventory().keySet()){
                    player.getInventory().setItem(i1, kit.getInventory().get(i1));
                }
                i++;
            }
        }
        gameState = GameState.RUNNING;
        instance.removeStagedGames(this);
        instance.addToCurrentGames(this);
        return GameStartResult.SUCCESS;
    }

    public void onEnd(int winnerTeamID){
        instance.removeCurrentGames(this);
        Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Team team : teams){
                    for(Player player : team.getPlayers()){
                        player.teleport(new Location(Bukkit.getWorld("world"), 0, 100, 0, 0, 0));
                    }
                }
            }
        }, 4);

    }

    public enum GameStartResult{
        SUCCESS,
        NO_KIT_SELECTED,
        NOT_ENOUGH_PLAYERS,
        NO_MAP_SELECTED,
        NO_FREE_ARENA_FOUND;


    }

    public int getPlayerCount(){
        int c = 0;
        for(Team team : teams){
            c += team.getPlayers().size();
        }
        return c;
    }


    public Kit getKit(){
        return this.kit;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team){
        this.teams.add(team);
    }

    public GameState getGameState() {
        return gameState;
    }

    public ArrayList<Player> getSpectators() {
        return spectators;
    }

    public Arena getArena() {
        return arena;
    }

    public enum GameState{
        WAITING,
        RUNNING;
    }



}