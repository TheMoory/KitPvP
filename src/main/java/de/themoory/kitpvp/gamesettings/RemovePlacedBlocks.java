package de.themoory.kitpvp.gamesettings;

import de.themoory.kitpvp.utils.GameSetting;
import de.themoory.kitpvp.utils.Kit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import java.util.ArrayList;

public class RemovePlacedBlocks extends GameSetting {
    public RemovePlacedBlocks(Kit k) {
        super(k);
        placedBlocks = new ArrayList<>();
    }

    private ArrayList<Block> placedBlocks;

    @Override
    public void onBlockPlace(BlockPlaceEvent e) {
        System.out.println("Placed Water");
        placedBlocks.add(e.getBlockPlaced());
    }

    @Override
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event){
        placedBlocks.add(event.getBlockClicked().getRelative(event.getBlockFace()));
    }


    @Override
    public void onGameEnd(){
        for(Block block : placedBlocks){
            block.setType(Material.AIR);
        }
    }
}
