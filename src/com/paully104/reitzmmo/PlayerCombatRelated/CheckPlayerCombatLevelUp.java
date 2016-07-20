package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.entity.Player;

/**
 * Created by Paul on 3/22/2016.
 */
public class CheckPlayerCombatLevelUp {

    public static void CheckLevelUp(Player p)
    {
        int combatexp = API.Players.get(p.getName()).getData().getInt("Combat-EXP");
        int level = API.Players.get(p.getName()).getData().getInt("Level");
        int combatexpneeded = level * (API.playerConfig.getInt("CombatEXP") * API.playerConfig.getInt("CombatEXP_MULTIPLIER"));
        //level up occurs
        if(combatexp >= combatexpneeded)
        {
            level = level+1;
            combatexp = combatexp - combatexpneeded;
            API.Players.get(p.getName()).getData().set("Combat-EXP", combatexp);
            API.Players.get(p.getName()).getData().set("Level", level);
            p.sendMessage("You leveled up to: " + level);


        }




    }
}
