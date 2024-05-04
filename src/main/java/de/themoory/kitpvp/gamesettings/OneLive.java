package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.utils.*;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class OneLive extends GameSetting {

    public OneLive(){

    }


    @Override
    public void onDeath(PlayerDeathEvent e) {
        Game game = KitPvP.getInstance().getCurrentGameOfPlayer(e.getEntity());
        Player player = e.getEntity();
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