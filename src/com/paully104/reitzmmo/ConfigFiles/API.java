package com.paully104.reitzmmo.ConfigFiles;

import com.paully104.reitzmmo.PlayerData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;

/**
 * Created by Paul on 3/22/2016.
 */
public class API {
    //Configs
    public static HashMap<String, PlayerData> Players = new HashMap<String, PlayerData>();
    public static FileConfiguration monsterConfig;
    public static FileConfiguration playerConfig;
    public static FileConfiguration debugConfig;

    //Start of sets
    public static void setMonsterConfig()
    {
        monsterConfig = YamlConfiguration.loadConfiguration(FileManager.monsterHPConfig);
    }
    public static void setPlayerConfig()
    {
        playerConfig = YamlConfiguration.loadConfiguration(FileManager.playerConfig);
    }
    public static void setDebugConfig()
    {
        debugConfig = YamlConfiguration.loadConfiguration(FileManager.debugConfig);
    }

}


