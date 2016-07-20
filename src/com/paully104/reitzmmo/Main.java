package com.paully104.reitzmmo;

import com.paully104.reitzmmo.ConfigFiles.*;
import com.paully104.reitzmmo.MonsterCombatRelated.MonsterLevelsDamage;
import com.paully104.reitzmmo.MonsterCombatRelated.MonsterLevelsHealth;
import com.paully104.reitzmmo.OnPlayerEvents.OnPlayerExitStatSave;
import com.paully104.reitzmmo.OnPlayerEvents.OnPlayerJoinStatSetup;
import com.paully104.reitzmmo.PlayerCombatRelated.PlayerAttackingMonster;
import com.paully104.reitzmmo.PlayerCombatRelated.PlayerDefeatsMonster;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Paul on 3/22/2016.
 */
public class Main extends JavaPlugin {
//build location: C:\Users\Paul\IdeaProjects\ReitzMMO\out\artifacts\ReitzMMO_jar


    @Override
    public void onEnable(){
        //Fired when the server enables the plugin
        getLogger().info("ReitzMMO is now enabled");

        //Setup PlayerData
        PlayerData.setup(this);

        //Register Config Files
        FileManager.FileManagerFiles();

        //Set Config File Data
        MonsterConfig.Configuration();
        PlayerConfig.Configuration();
        DebugConfig.Configuration();

        //Set API data for quicker config reading
        API.setMonsterConfig();
        API.setPlayerConfig();
        API.setDebugConfig();

        //Main Commands
        this.getCommand("ReitzMMO").setExecutor(new ReitzRPGMMO_Commands());
        this.getCommand("RRM").setExecutor(new ReitzRPGMMO_Commands());

        //Register Events
        registerEvents(this,new OnPlayerJoinStatSetup(), new MonsterLevelsHealth(), new OnPlayerExitStatSave(),
        new MonsterLevelsDamage(), new PlayerAttackingMonster(),new PlayerDefeatsMonster());


    }
    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }

    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}
