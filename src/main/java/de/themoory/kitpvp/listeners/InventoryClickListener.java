package de.themoory.kitpvp.listeners;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.sql.SQLException;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e) throws SQLException, IllegalArgumentException{
        GameSetting[] settings = Utils.getGameSettingsFromGame(
                KitPvP.getInstance().getCurrentGameOfPlayer((Player) e.getWhoClicked()));
        for(GameSetting gameSetting : settings){
            gameSetting.onInventoryClick(e);
        }
    }
}
