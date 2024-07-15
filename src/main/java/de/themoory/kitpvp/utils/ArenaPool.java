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
        spawns.put("0_0", new Location(Bukkit.getWorld("world"), 40,101,0,90,0));
        spawns.put("1_0", new Location(Bukkit.getWorld("world"), -40,101,0,-90,0));
        Arena arena = new Arena(Arena.MAP.DEFAULT, 2, 2, 2, spawns);
        arenas.add(arena);

        spawns.put("0_0", new Location(Bukkit.getWorld("world"), 260,147,85,0,0));
        spawns.put("1_0", new Location(Bukkit.getWorld("world"), 260,147,99,180,0));
        arena = new Arena(Arena.MAP.KNOCKBACK, 2, 2, 2, spawns);
        arenas.add(arena);

    }



    public static Arena getFreeArea(Arena.MAP map){
        //TODO Get logic for arena management, return null if no free map is available
        for(Arena arena : arenas){
            if(arena.getMapType().equals(map)){
                if(arena.getState().equals(Arena.STATE.WAITING)){
                    arena.setState(Arena.STATE.RUNNING);
                    return arena;
                }
            }
        }
        return null;
    }



















}
