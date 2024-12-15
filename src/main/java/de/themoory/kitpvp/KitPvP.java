package de.themoory.kitpvp;

import de.themoory.kitpvp.commands.KitPvPCMD;
import de.themoory.kitpvp.listeners.*;
import de.themoory.kitpvp.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public final class KitPvP extends JavaPlugin {

    public static KitPvP instance;


    private ArrayList<Game> currentGames;
    private ArrayList<Game> stagedGames;
    private ArrayList<PlayerSettings> playerSettings;

    HashMap<Arena.MAP, Queue<Game>> arenaQueue;


    @Override
    public void onEnable() {
        Bukkit.getWorld("world").setGameRuleValue("keepInventory", "true");
        instance = this;
        registerEvents();
        registerCommands();
        stagedGames = new ArrayList<>();
        currentGames = new ArrayList<>();
        playerSettings = new ArrayList<>();
        arenaQueue = new HashMap<>();


        for(Arena.MAP mapping : Arena.MAP.values()) {
            arenaQueue.put(mapping, new LinkedList<>());
        }

        new ArenaPool();
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static KitPvP getInstance() {
        return instance;
    }




    public ArrayList<Game> getCurrentGames() {
        return currentGames;
    }

    public ArrayList<Game> getStagedGames() {
        return stagedGames;
    }

    public void removeStagedGames(Game game){
        stagedGames.removeIf(game1 -> game1.getUuid().toString().equals(game.getUuid().toString()));
    }

    public void removeCurrentGames(Game game){
        currentGames.removeIf(game1 -> game1.getUuid().toString().equals(game.getUuid().toString()));
    }

    public void addToCurrentGames(Game game){
        this.currentGames.add(game);
    }

    public void addToStagedGames(Game game){
        this.stagedGames.add(game);
    }

    public HashMap<Arena.MAP, Queue<Game>> getArenaQueue() {
        return arenaQueue;
    }


    private void registerEvents() {

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new InventoryClickListener(), this);
        pluginManager.registerEvents(new InventoryInteractListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new LeaveListener(), this);
        pluginManager.registerEvents(new PlayerEvents(), this);

    }
    private void registerCommands() {
        //getCommand("kitpvp").setExecutor(new KitPvPCMD());
    }

    public Game getCurrentGameOfPlayer(Player p) {
        for(Game game : stagedGames){
            for(Team team : game.getTeams()){
                for(Player player : team.getPlayers()){
                    if(player.getUniqueId() == p.getUniqueId()){
                        return game;
                    }
                }
            }
        }

        for(Game game : currentGames){
            for(Team team : game.getTeams()){
                for(Player player : team.getPlayers()){
                    if(player.getUniqueId() == p.getUniqueId()){
                        return game;
                    }
                }
            }
        }
        return null;
    }

    public PlayerSettings getCurrentPlayerSetting(Player p) {
        for(PlayerSettings settings : playerSettings) {
            if(settings.getPlayer().getUniqueId() == p.getUniqueId()){
                return settings;
            }
        }
        PlayerSettings newSettings = new PlayerSettings(p);
        playerSettings.add(newSettings);
        return newSettings;
    }

}
