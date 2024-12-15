package de.themoory.kitpvp.utils;

import de.themoory.kitpvp.kits.Knockback;
import de.themoory.kitpvp.kits.Lobby;
import de.themoory.kitpvp.kits.Soup;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public abstract class Kit {

    protected HashMap<Integer, ItemStack> inventory;

    protected HashMap<Integer, ItemStack> armor;
    protected ArrayList<GameSetting> settings;

    protected ArrayList<Arena.MAP> possibleMaps;
    protected Game game;


    public Kit(){
        inventory = new HashMap<>();
        armor = new HashMap<>();
        possibleMaps = new ArrayList<>();
        settings = new ArrayList<>();

        createInventory();
        createSettings();
        createMapSettings();
    }


    public HashMap<Integer, ItemStack> getInventory(){
        return inventory;
    }

    public HashMap<Integer, ItemStack> getArmor() {
        return armor;
    }

    public ArrayList<GameSetting> getGameSettings() {
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

    public void addGameSetting(GameSetting gameSetting) {
        this.settings.add(gameSetting);
    }

    public void createMapSettings(){
    }


    public void createSettings(){
    }



    public enum KITS{


        KNOCKBACK(Knockback.class, new ItemStack(Material.STICK, 1) {{
            ItemMeta meta = getItemMeta();
            meta.addEnchant(Enchantment.KNOCKBACK, 3, true);
            setItemMeta(meta);
        }}),
        SOUP(Soup.class, new ItemStack(Material.MUSHROOM_SOUP));

        private final Class<? extends Kit> clazz;

        private final ItemStack itemStack;

        KITS(Class<? extends Kit> clazz, ItemStack itemStack){
            this.clazz = clazz;
            this.itemStack = itemStack;
        }

        public Kit getKit(){
            try{
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                return new Lobby();
            }
        }

        public ItemStack getItemStack() {
            return itemStack;
        }
    }

}
