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
        chestMeta.setDisplayName(ChatColor.GOLD + "Map select");
        chest.setItemMeta(chestMeta);
        inventory.put(0, sword);
        inventory.put(1, chest);
    }

    @Override
    public void createSettings() {
        settings = new GameSetting[]{new NoHunger(this), new Invincible(this), new InviteHandler(this), new TeleportToSpawn(this, 80), /*new PreventBreakingBlocks(this)*/};
    }
}
