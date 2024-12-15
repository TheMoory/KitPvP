package de.themoory.kitpvp.kits;

import de.themoory.kitpvp.gamesettings.*;
import de.themoory.kitpvp.utils.Arena;
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
        ItemStack perl = new ItemStack(Material.ENDER_PEARL, 16);
        ItemStack water = new ItemStack(Material.WATER_BUCKET, 1);

        inventory.put(0, stick);
        inventory.put(1, perl);
        inventory.put(2, water);
        inventory.put(3, water);
    }

    @Override
    public void createSettings() {
        ArrayList<GameStats.STATSTYPE> stats = new ArrayList<>();
        stats.add(GameStats.STATSTYPE.TIME);
        stats.add(GameStats.STATSTYPE.DAMAGE_RECEIVED);
        stats.add(GameStats.STATSTYPE.DAMAGE_CAUSED);
        stats.add(GameStats.STATSTYPE.MISSED_HITS);
        stats.add(GameStats.STATSTYPE.HITS);
        stats.add(GameStats.STATSTYPE.HEARTS_LEFT);
        settings.add(new RemoveDropsAfterGame(this));
        settings.add(new RemoveItemsAfterGame(this));
        settings.add(new KitItemSetter(this));
        settings.add(new GameStatsHandler(this, stats));
        settings.add(new NoHunger(this));
        settings.add(new Souping(this));
        settings.add(new NoFallDamage(this));
                //new ConstantEffect(this, new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999999, 999999,false, false)),
        settings.add(new DeathUnderHeight(this, 0));
        settings.add(new PreventBreakingBlocks(this));
        settings.add(new RemovePlacedBlocks(this));
        };

    @Override
    public void createMapSettings() {
        possibleMaps.add(Arena.MAP.KNOCKBACK);
    }
}
