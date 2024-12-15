package de.themoory.kitpvp.listeners;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.sql.SQLException;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) throws SQLException {
        ArrayList<GameSetting> settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for(GameSetting gameSetting : settings){
            gameSetting.onMove(e);
        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        ArrayList<GameSetting> settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for(GameSetting gameSetting : settings){
            gameSetting.onDrop(e);
        }
    }

    @EventHandler
    public void PlayerAchievementAwardedEvent(PlayerAchievementAwardedEvent e) {
        ArrayList<GameSetting> settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for(GameSetting gameSetting : settings){
            gameSetting.onAchievementAwarded(e);
        }

    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
        ArrayList<GameSetting> settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer((Player)e.getEntity()));
        for(GameSetting gameSetting : settings){
            gameSetting.onHunger(e);
        }

    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            System.out.println("Weather Change False");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            ArrayList<GameSetting> settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer((Player) e.getEntity()));
            for (GameSetting gameSetting : settings) {
                gameSetting.onDamage(e);
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player) {
            ArrayList<GameSetting> settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer((Player) e.getEntity()));
            for (GameSetting gameSetting : settings) {
                gameSetting.onDamageByEntity(e);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        ArrayList<GameSetting> settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getEntity()));
        for (GameSetting gameSetting : settings) {
            gameSetting.onDeath(e);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for (GameSetting gameSetting : settings) {
            gameSetting.onPlayerInteract(e);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for (GameSetting gameSetting : settings) {
            gameSetting.onBlockBreak(e);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for (GameSetting gameSetting : settings) {
            gameSetting.onBlockPlace(e);
        }
    }

    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent e){
        GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for (GameSetting gameSetting : settings) {
            gameSetting.onPlayerBucketEmpty(e);
        }
    }

    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent e){
        GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for (GameSetting gameSetting : settings) {
            gameSetting.onPlayerBucketFill(e);
        }
    }


    @EventHandler
    public void onPlayerSwing(PlayerAnimationEvent e){
        ArrayList<GameSetting> settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for (GameSetting gameSetting : settings) {
            gameSetting.onPlayerSwing(e);
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        if(e.getEntity().getShooter() instanceof Player){
            ArrayList<GameSetting> settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer((Player) e.getEntity().getShooter()));
            for (GameSetting gameSetting : settings) {
                gameSetting.onProjectileLaunch(e);
            }
        }
    }
}
