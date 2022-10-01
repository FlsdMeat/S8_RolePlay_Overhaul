package net.s8gaming.s8_roleplay_overhaul.Storage;
import net.s8gaming.s8_roleplay_overhaul.LoadConfigurationFiles;

public class DBSetup {
    private LoadConfigurationFiles dbConfig;
    public DBSetup(){
        dbConfig = new LoadConfigurationFiles("database");
        dbConfig.setup();
        dbConfig.get().addDefault("Database Type", "SQLite");
        dbConfig.get().addDefault("Database Address (IP or FQDN)", "localhost");
        dbConfig.get().addDefault("Database Port", 3306);
        dbConfig.get().addDefault("Database Username", "root");
        dbConfig.get().addDefault("Database Password", "password123!");
        dbConfig.get().addDefault("Table Prefix", "s8PlayerOverhaul_");
        dbConfig.get().options().copyDefaults(true);
        dbConfig.save();
    }
}
