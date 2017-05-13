package com.paully104.reitzmmo.Skills;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

/**
 * Created by Paul on 5/7/2017.
 */
public class Shift_Back {
    public static void performShiftBack(PlayerToggleSneakEvent event) {
        Vector dir = event.getPlayer().getLocation().getDirection();
        if(event.getPlayer().isOnGround()) {
        event.getPlayer().setVelocity(dir.multiply(-1.25));
    }
}
}
