package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.Game;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class NoHunger extends GameSetting {
    public NoHunger(Kit k){
        super(k);
    }

    @Override
    public void onHunger(FoodLevelChangeEvent e){
        if(e.getFoodLevel() < 20){
            ((Player)e.getEntity()).setFoodLevel(20);
        }
    }
}
