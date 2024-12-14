package de.themoory.kitpvp.utils;

import de.themoory.kitpvp.KitPvP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Game {
    private final ArrayList<Team> teams;

    private final ArrayList<Team> aliveTeams;
    private final ArrayList<Team> deadTeams;
    private final ArrayList<UUID> invites;

    private final ArrayList<Player> spectators;
    private Kit kit;

    private Arena arena;

    private Arena.MAP map;

    private final KitPvP instance;

    private GameState gameState;

    private final Arena.MODE mode;

    private final UUID uuid;


    public Game(KitPvP instance, Player firstPlayer, Arena.MODE mode){
        this.instance = instance;
        this.uuid = UUID.randomUUID();
        this.mode = mode;
        this.kit = null;
        this.arena = null;
        this.map = null;
        this.teams = new ArrayList<>();
        this.invites =new ArrayList<>();
        this.aliveTeams = new ArrayList<>();
        this.deadTeams = new ArrayList<>();
        this.teams.add(new Team(firstPlayer, 0, mode.getPlayerCount()/mode.getTeamCount()));
        for (int i = 1; i <= mode.getPlayerCount()/mode.getTeamCount(); i++) {
            this.teams.add(new Team(null, i, mode.getPlayerCount()/mode.getTeamCount()));
        }
        aliveTeams.addAll(teams);
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
        for(Team team : teams){
            if(!team.isFull()){
                return;
            }
        }
        /*if(start().equals(GameStartResult.)){

        }*/
        GameStartResult gameStartResult = start();
        if(gameStartResult.equals(GameStartResult.NO_FREE_ARENA_FOUND)){
            firstPlayer.sendMessage("Zurzeit ist keine Arena frei, du befindest dich nun in der Warteschlange");
            instance.getArenaQueue().get(this.getArena().getMapType()).add(this);
        }else{
            firstPlayer.sendMessage(gameStartResult.toString());
        }
    }

    public void addDeadTeam(Team team){
        aliveTeams.remove(team);
        deadTeams.add(team);
    }

    public ArrayList<Team> getAliveTeams() {
        return aliveTeams;
    }

    public ArrayList<Team> getDeadTeams() {
        return deadTeams;
    }

    public void setKit(Kit kit){
        this.kit = kit;
        this.kit.setGame(this);
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

        //Starting

        for(GameSetting gameSetting : getKit().getGameSettings()){
            gameSetting.onGameStart();
        }

        gameState = GameState.RUNNING;
        instance.removeStagedGames(this);
        instance.addToCurrentGames(this);
        return GameStartResult.SUCCESS;
    }

    public void onEnd(int winnerTeamID){
        arena.setState(Arena.STATE.RESTARTING);
        instance.removeCurrentGames(this);
        GameSetting[] settings = Utils.getGameSettingsFromGame(this);
        for(GameSetting gameSetting : settings){
            gameSetting.onGameEnd();
        }

        arena.setState(Arena.STATE.WAITING);

        new BukkitRunnable() {
            @Override
            public void run() {
                Queue<Game> arenaQueue = instance.getArenaQueue().get(getArena().getMapType());
                if(!arenaQueue.isEmpty()){
                    Game game = arenaQueue.poll();
                    game.setArena(arena);
                    game.start();
                }
            }
        }.runTaskLater(instance, 20L * 3);

    }

    public enum GameStartResult{
        SUCCESS,
        NO_KIT_SELECTED,
        NOT_ENOUGH_PLAYERS,
        NO_MAP_SELECTED,
        NO_FREE_ARENA_FOUND


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
        RUNNING
    }



}