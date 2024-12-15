package de.themoory.kitpvp.listeners;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryInteractListener implements Listener {
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent e) throws SQLException {
        ArrayList<GameSetting> settings = Utils.getGameSettingsFromGame(
                KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for(GameSetting gameSetting : settings){
            gameSetting.onPlayerInteraction(e);
        }
    }
}
