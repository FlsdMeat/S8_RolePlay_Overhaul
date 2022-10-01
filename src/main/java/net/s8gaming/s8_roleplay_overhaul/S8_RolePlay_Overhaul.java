package net.s8gaming.s8_roleplay_overhaul;

import net.s8gaming.s8_roleplay_overhaul.Commands.Status;
import net.s8gaming.s8_roleplay_overhaul.Storage.DBSetup;
import org.bukkit.plugin.java.JavaPlugin;

public final class S8_RolePlay_Overhaul extends JavaPlugin {
    @Override
    public void onEnable() {
        new DBSetup();
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("status").setExecutor(new Status());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
