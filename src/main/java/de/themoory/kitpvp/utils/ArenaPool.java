package de.themoory.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class ArenaPool {


    public static ArrayList<Arena> arenas;

    public ArenaPool(){
        arenas = new ArrayList<>();
        HashMap<String, Location> spawns = new HashMap<>();
        spawns.put("0_0", new Location(Bukkit.getWorld("world"), 0,100,0,0,0));
        spawns.put("1_0", new Location(Bukkit.getWorld("world"), 0,100,0,0,0));
        Arena arena1 = new Arena(Arena.MAP.DEFAULT, 2, 2, 2, spawns);
        arenas.add(arena1);
    }



    public static Arena getFreeArea(Arena.MAP map){
        //TODO Get logic for arena management, return null if no free map is available
        for(Arena arena : arenas){
            if(arena.getMapType().equals(map)){
                if(arena.getState().equals(Arena.STATE.WAITING)){
                    return arena;
                }
            }
        }
        return null;
    }



















}
