package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.Game;
import de.themoory.kitpvp.utils.GameSetting;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class NoHunger extends GameSetting {
    public NoHunger(){
        super();
    }

    @Override
    public void onHunger(FoodLevelChangeEvent e){
        if(e.getFoodLevel() < 20){
            ((Player)e.getEntity()).setFoodLevel(20);
        }
    }
}
