package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.PlayerData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Paul on 3/22/2016.
 */
public class PlayerAttackingMonster implements Listener {

    @EventHandler
    public void playerAttackingMonster(EntityDamageByEntityEvent e)
    {
        Entity defender = e.getEntity();//monster
        Entity attacker = e.getDamager();//player
        int player_attack;
        int monster_defense;
        int damage_done;
        String monster_level_from_name;

        if(attacker.getType() == EntityType.PLAYER) {
            PlayerData pd = API.Players.get(attacker.getName());
            player_attack = pd.getData().getInt("Attack");
            monster_level_from_name = defender.getCustomName().replaceAll("\\D+", "");
            monster_defense = Integer.parseInt(monster_level_from_name);
            damage_done = monster_defense - player_attack;
            if (damage_done < 1) {
                damage_done = 1;
            }

        }
    }
}
