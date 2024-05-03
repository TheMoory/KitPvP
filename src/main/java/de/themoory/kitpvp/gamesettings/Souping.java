package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Souping extends GameSetting {
    @Override
    public void onPlayerInteraction(PlayerInteractEvent e) {
        if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && (
                e.getPlayer().getItemInHand().getType() == Material.MUSHROOM_SOUP
                )){
            double health = e.getPlayer().getHealth();
            if(health < 20){
                double newHealth = Math.min(health + 7d, 20d);
                e.getPlayer().chat("Sooooup");
                e.getPlayer().getItemInHand().setType(Material.BOWL);
                e.getPlayer().updateInventory();
                e.getPlayer().setHealth(newHealth);
            }
        }
    }
}
