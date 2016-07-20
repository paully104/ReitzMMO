package com.paully104.reitzmmo.MonsterCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Created by Paul on 3/22/2016.
 */
public class MonsterLevelsHealth implements Listener {

    private int blocksPerMobLevel = API.monsterConfig.getInt("BLOCKS-PER-MOB-LEVEL");
    private int zombieBaseHP = API.monsterConfig.getInt("ZOMBIE_BASE_HP");
    private int wolfBaseHP = API.monsterConfig.getInt("WOLF_BASE_HP");
    private int villagerBaseHP = API.monsterConfig.getInt("VILLAGER_BASE_HP");
    private int squidBaseHP = API.monsterConfig.getInt("SQUID_BASE_HP");
    private int spiderBaseHP = API.monsterConfig.getInt("SPIDER_BASE_HP");
    private int snowmanBaseHP = API.monsterConfig.getInt("SNOWMAN_BASE_HP");
    private int slimeBaseHP = API.monsterConfig.getInt("SLIME_BASE_HP");
    private int skeletonBaseHP = API.monsterConfig.getInt("SKELETON_BASE_HP");
    private int silverfishBaseHP = API.monsterConfig.getInt("SILVERFISH_BASE_HP");
    private int sheepBaseHP = API.monsterConfig.getInt("SHEEP_BASE_HP");
    private int rabbitBaseHP = API.monsterConfig.getInt("RABBIT_BASE_HP");
    private int pigzombieBaseHP = API.monsterConfig.getInt("PIGZOMBIE_BASE_HP");
    private int pigBaseHP = API.monsterConfig.getInt("PIG_BASE_HP");
    private int mushroomcowBaseHP = API.monsterConfig.getInt("MUSHROOMCOW_BASE_HP");
    private int magmacubeBaseHP = API.monsterConfig.getInt("MAGMACUBE_BASE_HP");
    private int guardianBaseHP = API.monsterConfig.getInt("GUARDIAN_BASE_HP");
    private int giantBaseHP = API.monsterConfig.getInt("GIANT_BASE_HP");
    private int ghastBaseHP = API.monsterConfig.getInt("GHAST_BASE_HP");
    private int endermiteBaseHP = API.monsterConfig.getInt("ENDERMITE_BASE_HP");
    private int endermanBaseHP = API.monsterConfig.getInt("ENDERMAN_BASE_HP");
    private int enderdragonBaseHP = API.monsterConfig.getInt("ENDERDRAGON_BASE_HP");
    private int creeperBaseHP = API.monsterConfig.getInt("CREEPER_BASE_HP");
    private int cowBaseHP = API.monsterConfig.getInt("COW_BASE_HP");
    private int chickenBaseHP = API.monsterConfig.getInt("CHICKEN_BASE_HP");
    private int cavespiderBaseHP = API.monsterConfig.getInt("CAVESPIDER_BASE_HP");
    private int blazeBaseHP = API.monsterConfig.getInt("BLAZE_BASE_HP");

    public int calculateDistanceFromSpawn(Location worldSpawn, Location monsterSpawn)
    {
        float deltaX = (float) (worldSpawn.getX() - monsterSpawn.getX());
        float deltaY= (float) (worldSpawn.getY() - monsterSpawn.getY());
        float deltaZ= (float) (worldSpawn.getZ() - monsterSpawn.getZ());
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
        int distance2 = Math.round(distance) / blocksPerMobLevel; //change this later to the config option
        if(distance2 < 1)
        {
            distance2 = 1;
        }
        return distance2;

    }


    @EventHandler
    public void applyMonsterLevelOnSpawn(CreatureSpawnEvent e)
    {

        Location worldSpawn = e.getLocation().getWorld().getSpawnLocation();
        Location monsterSpawn = e.getLocation();
        if (monsterSpawn == null) return;//if there's a problem
        int distance = calculateDistanceFromSpawn(worldSpawn,monsterSpawn);
        e.getEntity().setCustomName("LV "+distance);
       e.getEntity().setCustomNameVisible(true);

        //configure health per mob

        if(e.getEntity().getType() == EntityType.ZOMBIE) {
            e.getEntity().setMaxHealth(distance * zombieBaseHP);
            e.getEntity().setHealth(distance * zombieBaseHP);
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
            e.getEntity().setMaxHealth(distance * giantBaseHP);
            e.getEntity().setHealth(distance * giantBaseHP);
        }
        if(e.getEntity().getType() == EntityType.GHAST)
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
