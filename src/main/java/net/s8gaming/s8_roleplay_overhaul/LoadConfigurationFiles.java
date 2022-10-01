package net.s8gaming.s8_roleplay_overhaul;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public abstract class LoadConfigurationFiles {
    static S8_RolePlay_Overhaul plugin;
    public static File configFile;
    public static FileConfiguration configFileObject;
    public static String fileName;
    public void setup(){
        configFile = new File(Bukkit.getServer().getPluginManager().getPlugin("S8_Roleplay_Overhaul").getDataFolder(), fileName + ".yml");

        if ( !configFile.exists() ){
            try{
                configFile.createNewFile();
            } catch (IOException exception){
                plugin.getLogger().log(Level.SEVERE, "Unable to create config file: ", exception);
            }
        }
        configFileObject = YamlConfiguration.loadConfiguration(configFile);
    }
    public static FileConfiguration config(){
        return configFileObject;
    }

    public static void save(){
        try{
            configFileObject.save(configFile);
        } catch (IOException exception) {
            plugin.getLogger().log(Level.SEVERE, "Unable to save the configuration file: ", exception);
        }
    }

    public static void reload(){
        configFileObject = YamlConfiguration.loadConfiguration(configFile);
    }
}
