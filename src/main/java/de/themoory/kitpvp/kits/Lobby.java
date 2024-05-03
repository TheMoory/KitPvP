package de.themoory.kitpvp.kits;

import de.themoory.kitpvp.gamesettings.Invincible;
import de.themoory.kitpvp.gamesettings.InviteHandler;
import de.themoory.kitpvp.gamesettings.NoHunger;
import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Lobby extends Kit {

    @Override
    public void createInventory() {
        super.createInventory();
    }

    @Override
    public void createSettings() {
        settings = new GameSetting[]{new NoHunger(), new Invincible(), new InviteHandler()};
    }
}
