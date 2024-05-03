package de.themoory.kitpvp.utils;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.gamesettings.InviteHandler;
import de.themoory.kitpvp.kits.Lobby;
import org.bukkit.entity.Player;

public class Utils {

    public static GameSetting[] getGameSettingsFromGame(Game g){
        Kit k;
        if(g == null || g.getGameState() == Game.GameState.WAITING){
            k = new Lobby();
        } else {
            k = g.getKit();
        }
        if(k == null){
            return new GameSetting[0];
        }
        return k.getGameSettings();
    }

    public static Team getTeamFromPlayer(Player player){
        Game game = KitPvP.getInstance().getCurrentGameOfPlayer(player);
        assert game != null;
        for(Team team : game.getTeams()){
            for(Player player1 : team.getPlayers()){
                if(player1.getUniqueId().toString().equals(player.getUniqueId().toString())){
                    return team;
                }
            }
        }
        return null;
    }
}
