package com.paully104.reitzmmo.MonsterCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Created by Paul on 3/22/2016.
 */
public class MonsterLevelsHealth implements Listener {

    private final int blocksPerMobLevel = API.monsterConfig.getInt("BLOCKS-PER-MOB-LEVEL");
    private final int zombieBaseHP = API.monsterConfig.getInt("ZOMBIE_BASE_HP");
    private final int wolfBaseHP = API.monsterConfig.getInt("WOLF_BASE_HP");
    private final int villagerBaseHP = API.monsterConfig.getInt("VILLAGER_BASE_HP");
    private final int squidBaseHP = API.monsterConfig.getInt("SQUID_BASE_HP");
    private final int spiderBaseHP = API.monsterConfig.getInt("SPIDER_BASE_HP");
    private final int snowmanBaseHP = API.monsterConfig.getInt("SNOWMAN_BASE_HP");
    private final int slimeBaseHP = API.monsterConfig.getInt("SLIME_BASE_HP");
    private final int skeletonBaseHP = API.monsterConfig.getInt("SKELETON_BASE_HP");
    private final int silverfishBaseHP = API.monsterConfig.getInt("SILVERFISH_BASE_HP");
    private final int sheepBaseHP = API.monsterConfig.getInt("SHEEP_BASE_HP");
    private final int rabbitBaseHP = API.monsterConfig.getInt("RABBIT_BASE_HP");
    private final int pigzombieBaseHP = API.monsterConfig.getInt("PIGZOMBIE_BASE_HP");
    private final int pigBaseHP = API.monsterConfig.getInt("PIG_BASE_HP");
    private final int mushroomcowBaseHP = API.monsterConfig.getInt("MUSHROOMCOW_BASE_HP");
    private final int magmacubeBaseHP = API.monsterConfig.getInt("MAGMACUBE_BASE_HP");
    private final int guardianBaseHP = API.monsterConfig.getInt("GUARDIAN_BASE_HP");
    private final int giantBaseHP = API.monsterConfig.getInt("GIANT_BASE_HP");
    private final int ghastBaseHP = API.monsterConfig.getInt("GHAST_BASE_HP");
    private final int endermiteBaseHP = API.monsterConfig.getInt("ENDERMITE_BASE_HP");
    private final int endermanBaseHP = API.monsterConfig.getInt("ENDERMAN_BASE_HP");
    private final int enderdragonBaseHP = API.monsterConfig.getInt("ENDERDRAGON_BASE_HP");
    private final int creeperBaseHP = API.monsterConfig.getInt("CREEPER_BASE_HP");
    private final int cowBaseHP = API.monsterConfig.getInt("COW_BASE_HP");
    private final int chickenBaseHP = API.monsterConfig.getInt("CHICKEN_BASE_HP");
    private final int cavespiderBaseHP = API.monsterConfig.getInt("CAVESPIDER_BASE_HP");
    private final int blazeBaseHP = API.monsterConfig.getInt("BLAZE_BASE_HP");

    private int calculateDistanceFromSpawn(Location worldSpawn, Location monsterSpawn)
    {
        float deltaX = (float) (worldSpawn.getX() - monsterSpawn.getX());
        float deltaY= (float) (worldSpawn.getY() - monsterSpawn.getY());
        float deltaZ= (float) (worldSpawn.getZ() - monsterSpawn.getZ());
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
        String worldName = monsterSpawn.getWorld().getName();
        int distance2 = (Math.round(distance) / blocksPerMobLevel);
        //change this later to the config option
        int baseWorldDamage = API.worldConfig.getInt(worldName);

        distance2 = distance2 + baseWorldDamage;
        if(distance2 < 1)
        {
            distance2 = 1;
        }
        return distance2;

    }


