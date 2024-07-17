package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.event.entity.EntityDamageEvent;

public class Invincible extends GameSetting{

    public Invincible(Kit k){
        super(k);
    }
    @Override
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }
}

