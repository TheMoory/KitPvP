package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;

public class DeathUnderHeight extends GameSetting {
    int underHeight;
    public DeathUnderHeight(Kit k, int underHeight){
        super(k);
        this.underHeight = underHeight;
    }

    @Override
    public void onMove(PlayerMoveEvent e) {
        if(e.getPlayer().getLocation().getY() < underHeight){
            e.getPlayer().damage(99999999);
        }
    }
}
