package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import de.themoory.kitpvp.utils.Team;
import de.themoory.kitpvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RemoveItemsAfterGame extends GameSetting {
    public RemoveItemsAfterGame(Kit k){
        super(k);
    }

    @Override
    public void onGameEnd() {

        Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.getInstance(), () -> {
            for(Team team : kit.getGame().getTeams()){
                for(Player player : team.getPlayers()){
                    player.getInventory().clear();
                    Utils.setInventory(Utils.getInventoryFromGame(null),player);
                    player.getInventory().setHelmet(null);
                    player.getInventory().setChestplate(null);
                    player.getInventory().setLeggings(null);
                    player.getInventory().setBoots(null);
                    player.setHealth(20);
                    player.teleport(new Location(Bukkit.getWorld("world"), 63, 85, -65, 0, 0));
                }
            }
            for(Player player : kit.getGame().getSpectators()){
                player.teleport(new Location(Bukkit.getWorld("world"), 63, 85, -65, 0, 0));
            }
        }, 4);
    }
}
