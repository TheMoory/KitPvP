package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.event.block.BlockBreakEvent;

public class PreventBreakingBlocks extends GameSetting {
    public PreventBreakingBlocks(Kit k) {
        super(k);
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event){
        event.setCancelled(true);
    }
}
