package de.themoory.kitpvp.listeners;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.gamesettings.KitItemSetter;
import de.themoory.kitpvp.kits.Lobby;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.ArrayList;

public class JoinListener implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e){
        e.getPlayer().sendMessage(ChatColor.GREEN +""+ ChatColor.BOLD + "HELLO TO KITPVP 1.0");
        e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), 63, 83, -65));
        new KitItemSetter(new Lobby()).setKitItemsForPlayer(e.getPlayer());
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent e){
        ArrayList<GameSetting> settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for (GameSetting gameSetting : settings) {
            gameSetting.onPlayerQuit(e);
        }
    }

}
