package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * Created by Paul on 3/22/2016.
 */
public class PlayerDefeatsMonster implements Listener {

    @EventHandler
    public void MonsterDeathCausedBByPlayer(EntityDeathEvent e)
    {

        if(e.getEntity().getKiller() instanceof Player)
        {
            //Get Entities
            Entity dead = e.getEntity();
            Entity player = e.getEntity().getKiller();

            //Get Experience
            Integer currentexp = API.Players.get(player.getName()).getData().getInt("Combat-EXP");
            String monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
            int monster_level = Integer.parseInt(monster_level_from_name);
            int new_exp = currentexp + (monster_level*2);
            API.Players.get(player.getName()).getData().set("Combat-EXP", new_exp);
            CheckPlayerCombatLevelUp.CheckLevelUp((Player)player);


        }

    }

}
