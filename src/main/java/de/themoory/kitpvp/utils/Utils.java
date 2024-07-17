package de.themoory.kitpvp.utils;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.kits.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

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

    public static HashMap<Integer, ItemStack> getInventoryFromGame(Game g){
        Kit k;
        if(g == null || g.getGameState() == Game.GameState.WAITING){
            k = new Lobby();
        } else {
            k = g.getKit();
        }
        if(k == null) {
            return new HashMap<Integer, ItemStack>();
        }
        return k.getInventory();
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

    public static void setInventory(HashMap<Integer, ItemStack> inventory, Player player){
        for(int i1 : inventory.keySet()){
            player.getInventory().setItem(i1, inventory.get(i1));
        }
    }
}
