package de.themoory.kitpvp.listeners;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.sql.SQLException;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) throws SQLException {
        GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for(GameSetting gameSetting : settings){
            gameSetting.onMove(e);
        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for(GameSetting gameSetting : settings){
            gameSetting.onDrop(e);
        }
    }

    @EventHandler
    public void PlayerAchievementAwardedEvent(PlayerAchievementAwardedEvent e) {
        GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getPlayer()));
        for(GameSetting gameSetting : settings){
            gameSetting.onAchievementAwarded(e);
        }

    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
        GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer((Player)e.getEntity()));
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
            GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer((Player) e.getEntity()));
            for (GameSetting gameSetting : settings) {
                gameSetting.onDamage(e);
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player) {
            ((Player) e.getEntity()).chat("Listener activated!");
            GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer((Player) e.getEntity()));
            for (GameSetting gameSetting : settings) {
                ((Player) e.getEntity()).chat("Setting:" + gameSetting);
                gameSetting.onDamageByEntity(e);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        GameSetting[] settings = Utils.getGameSettingsFromGame(KitPvP.getInstance().getCurrentGameOfPlayer(e.getEntity()));
        for (GameSetting gameSetting : settings) {
            gameSetting.onDeath(e);
        }
    }
}
