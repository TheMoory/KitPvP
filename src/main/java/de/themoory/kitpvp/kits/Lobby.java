package de.themoory.kitpvp.kits;

import de.themoory.kitpvp.gamesettings.*;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Lobby extends Kit {

    @Override
    public void createInventory() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);

        ItemStack chest = new ItemStack(Material.CHEST, 1);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.setDisplayName(ChatColor.GOLD + "Kit select");
        chest.setItemMeta(chestMeta);

        ItemStack kit = new ItemStack(Material.GRASS, 1);
        ItemMeta kiItemMeta = kit.getItemMeta();
        kiItemMeta.setDisplayName(ChatColor.GOLD + "Map select");
        kit.setItemMeta(kiItemMeta);

        ItemStack comparator = new ItemStack(Material.REDSTONE_COMPARATOR, 1);
        ItemMeta comparatorMeta = comparator.getItemMeta();
        comparatorMeta.setDisplayName(ChatColor.DARK_RED + "Settings");
        comparator.setItemMeta(comparatorMeta);


        inventory.put(0, sword);
        inventory.put(1, chest);
        inventory.put(2, kit);
        inventory.put(3, comparator);

    }

    @Override
    public void createSettings() {
        settings.add(new NoHunger(this));
        settings.add(new Invincible(this));
        settings.add(new InviteHandler(this));
        settings.add(new TeleportToSpawn(this, 80));
    }
}
