package de.themoory.kitpvp.utils;

import de.themoory.kitpvp.gamesettings.NoHunger;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Kit {

    protected HashMap<Integer, ItemStack> inventory;
    protected GameSetting[] settings;

    public Kit(){
        inventory = new HashMap<>();

        createInventory();
        createSettings();
    }
    public HashMap<Integer, ItemStack> getInventory(){
        return inventory;
    }

    public GameSetting[] getGameSettings() {
        return settings;
    }

    public void createInventory(){
        inventory = new HashMap<Integer, ItemStack>();
    }

    public void createSettings(){
        settings = new GameSetting[]{};
    };

}