    @EventHandler
    public void applyMonsterLevelOnSpawn(CreatureSpawnEvent e) {

        Location worldSpawn = e.getLocation().getWorld().getSpawnLocation();
        Location monsterSpawn = e.getLocation();
        if (monsterSpawn == null) return;//if there's a problem
        int distance = calculateDistanceFromSpawn(worldSpawn, monsterSpawn);


        e.getEntity().setCustomName("LV "+distance);
       e.getEntity().setCustomNameVisible(true);

        //configure health per mob

        if(e.getEntity().getType() == EntityType.ZOMBIE) {
            Zombie zombie = (Zombie) e.getEntity();
            if (zombie.isBaby())
            {
                e.getEntity().setMaxHealth(distance * zombieBaseHP);
                e.getEntity().setHealth(distance * zombieBaseHP);
                //Update onm 4/26/2017 To slow the hell down baby zombies
                e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 0.8);
                //Updated on 4/26/2017 to increase follow_range to 2.0 from 1.25
                e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2.00);
                //updated on 4/26/2017 to increase the chance of getting tons of zombies :)
                e.getEntity().getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(e.getEntity().getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).getValue() + 0.25);
            }
            else
            {
                e.getEntity().setMaxHealth(distance * zombieBaseHP);
                e.getEntity().setHealth(distance * zombieBaseHP);
                //superfastZombie
                e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 1.25);
                //Updated on 4/26/2017 to increase follow_range to 2.0 from 1.25
                e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2.00);
                //updated on 4/26/2017 to increase the chance of getting tons of zombies :)
                e.getEntity().getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(e.getEntity().getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).getValue() + 0.25);
            }
        }

        if(e.getEntity().getType() == EntityType.WOLF)
        {
            e.getEntity().setMaxHealth(distance * wolfBaseHP);
            e.getEntity().setHealth(distance * wolfBaseHP);
        }

        if(e.getEntity().getType() == EntityType.VILLAGER)
        {
            e.getEntity().setMaxHealth(distance * villagerBaseHP);
            e.getEntity().setHealth(distance * villagerBaseHP);
        }

        if(e.getEntity().getType() == EntityType.SQUID)
        {
            e.getEntity().setMaxHealth(distance * squidBaseHP);
            e.getEntity().setHealth(distance * squidBaseHP);
        }

        if(e.getEntity().getType() == EntityType.SPIDER)
        {
            e.getEntity().setMaxHealth(distance * spiderBaseHP);
            e.getEntity().setHealth(distance * spiderBaseHP);
        }
        if(e.getEntity().getType() == EntityType.SNOWMAN)
        {
            e.getEntity().setMaxHealth(distance * snowmanBaseHP);
            e.getEntity().setHealth(distance * snowmanBaseHP);
        }
        if(e.getEntity().getType() == EntityType.SLIME)
        {
            e.getEntity().setMaxHealth(distance * slimeBaseHP);
            e.getEntity().setHealth(distance * slimeBaseHP);
        }
        if(e.getEntity().getType() == EntityType.SKELETON)
        {
            e.getEntity().setMaxHealth(distance * skeletonBaseHP);
            e.getEntity().setHealth(distance * skeletonBaseHP);
            //slower Skellies because they suck
            e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue()*.9);
        }
        if(e.getEntity().getType() == EntityType.SILVERFISH)
        {
            e.getEntity().setMaxHealth(distance * silverfishBaseHP);
            e.getEntity().setHealth(distance * silverfishBaseHP);
        }
        if(e.getEntity().getType() == EntityType.SHEEP)
        {
            e.getEntity().setMaxHealth(distance * sheepBaseHP);
            e.getEntity().setHealth(distance * sheepBaseHP);
        }
        if(e.getEntity().getType() == EntityType.RABBIT)
        {
            e.getEntity().setMaxHealth(distance * rabbitBaseHP);
            e.getEntity().setHealth(distance * rabbitBaseHP);
        }
        if(e.getEntity().getType() == EntityType.PIG_ZOMBIE)
        {
            e.getEntity().setMaxHealth(distance * pigzombieBaseHP);
            e.getEntity().setHealth(distance * pigzombieBaseHP);
            //superfast
            e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue()*1.25);
        }
        if(e.getEntity().getType() == EntityType.PIG)
        {
            e.getEntity().setMaxHealth(distance * pigBaseHP);
            e.getEntity().setHealth(distance * pigBaseHP);
        }
        if(e.getEntity().getType() == EntityType.MUSHROOM_COW)
        {
            e.getEntity().setMaxHealth(distance * mushroomcowBaseHP);
            e.getEntity().setHealth(distance * mushroomcowBaseHP);
        }
        if(e.getEntity().getType() == EntityType.MAGMA_CUBE)
        {
            e.getEntity().setMaxHealth(distance * magmacubeBaseHP);
            e.getEntity().setHealth(distance * magmacubeBaseHP);
        }
        if(e.getEntity().getType() == EntityType.GUARDIAN)
        {
            e.getEntity().setMaxHealth(distance * guardianBaseHP);
            e.getEntity().setHealth(distance * guardianBaseHP);
        }
        if(e.getEntity().getType() == EntityType.GIANT)
        {
            e.getEntity().setMaxHealth(distance * giantBaseHP);
            e.getEntity().setHealth(distance * giantBaseHP);
        }
        if(e.getEntity().getType() == EntityType.GHAST)
        {
            e.getEntity().setMaxHealth(distance * ghastBaseHP);
            e.getEntity().setHealth(distance * ghastBaseHP);
            //Slow them down
            e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue()*.9);
        }
        if(e.getEntity().getType() == EntityType.ENDERMITE)
        {
            e.getEntity().setMaxHealth(distance * endermiteBaseHP);
            e.getEntity().setHealth(distance * endermiteBaseHP);
        }
        if(e.getEntity().getType() == EntityType.ENDERMAN)
        {
            e.getEntity().setMaxHealth(distance * endermanBaseHP);
            e.getEntity().setHealth(distance * endermanBaseHP);
        }
        if(e.getEntity().getType() == EntityType.ENDER_DRAGON)
        {
            e.getEntity().setMaxHealth(distance * enderdragonBaseHP);
            e.getEntity().setHealth(distance * enderdragonBaseHP);
        }
        if(e.getEntity().getType() == EntityType.CREEPER)
        {
            e.getEntity().setMaxHealth(distance * creeperBaseHP);
            e.getEntity().setHealth(distance * creeperBaseHP);
            //superFastCreepers?
            e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue()*1.50);
            e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue()*2.00);

        }
        if(e.getEntity().getType() == EntityType.COW)
        {
            e.getEntity().setMaxHealth(distance * cowBaseHP);
            e.getEntity().setHealth(distance * cowBaseHP);
        }
        if(e.getEntity().getType() == EntityType.CHICKEN)
        {
            e.getEntity().setMaxHealth(distance * chickenBaseHP);
            e.getEntity().setHealth(distance * chickenBaseHP);
        }
        if(e.getEntity().getType() == EntityType.CAVE_SPIDER)
        {
            e.getEntity().setMaxHealth(distance * cavespiderBaseHP);
            e.getEntity().setHealth(distance * cavespiderBaseHP);
        }
        if(e.getEntity().getType() == EntityType.BLAZE)
        {
            e.getEntity().setMaxHealth(distance * blazeBaseHP);
            e.getEntity().setHealth(distance * blazeBaseHP);
        }




    }


}
