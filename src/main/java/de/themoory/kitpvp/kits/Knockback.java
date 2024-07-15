package de.themoory.kitpvp.kits;

import de.themoory.kitpvp.gamesettings.*;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.GameStats;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Knockback extends Kit {

    @Override
    public void createInventory(){
        ItemStack stick = new ItemStack(Material.STICK, 1);
        ItemMeta stickMeta = stick.getItemMeta();
        stickMeta.addEnchant(Enchantment.KNOCKBACK,3, true);
        stick.setItemMeta(stickMeta);

        inventory.put(0, stick);
    }

    @Override
    public void createSettings() {
        ArrayList<GameStats.STATSTYPE> stats = new ArrayList<>();
        stats.add(GameStats.STATSTYPE.TIME);
        stats.add(GameStats.STATSTYPE.DAMAGE_RECEIVED);
        stats.add(GameStats.STATSTYPE.DAMAGAE_CAUSED);
        stats.add(GameStats.STATSTYPE.MISSED_HITS);
        stats.add(GameStats.STATSTYPE.HITS);
        settings = new GameSetting[]{
                new RemoveDropsAfterGame(this),
                new RemoveItemsAfterGame(this),
                new KitItemSetter(this),
                new GameStatsHandler(this, stats),
                new NoHunger(this),
                new Souping(this),
                new OneLive(this),
                new NoFallDamage(this),
        };
    }
}
