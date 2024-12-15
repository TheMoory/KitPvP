package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import de.themoory.kitpvp.utils.Team;
import de.themoory.kitpvp.utils.Utils;
import org.bukkit.entity.Player;

public class KitItemSetter extends GameSetting {
    public KitItemSetter(Kit k){
        super(k);
    }
    public void setKitItemsForPlayer(Player player){
        player.getInventory().clear();
        Utils.setInventory(kit.getInventory(),player);
        player.setHealth(20);
        player.getInventory().setHelmet(kit.getArmor().get(0));
        player.getInventory().setChestplate(kit.getArmor().get(1));
        player.getInventory().setLeggings(kit.getArmor().get(2));
        player.getInventory().setBoots(kit.getArmor().get(3));
    }

    @Override
    public void onGameStart() {
        // Teleport players to the spawn points in the arena
        for(Team team : kit.getGame().getTeams()){
            int i = 0;
            for(Player player : team.getPlayers()){
                player.teleport(kit.getGame().getArena().getSpawnLocation(team.getTeamID(), i));
                setKitItemsForPlayer(player);
                i++;
            }
        }
    }
}
