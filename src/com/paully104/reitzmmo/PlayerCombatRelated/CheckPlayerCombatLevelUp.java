package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Paul on 3/22/2016.
 */
class CheckPlayerCombatLevelUp {

    private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerLevelUp");

    public  void CheckLevelUp(Player p)
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

            /* message sent to players for leveling u */
            Bukkit.broadcastMessage(ChatColor.GREEN + "[LEVEL UP] " + ChatColor.WHITE +  p.getName().toUpperCase().charAt(0) + " reached level: " + level);

            API.Players.get(p.getName()).getData().set("Attack", (level * API.playerConfig.getInt("AttackScale")));
            API.Players.get(p.getName()).getData().set("Health", (18 + (level * API.playerConfig.getInt("HealthScale"))));
            API.Players.get(p.getName()).getData().set("CombatEXP", combatexp);
            p.setMaxHealth((18 + (level * API.playerConfig.getInt("HealthScale"))));


        }

        if(debugEnabled)
        {
            System.out.println(p.getName() + " combatexp: " + combatexp + " " + "combatexpneeded: " + combatexpneeded);


        }




    }
}
