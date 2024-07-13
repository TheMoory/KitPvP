package de.themoory.kitpvp.listeners;


import de.themoory.kitpvp.utils.EventType;
import de.themoory.kitpvp.utils.Game;
import de.themoory.kitpvp.utils.GameSetting;

public class CustomListener{
    public static void dispatchEvent (Game g, EventType e){
        dispatchEvent(g, e, null);
    }
    public static void dispatchEvent(Game g, EventType e, Object eventData) {
        switch (e){
            case ON_SOUP:
                for(GameSetting gameSetting : g.getKit().getGameSettings()){
                    gameSetting.onSoup(eventData);
                }
                break;
            default:
                break;

        }


    }
}

