package de.themoory.kitpvp.listeners;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Utils;
import org.bukkit.ChatColor;
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
        e.getPlayer().sendMessage(ChatColor.GREEN +""+ ChatColor.BOLD + "HELLO TO KITPVP 1.0");
        e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), 63, 83, -65));
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        e.getPlayer().getInventory().setItem(0, sword);
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent e){
        GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for (GameSetting gameSetting : settings) {
            gameSetting.onPlayerQuit(e);
        }
    }

}
