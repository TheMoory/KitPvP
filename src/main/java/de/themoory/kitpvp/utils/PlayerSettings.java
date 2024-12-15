package de.themoory.kitpvp.utils;

import de.themoory.kitpvp.kits.Soup;
import org.bukkit.entity.Player;

public class PlayerSettings {
    private final Player player;

    private Kit kit;
    private Arena.MAP map;
    private Arena.MODE mode;
    private int lives;

    public PlayerSettings(Player p){
        this.player = p;
        kit = new Soup();
    }

    public Player getPlayer(){
        return this.player;
    }

    public void setKit(Kit kit){
        this.kit = kit;
    }

    public Kit getKit(){
        return kit;
    }

    public void setMode(Arena.MODE mode){
        this.mode = mode;
    }

    public Arena.MODE getMode(){
        return mode;
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    public int getLives(){
        return lives;
    }

    public Arena.MAP getMap(){
        return map;
    }

    public void setMap(Arena.MAP map){
        this.map = map;
    }

}
