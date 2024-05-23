package de.themoory.kitpvp.kits;

import de.themoory.kitpvp.gamesettings.NoHunger;
import de.themoory.kitpvp.gamesettings.OneLive;
import de.themoory.kitpvp.gamesettings.Souping;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Soup extends Kit {

    @Override
    public void createInventory(){
        ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.addEnchant(Enchantment.DAMAGE_ALL,2, true);
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        ItemStack chestPlate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta chestPlateMeta = chestPlate.getItemMeta();
        chestPlateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        chestPlate.setItemMeta(chestPlateMeta);
        ItemStack leggins = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta legginsMeta = leggins.getItemMeta();
        legginsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        chestPlate.setItemMeta(legginsMeta);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta bootsMeta = boots.getItemMeta();
        bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        chestPlate.setItemMeta(bootsMeta);
        armor.put(0, helmet);
        armor.put(1, chestPlate);
        armor.put(2, leggins);
        armor.put(3, boots);
        inventory.put(0, sword);
        for (int i = 1; i < 32; i++) {
            inventory.put(i, new ItemStack(Material.MUSHROOM_SOUP, 1));
        }
        inventory.put(33, new ItemStack(Material.BOWL,64));
        inventory.put(34, new ItemStack(Material.BROWN_MUSHROOM,64));
        inventory.put(35, new ItemStack(Material.RED_MUSHROOM, 64));
    }

    @Override
    public void createSettings() {
        settings = new GameSetting[]{new NoHunger(), new Souping(), new OneLive()};
    }
}
