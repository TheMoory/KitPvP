package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import org.bukkit.event.entity.EntityDamageEvent;

public class Invincible extends GameSetting{
    @Override
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }
}

