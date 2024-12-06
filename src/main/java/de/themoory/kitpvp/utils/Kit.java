package de.themoory.kitpvp.utils;

import de.themoory.kitpvp.kits.Knockback;
import de.themoory.kitpvp.kits.Soup;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Kit {

    protected HashMap<Integer, ItemStack> inventory;

    protected HashMap<Integer, ItemStack> armor;
    protected GameSetting[] settings;

    protected Game game;

    public Kit(){
        inventory = new HashMap<>();
        armor = new HashMap<>();
        possibleMaps = new ArrayList<>();
        settings = new GameSetting[]{};

        createInventory();
        createSettings();
    }
    public HashMap<Integer, ItemStack> getInventory(){
        return inventory;
    }

    public HashMap<Integer, ItemStack> getArmor() {
        return armor;
    }

    public GameSetting[] getGameSettings() {
        return settings;
    }

    public void setGame(Game g){
        this.game = g;
    }

    public Game getGame(){
        return this.game;
    }
    public void createInventory(){

    }

    public void createSettings(){
        settings = new GameSetting[]{};
    };

}
