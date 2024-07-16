package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class PreventPlacingBlocks extends GameSetting {
    public PreventPlacingBlocks(Kit k) {
        super(k);
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent event){
        event.setCancelled(true);
    }
    @Override
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event){
        System.out.println("WFOWJFIKWFHJWIKF");
        event.setCancelled(true);
    }
}
