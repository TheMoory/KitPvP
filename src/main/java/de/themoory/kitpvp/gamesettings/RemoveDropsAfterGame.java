package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.entity.Item;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.ArrayList;

public class RemoveDropsAfterGame extends GameSetting {

    private ArrayList<Item> droppedItems;
    public RemoveDropsAfterGame(Kit k){
        super(k);
        droppedItems = new ArrayList<Item>();
    }
    @Override
    public void onDrop(PlayerDropItemEvent e){
        droppedItems.add(e.getItemDrop());
    }

    @Override
    public void onGameEnd() {
        for(Item i: droppedItems){
            i.remove();
        }
    }
}
