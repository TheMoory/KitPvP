package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoFallDamage extends GameSetting {
    public NoFallDamage(Kit k){
        super(k);
    }

    @Override
    public void onDamage(EntityDamageEvent e) {
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }
}
