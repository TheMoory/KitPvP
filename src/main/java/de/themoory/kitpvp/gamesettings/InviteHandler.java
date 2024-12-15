package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.KitPvP;
import de.themoory.kitpvp.utils.Arena;
import de.themoory.kitpvp.utils.Game;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;

public class InviteHandler extends GameSetting {
    public InviteHandler(Kit k) {
        super(k);
    }

    @Override
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @Override
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        e.setCancelled(true);
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            if(((Player) e.getDamager()).getItemInHand().getType() == Material.DIAMOND_SWORD){
                Player damager = (Player) e.getDamager();
                Player damaged = (Player) e.getEntity();

                Game gameOfDamaged = KitPvP.getInstance().getCurrentGameOfPlayer(damaged);
                Game gameOfDamager = KitPvP.getInstance().getCurrentGameOfPlayer(damager);

                // check if damager is in invite list of game of damaged: if yes join game
                if(gameOfDamager == null && gameOfDamaged != null && gameOfDamaged.isInvited((Player) e.getDamager())){
                    damaged.sendMessage(ChatColor.LIGHT_PURPLE + damager.getName() + " accepted your challenge!");
                    damager.sendMessage(ChatColor.LIGHT_PURPLE + "You accepted the challenge from " +  damaged.getName() + "!");
                    gameOfDamaged.addPlayer((Player) e.getDamager());
                    e.setCancelled(true);
                    return;
                }

                // check if damaged is already in a game, don't invite
                if(gameOfDamaged == null) {
                    e.setCancelled(true);
                    if(gameOfDamager == null){
                        gameOfDamager = new Game(KitPvP.getInstance(), (Player)e.getDamager(), Arena.MODE.one_vs_one);
                        //KitPvP.getInstance().addToStagedGames(gameOfDamager); Wird schon beim ersteller des Game Objektes hinzugefügt --- Oder besser hier machen?

                    }
                    // add player to invite list of game
                    damaged.sendMessage(ChatColor.LIGHT_PURPLE + "You've been challenged by " + damager.getName() + "!");
                    damager.sendMessage(ChatColor.LIGHT_PURPLE + "Challenge send to " +  damaged.getName() + "!");
                    gameOfDamager.addInvite((Player)e.getEntity());
                }
            }
        }
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        /***dev temp***/
        if(player.getGameMode().equals(GameMode.CREATIVE)) return;

        if(event.getItem() == null){
            return;
        }
        if(event.getItem().getItemMeta() == null){
            return;
        }
        String displayName = event.getItem().getItemMeta().getDisplayName();
        if(displayName == null){
            return;
        }
        if(displayName.equals(ChatColor.GOLD + "Map select")){
            Inventory inv = Bukkit.createInventory(null, 6*9, "Wähle eine Map aus!");
            for(Arena.MAP maps : Arena.MAP.values()){
                ItemStack item = new ItemStack(Material.GRASS);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(maps.toString());
                item.setItemMeta(meta);
                inv.addItem(item);
            }
            player.openInventory(inv);
            event.setCancelled(true);
        }else if(displayName.equals(ChatColor.GOLD + "Kit select")) {
            Inventory inv = Bukkit.createInventory(null, 6*9, "Wähle ein Kit aus!");
            for(Kit.KITS kits : Kit.KITS.values()) {
                ItemStack item = new ItemStack(kits.getItemStack());
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(kits.toString());
                item.setItemMeta(meta);
                inv.addItem(item);
            }
            player.openInventory(inv);
            event.setCancelled(true);
        }else if(displayName.equals(ChatColor.DARK_RED + "Settings")) {
            Inventory inv = Bukkit.createInventory(null, 6*9, "Wähle ein Setting aus!");
            for(Arena.MODE mode : Arena.MODE.values()) {
                ItemStack item = new ItemStack(Material.SKULL_ITEM);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(mode.toString());
                item.setItemMeta(meta);
                inv.addItem(item);
            }
            int slot = 9;
            for(XLive.LIVES lives : XLive.LIVES.values()) {
                ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(lives.toString());
                item.setItemMeta(meta);
                inv.setItem(slot, item);
                slot++;
            }

            player.openInventory(inv);
            event.setCancelled(true);
        }
    }
    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        Player player = (Player) event.getWhoClicked();
        String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
        if(event.getInventory().getName().equals("Wähle eine Map aus!")){
            for(Arena.MAP maps : Arena.MAP.values()){
                if(maps.toString().equals(displayName)){
                    player.sendMessage("Du hast "+maps+" als Map ausgewählt!");
                    KitPvP.getInstance().getCurrentPlayerSetting(player).setMap(maps);
                    event.setCancelled(true);
                    player.closeInventory();
                    return;
                }
            }
        }
        if(event.getInventory().getName().equals("Wähle ein Kit aus!")) {
            for (Kit.KITS kits : Kit.KITS.values()) {
                if (kits.toString().equals(displayName)) {
                    player.sendMessage("Du hast " + kits + " als Kit ausgewählt!");
                    KitPvP.getInstance().getCurrentPlayerSetting(player).setKit(kits.getKit());
                    event.setCancelled(true);
                    player.closeInventory();
                    return;
                }
            }
        }

        if(event.getInventory().getName().equals("Wähle ein Setting aus!")) {
            for (Arena.MODE mode : Arena.MODE.values()) {
                if (mode.toString().equals(displayName)) {
                    player.sendMessage("Du hast " + mode + " ausgewählt!");
                    KitPvP.getInstance().getCurrentPlayerSetting(player).setMode(mode);
                    event.setCancelled(true);
                    player.closeInventory();
                    return;
                }
            }
            for (XLive.LIVES mode : XLive.LIVES.values()) {
                if (mode.toString().equals(displayName)) {
                    player.sendMessage("Du hast " + mode + " ausgewählt!");
                    KitPvP.getInstance().getCurrentPlayerSetting(player).setLives(mode.getLives());
                    event.setCancelled(true);
                    player.closeInventory();
                    return;
                }
            }
        }
    }
}