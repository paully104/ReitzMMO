package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Custom_Recipes.Custom_Bows;
import com.paully104.reitzmmo.Enum.Weapon_Damage;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Paul on 3/22/2016.
 */
public class PlayerAttackingMonster implements Listener {

    private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerAttackingMonster");

    @EventHandler
    public void playerAttackingMonster(EntityDamageByEntityEvent e)
    {

        Boolean isProjectile = false;
        Entity defender = e.getEntity();//monster
        Entity attacker = e.getDamager();//player
        int player_attack = 0;
        int monster_defense = 0;
        int damage_done = 0;
        String monster_level_from_name ="";

        if(attacker instanceof Player) {
            //lets ignore if the damage source is custom
            PlayerData pd = API.Players.get(attacker.getName());
            player_attack = pd.getData().getInt("Attack");
            try {
                monster_level_from_name = defender.getCustomName().replaceAll("\\D+", "");
            }
            catch(NullPointerException error)
            {
                monster_level_from_name = "1";
                defender.setCustomName("Lv 1");
                defender.setCustomNameVisible(true);
            }
            monster_defense = Integer.parseInt(monster_level_from_name);
            damage_done = player_attack - monster_defense;
                if (damage_done < 1) {
                damage_done = 1;
            }
            HumanEntity human = (HumanEntity) attacker;
            try {
                //if in list
                Integer weaponDamage = (Weapon_Damage.Weapon_Damages.valueOf(human.getInventory().getItemInMainHand().getType().toString().toUpperCase()).getValue());
                if(weaponDamage == null)
                {
                    System.out.println(human.getInventory().getItemInMainHand().getType().toString().toUpperCase());
                    weaponDamage = 1;
                }
                e.setDamage(damage_done + weaponDamage);
                damage_done = damage_done + weaponDamage;
            }
            catch (IllegalArgumentException error)
            {
                e.setDamage(damage_done);//if not in list
            }




        }
        if(e.getDamager() instanceof  Arrow)
        {
            isProjectile = true;
            System.out.println("Arrow attack event");
            Arrow arrow = (Arrow)e.getDamager();
            Entity shooter = (Entity)arrow.getShooter();
            if(shooter instanceof Player)
            {
                PlayerData pd = API.Players.get(shooter.getName());
                player_attack = pd.getData().getInt("Attack");
                monster_level_from_name = defender.getCustomName().replaceAll("\\D+", "");
                monster_defense = Integer.parseInt(monster_level_from_name);
                //updated on 5/6/2017 to add new custom bow recipes
                damage_done = player_attack - monster_defense + Custom_Bows.getBowDamage(((Player) shooter).getInventory().getItemInMainHand());
                System.out.println("Damage Done: " + player_attack + " " + monster_defense + " " + Custom_Bows.getBowDamage(((Player) shooter).getInventory().getItemInMainHand()));

                //custom item logic

                //check if cheap arrow
                //make sure 1 damage is done minimum
                if (damage_done < 1) {
                    damage_done = 1;
                }
                e.setDamage(damage_done);
                if(debugEnabled && isProjectile)
                {
                    System.out.println("[PAM]: " + shooter.getName() + " " +  player_attack +
                            " -> " + defender.getCustomName() + " " + monster_defense + "\n" + " Damage_Done -> " + damage_done +
                            "\n" + " Mob HP: " + ((LivingEntity)defender).getHealth());

                }
            }

        }

        if(debugEnabled && !isProjectile && !(e.getDamager() instanceof  TNTPrimed))
        {
            System.out.println("[PAM]: " + attacker.getName() + " " +  player_attack +
                    " -> " + defender.getCustomName() + " " + monster_defense + "\n" + " Damage_Done -> " + damage_done +
            "\n" + " Mob HP: " + ((LivingEntity)defender).getHealth());

        }
        System.out.println(e.getDamage() + "Damage DONE");

    }
}
