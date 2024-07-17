package de.themoory.kitpvp.utils;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public abstract class Kit {

    protected HashMap<Integer, ItemStack> inventory;

    protected HashMap<Integer, ItemStack> armor;
    protected GameSetting[] settings;

    protected Game game;

    public Kit(){
        inventory = new HashMap<>();
        armor = new HashMap<>();

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
        inventory = new HashMap<Integer, ItemStack>();
    }

    public void createSettings(){
        settings = new GameSetting[]{};
    };

}
