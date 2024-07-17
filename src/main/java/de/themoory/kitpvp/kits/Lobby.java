package de.themoory.kitpvp.kits;

import de.themoory.kitpvp.gamesettings.Invincible;
import de.themoory.kitpvp.gamesettings.InviteHandler;
import de.themoory.kitpvp.gamesettings.NoHunger;
import de.themoory.kitpvp.gamesettings.TeleportToSpawn;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Lobby extends Kit {

    @Override
    public void createInventory() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        inventory.put(0, sword);
    }

    @Override
    public void createSettings() {
        settings = new GameSetting[]{new NoHunger(this), new Invincible(this), new InviteHandler(this), new TeleportToSpawn(this, 80)};
    }
}
