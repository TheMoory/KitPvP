package de.themoory.kitpvp.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

public abstract class GameSetting {
    protected Kit kit;
    public GameSetting(Kit k){
        this.kit = k;
    }
    public void onHunger(FoodLevelChangeEvent e){}

    public void onDamage(EntityDamageEvent e){}

    public void onDrop(PlayerDropItemEvent e){}

    public void onMove(PlayerMoveEvent e){}

    public void onAchievementAwarded(PlayerAchievementAwardedEvent e){
        if(e instanceof Player) {
            e.setCancelled(true);
        }
    }

    public void onInventoryClick(InventoryClickEvent e){}

    public void onPlayerInteraction(PlayerInteractEvent e) {}

    public void onDamageByEntity(EntityDamageByEntityEvent e){}

    public void onDeath(PlayerDeathEvent e){}

    public void onGameEnd(){}

    public void onGameStart(){}

    public Game getGame(){
        return kit.getGame();
    }

    public void onPlayerInteract(PlayerInteractEvent e){
    }

    public void onBlockPlace(BlockPlaceEvent e){
    }

    public void onBlockBreak(BlockBreakEvent e) {}

    public void onPlayerQuit(PlayerQuitEvent e){}

    public void onPlayerBucketFill(PlayerBucketFillEvent e){

    }

    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent e){

    }

    public void onSoup(Object o){}
}
