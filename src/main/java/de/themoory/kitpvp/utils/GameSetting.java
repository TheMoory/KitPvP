package de.themoory.kitpvp.utils;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public abstract class GameSetting {
    public GameSetting(){
    }
    public void onHunger(FoodLevelChangeEvent e){}

    public void onDamage(EntityDamageEvent e){}

    public void onDrop(PlayerDropItemEvent e){}

    public void onMove(PlayerMoveEvent e){}

    public void onAchievementAwarded(PlayerAchievementAwardedEvent e){}

    public void onInventoryClick(InventoryClickEvent e){}

    public void onPlayerInteraction(PlayerInteractEvent e) {}

    public void onDamageByEntity(EntityDamageByEntityEvent e){}

    public void onDeath(PlayerDeathEvent e){}

}
