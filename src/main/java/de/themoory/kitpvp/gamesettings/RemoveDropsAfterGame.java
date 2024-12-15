package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RemoveDropsAfterGame extends GameSetting {

    private final ArrayList<Item> droppedItems;
    private final ArrayList<Projectile> trackedPearls;
    public RemoveDropsAfterGame(Kit k){
        super(k);
        droppedItems = new ArrayList<>();
        trackedPearls = new ArrayList<>();
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
        for(Projectile p: trackedPearls){
            p.remove();
        }
    }

    @Override
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        List<ItemStack> items = event.getDrops();
        event.getDrops().clear();
        for(ItemStack i: items){
            droppedItems.add(player.getWorld().dropItem(player.getLocation(), i));
        }
    }

    @Override
    public void onProjectileLaunch(ProjectileLaunchEvent event){
        trackedPearls.add(event.getEntity());
    }
}
