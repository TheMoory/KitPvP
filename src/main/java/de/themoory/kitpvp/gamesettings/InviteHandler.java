package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.kits.Knockback;
import de.themoory.kitpvp.kits.Soup;
import de.themoory.kitpvp.utils.Arena;
import de.themoory.kitpvp.utils.Game;
import de.themoory.kitpvp.utils.GameSetting;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class InviteHandler extends GameSetting {
    @Override
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player &&
                ((Player) e.getDamager()).getItemInHand().getType() == Material.DIAMOND_SWORD){
            Game gameOfDamaged = KitPvP.getInstance().getCurrentGameOfPlayer((Player) e.getEntity());
            Game gameOfDamager = KitPvP.getInstance().getCurrentGameOfPlayer((Player) e.getDamager());

            // check if damager is in invite list of game of damaged: if yes join game
            if(gameOfDamager == null && gameOfDamaged != null && gameOfDamaged.isInvited((Player) e.getDamager())){
                gameOfDamaged.addPlayer((Player) e.getDamager());
                e.setCancelled(true);
                return;
            }

            // check if damaged is already in a game, don't invite
            if(gameOfDamaged != null) {
            } else {
                e.setCancelled(true);
                if(gameOfDamager == null){
                    gameOfDamager = new Game(KitPvP.getInstance(), (Player)e.getDamager(), Arena.MODE.one_vs_one);
                    gameOfDamager.setMap(Arena.MAP.KNOCKBACK);
                    gameOfDamager.setKit(new Knockback());
                    //KitPvP.getInstance().addToStagedGames(gameOfDamager); Wird schon beim ersteller des Game Objektes hinzugefügt --- Oder besser hier machen?

                }
                // add player to invite list of game
                gameOfDamager.addInvite((Player)e.getEntity());
            }
        }
    }
}