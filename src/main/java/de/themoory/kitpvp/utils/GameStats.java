package de.themoory.kitpvp.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;

public class GameStats {
    private final Player player;

    private HashMap<STATSTYPE, Object> stats;

    public GameStats(Player player){
        this.player = player;
        this.stats = new HashMap<>();
    }

    public void setStat(STATSTYPE statstype, Object value){
        this.stats.put(statstype, value);
    }

    public Object getStat(STATSTYPE statstype){
        return this.stats.get(statstype);
    }

    public Player getPlayer() {
        return player;
    }

    public enum STATSTYPE {
        KILLS(0, "Kills"),
        DEATHS(0, "Deaths"),
        HITS(0, "Hits"),
        MISSED_HITS(0, "Missed Hits"),
        DAMAGE_RECEIVED(0d, "Damage Received"),
        DAMAGAE_CAUSED(0d, "Damage Caused"),
        TIME(0, "Time"),



        //custom kit settings

        EATEN_SOUPS(0, "Eaten Soups"),
        HEALED(0d, "Healed"),
        ;

        private final Object value;

        private final String name;

        STATSTYPE(Object value, String name) {
            this.value = value;
            this.name = name;
        }

        public Object getDefaultValue(){
            return value;
        }

        public String getName(){
            return name;
        }
    }



}
