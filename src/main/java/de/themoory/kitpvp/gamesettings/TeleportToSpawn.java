package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;

public class TeleportToSpawn extends GameSetting {
    private int fromHeight;
    public TeleportToSpawn(Kit k, int fromHeight){
        super(k);
        this.fromHeight = fromHeight;
    }

    @Override
    public void onMove(PlayerMoveEvent e) {
        if(e.getPlayer().getLocation().getY() < fromHeight){
            e.getPlayer().teleport(new Location(Bukkit.getWorld("world"),63, 85, -65, 0, 0));
        }
    }
}
