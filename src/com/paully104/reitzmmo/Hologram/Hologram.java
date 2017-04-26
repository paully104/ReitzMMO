package com.paully104.reitzmmo.Hologram;


import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 */


public class Hologram {


    public void setHologram(Player p, World w, Location location,int exp) {
        System.out.println("Hologram: Part A");

        ArmorStand a = (ArmorStand)w.spawnEntity(location,EntityType.ARMOR_STAND);
        a.setVisible(false);
        a.setGravity(false);
        a.setCustomName(ChatColor.GREEN+ "+EXP: " + exp);
        a.setCustomNameVisible(true);

        Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, new Runnable() {
            @Override
            public void run() {
                System.out.println("Hologram: Part B");
                a.remove();
            }
        },100L);}
}