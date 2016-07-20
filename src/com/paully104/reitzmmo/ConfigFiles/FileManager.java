package com.paully104.reitzmmo.ConfigFiles;

/**
 * Created by Paul on 3/23/2016.
 */
import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager
{
    public static File monsterHPConfig;
    public static File playerConfig;
    public static File debugConfig;



    public static void FileManagerFiles()
    {

        monsterHPConfig = new File("plugins/ReitzMMO/MonsterSettings/MonsterConfig.yml");
        playerConfig = new File("plugins/ReitzMMO/PlayerSettings/PlayerConfig.yml");
        debugConfig = new File("plugins/ReitzMMO/DebugSettings/DebugConfig.yml");



    }
    public static void FileManagerSave(File file)
    {
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        try {
            configuration.save(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
