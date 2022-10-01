package net.s8gaming.s8_roleplay_overhaul;

import net.s8gaming.s8_roleplay_overhaul.Player.Commands.Status;
import net.s8gaming.s8_roleplay_overhaul.Player.PlayerHandler;
import net.s8gaming.s8_roleplay_overhaul.Storage.DBInterface;
import net.s8gaming.s8_roleplay_overhaul.Storage.DBConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class S8_RolePlay_Overhaul extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        LoadCommands();
        StartUpModules(new DBConfig());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void LoadCommands(){
        getCommand("status").setExecutor(new Status());
    }

    public void StartUpModules( DBConfig databaseConfig){
        DBInterface database = new DBInterface(this, databaseConfig);
        new PlayerHandler(this, database);
    }
}
