package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class XLive extends GameSetting {

    private final int deathsToTeamEnd;
    private final boolean countPerPlayer;

    public XLive(Kit k, int deathsToTeamEnd, boolean countPerPlayer){
        super(k);
        this.deathsToTeamEnd = deathsToTeamEnd;
        this.countPerPlayer = countPerPlayer;
    }

    @Override
    public void onDeath(PlayerDeathEvent e) {
        Game game = KitPvP.getInstance().getCurrentGameOfPlayer(e.getEntity());
        Player player = e.getEntity();
        assert game != null;
        int teleportPlayerBack = -1;
        Team team = Utils.getTeamFromPlayer(player);
        assert team != null;
        if(game.getMode().equals(Arena.MODE.one_vs_one)){
            team.getDeaths().put(player.getUniqueId(), team.getDeaths().get(player.getUniqueId())+1);
            if(team.getDeaths().get(player.getUniqueId())>=deathsToTeamEnd){
                for(Team team1 : game.getTeams()){
                    if(!team1.getPlayers().get(0).getUniqueId().toString().equals(player.getUniqueId().toString())){
                        game.onEnd(team1.getTeamID());
                    }
                }
            }else{
                teleportPlayerBack = team.getTeamID();
            }
        }else{
            team.getDeaths().put(player.getUniqueId(), team.getDeaths().get(player.getUniqueId())+1);
            int deathPlayers = 0;
            if(countPerPlayer){
                for(Player player1 : team.getPlayers()){
                    if(team.getDeaths().get(player1.getUniqueId())>=deathsToTeamEnd){
                        deathPlayers++;
                    }
                }
                if(deathPlayers == team.getPlayers().size()){
                    game.addDeadTeam(team);
                    if(game.getAliveTeams().size() == 1) {
                        game.onEnd(game.getAliveTeams().get(0).getTeamID());
                    }
                }else{
                    if(team.getDeaths().get(player.getUniqueId())>=deathsToTeamEnd){
                        player.setGameMode(GameMode.SPECTATOR);
                    }else{
                        teleportPlayerBack = team.getTeamID();
                    }
                }
            }else{
                for(Player player1 : team.getPlayers()){
                    deathPlayers += team.getDeaths().get(player1.getUniqueId());
                }
                if(deathPlayers >= deathsToTeamEnd){
                    game.addDeadTeam(team);
                    if(game.getAliveTeams().size() == 1){
                        game.onEnd(game.getAliveTeams().get(0).getTeamID());
                    }else{
                        for(Player player1 : team.getPlayers()){
                            player1.setGameMode(GameMode.SPECTATOR);
                        }

                    }
                }else{
                    teleportPlayerBack = team.getTeamID();
                }
            }
        }
        player.spigot().respawn();
        if(teleportPlayerBack != -1){
            player.teleport(kit.getGame().getArena().getSpawnLocation(teleportPlayerBack, 0));
        }
    }


    public enum LIVES{
        ONE(1),
        TWO(2),
        FIVE(5),
        TEN(10),
        TWENTY(20),;

        private final int lives;

        LIVES(int lives){
            this.lives = lives;
        }

        public int getLives() {
            return lives;
        }
    }
}
