package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import de.themoory.kitpvp.utils.Team;
import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;

public class ConstantEffect extends GameSetting {
    ArrayList<PotionEffect> effects;

    public ConstantEffect(Kit k, PotionEffect pe){
        super(k);
        effects =new ArrayList<PotionEffect>();
        effects.add(pe);
    }
    public ConstantEffect(Kit k, ArrayList<PotionEffect> type){
        super(k);
        this.effects = type;
    }

    @Override
    public void onGameStart() {
        for(Team t : getGame().getTeams()){
            for(Player p : t.getPlayers()){
                for(PotionEffect e : effects){
                    p.addPotionEffect(e);
                }
            }
        }
    }
}
