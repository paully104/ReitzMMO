package com.paully104.reitzmmo.Skills;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Created by Paul on 5/6/2017.
 */
public class Bomb_Arrow {

    public static void performFireArrow(EntityShootBowEvent event, Entity arrow, int level)
    {
        Projectile projectile = (Projectile)event.getProjectile();
            event.setProjectile(arrow.getWorld().spawnEntity(arrow.getLocation(), EntityType.PRIMED_TNT));
            event.getProjectile().setVelocity(projectile.getVelocity());
    }
}
