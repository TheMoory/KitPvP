package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.utils.*;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class OneLive extends GameSetting {

    public OneLive(Kit k){
        super(k);

    }


    @Override
    public void onDeath(PlayerDeathEvent e) {
        handleDeath(e.getEntity());
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent e) {
        handleDeath(e.getPlayer());
    }

    private void handleDeath(Player player){

        Game game = KitPvP.getInstance().getCurrentGameOfPlayer(player);
        assert game != null;
        if(game.getMode().equals(Arena.MODE.one_vs_one)){
            for(Team team : game.getTeams()){
                if(!team.getPlayers().get(0).getUniqueId().toString().equals(player.getUniqueId().toString())){
                    game.onEnd(team.getTeamID());
                }
            }
        }else if(game.getMode().equals(Arena.MODE.two_vs_two)){
            Team team = Utils.getTeamFromPlayer(player);
            assert team != null;
            team.getDeaths().put(player.getUniqueId(), team.getDeaths().get(player.getUniqueId())+1);
            int death = 0;
            for(UUID uuid : team.getDeaths().keySet()){
                if(team.getDeaths().get(uuid) > 0){
                    death++;
                }
            }
            if(death >= 2){
                for(Team team1 : game.getTeams()){
                    if(team.getTeamID() != team1.getTeamID()){
                        game.onEnd(team1.getTeamID());
                    }
                }
            }else{
                player.setGameMode(GameMode.SPECTATOR);
            }
        }
        player.spigot().respawn();
    }
}