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
        KILLS(0),
        DEATHS(0),
        HITS(0),
        MISSED_HITS(0),
        DAMAGE_RECEIVED(0d),
        DAMAGAE_CAUSED(0d),
        TIME(0),



        //custom kit settings

        EATEN_SOUPS(0),
        HEALED(0d),
        ;

        private final Object value;

        STATSTYPE(Object value) {
            this.value = value;
        }

        public Object getDefaultValue(){
            return value;
        }

    }



}
