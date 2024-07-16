package de.themoory.kitpvp.listeners;

import de.themoory.kitpvp.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class JoinListener implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e){
        e.getPlayer().chat("HELLO TO KITPVP 1.0");
        e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), 63, 83, -65));
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        e.getPlayer().getInventory().setItem(0, sword);
    }

}
