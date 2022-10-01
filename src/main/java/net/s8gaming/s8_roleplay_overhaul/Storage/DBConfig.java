package net.s8gaming.s8_roleplay_overhaul.Storage;
import net.s8gaming.s8_roleplay_overhaul.LoadConfigurationFiles;

import java.util.Collections;

public class DBConfig extends LoadConfigurationFiles{
    public DBConfig(){
        super();
        fileName = "databaseConfig";
        setup();
        config().addDefault("Database Type", "SQLite");
        config().setComments("Database Type", Collections.singletonList("// SQLite, MariaDB (Preferred over MySQL), MySQL, MongoDB, PostgreSQL"));
        config().addDefault("Database Address (IP or FQDN)", "localhost");
        config().setComments("Database Address", Collections.singletonList("// Address, Port, Username, Password, DBName and Prefix not needed for SQLite option"));
        config().addDefault("Database Port", 3306);
        config().addDefault("Database Username", "root");
        config().addDefault("Database Password", "password123!");
        config().addDefault("Database Name", "MinecraftDatabase");
        config().addDefault("Database Table Prefix", "s8PlayerOverhaul");
        config().setComments("Database Table Prefix", Collections.singletonList("// If preferred, what would the table prefix start as for each of the data collections made by the plugin"));
        config().options().copyDefaults(true);
        save();
    }
}
