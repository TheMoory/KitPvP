package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;

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
        for(Team team : getGame().getTeams()){
            for(Player player : team.getPlayers()){
                GameStats gameStats = getGameStatsFromPlayer(player);
                for(GameStats.STATSTYPE statstype : statsTypes){
                    assert gameStats != null;
                    if(statstype.equals(GameStats.STATSTYPE.TIME)){
                        long start = (Long) gameStats.getStat(statstype);
                        long now = System.currentTimeMillis();
                        long duration = (now - start) / 1000;
                        player.sendMessage(ChatColor.GRAY + statstype.toString() + " " + ChatColor.GREEN + duration + " sec");
                    }else {
                        player.sendMessage(ChatColor.GRAY + statstype.toString() + " " + ChatColor.GREEN + gameStats.getStat(statstype));
                    }
                }
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
        if(kit.getGame().getGameState().equals(Game.GameState.RUNNING)){
            Player player = (Player) e.getEntity();
            GameStats gameStats = getGameStatsFromPlayer(player);
            assert gameStats != null;
            gameStats.setStat(GameStats.STATSTYPE.DAMAGE_RECEIVED, (double) gameStats.getStat(GameStats.STATSTYPE.DAMAGE_RECEIVED) + e.getDamage());
        }

    }


}
