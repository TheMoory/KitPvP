package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class GameStatsHandler extends GameSetting {

    private final ArrayList<GameStats> stats;
    private final ArrayList<GameStats.STATSTYPE> statsTypes;
    public GameStatsHandler(Kit kit, ArrayList<GameStats.STATSTYPE> statsTypes){
        super(kit);
        this.stats = new ArrayList<>();
        this.statsTypes = statsTypes;
    }


    private GameStats getGameStatsFromPlayer(Player player){
        for(GameStats gameStats : stats){
            if(gameStats.getPlayer().getUniqueId().toString().equals(player.getUniqueId().toString())){
                return gameStats;
            }
        }
        return null;
    }

    @Override
    public void onGameStart(){
        for(Team team : kit.getGame().getAliveTeams()){
            for(Player player : team.getPlayers()){
                GameStats gameStats = new GameStats(player);
                for(GameStats.STATSTYPE statstype : statsTypes){
                    if(statstype.equals(GameStats.STATSTYPE.TIME)){
                        gameStats.setStat(statstype, System.currentTimeMillis());
                    }else{
                        gameStats.setStat(statstype, statstype.getDefaultValue());
                    }
                }
                stats.add(gameStats);
            }
        }
    }

    @Override
    public void onGameEnd(){
        HashMap<Integer,Double> hearts = new HashMap<>();
        for(Team team : getGame().getTeams()){
            double teamHearts = 0;
            for(Player player : team.getPlayers()){
                teamHearts = teamHearts + player.getHealth();
            }
            hearts.put(team.getTeamID(), teamHearts);
        }
        for(Team team : getGame().getTeams()){
            for(Player player : team.getPlayers()){
                GameStats gameStats = getGameStatsFromPlayer(player);
                player.sendMessage(ChatColor.GREEN + "------" + ChatColor.DARK_RED + " Stats " + ChatColor.GREEN + "-------");
                for(GameStats.STATSTYPE statstype : statsTypes){
                    assert gameStats != null;
                    if(statstype.equals(GameStats.STATSTYPE.TIME)){
                        long start = (Long) gameStats.getStat(statstype);
                        long now = System.currentTimeMillis();
                        long duration = (now - start) / 1000;
                        player.sendMessage(ChatColor.GRAY + statstype.getName() + " " + ChatColor.GREEN + duration + " sec");
                    }else {
                        player.sendMessage(ChatColor.GRAY + statstype.getName() + " " + ChatColor.GREEN + gameStats.getStat(statstype));
                    }
                }
                player.sendMessage(ChatColor.GREEN + "------" + ChatColor.DARK_RED + " ------ " + ChatColor.GREEN + "-------");
            }
        }
    }

    @Override
    public void onSoup(Object o){
        Player player = (Player) o;
        GameStats gameStats = getGameStatsFromPlayer(player);
        assert gameStats != null;
        gameStats.setStat(GameStats.STATSTYPE.EATEN_SOUPS, (Integer) gameStats.getStat(GameStats.STATSTYPE.EATEN_SOUPS) + 1);
    }

    @Override
    public void onDamage(EntityDamageEvent e){
        if(e.isCancelled()) return;
        if(kit.getGame().getGameState().equals(Game.GameState.RUNNING)){
            if(e.getEntityType().equals(EntityType.PLAYER)){
                Player player = (Player) e.getEntity();
                GameStats gameStats = getGameStatsFromPlayer(player);
                assert gameStats != null;
                gameStats.setStat(GameStats.STATSTYPE.DAMAGE_RECEIVED, (double) gameStats.getStat(GameStats.STATSTYPE.DAMAGE_RECEIVED) + e.getDamage());
            }
        }
    }
    @Override
    public void onDamageByEntity(EntityDamageByEntityEvent event){
        if(kit.getGame().getGameState().equals(Game.GameState.RUNNING)){
            if(!(event.getDamager() instanceof Player)) return;
            Player damager = (Player) event.getDamager();
            GameStats gameStats = getGameStatsFromPlayer(damager);
            assert gameStats != null;
            gameStats.setStat(GameStats.STATSTYPE.DAMAGE_CAUSED, (double) gameStats.getStat(GameStats.STATSTYPE.DAMAGE_CAUSED) + event.getDamage());
            gameStats.setStat(GameStats.STATSTYPE.HITS, (int) gameStats.getStat(GameStats.STATSTYPE.HITS) + 1);
        }
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event){
        if(kit.getGame().getGameState().equals(Game.GameState.RUNNING)){
            GameStats gameStats = getGameStatsFromPlayer(event.getPlayer());
            if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK){
                gameStats.setStat(GameStats.STATSTYPE.MISSED_HITS, (int) gameStats.getStat(GameStats.STATSTYPE.MISSED_HITS) + 1);
            }
        }
    }
}
