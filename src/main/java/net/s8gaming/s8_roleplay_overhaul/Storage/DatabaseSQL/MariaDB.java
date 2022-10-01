package net.s8gaming.s8_roleplay_overhaul.Storage.DatabaseSQL;

import net.s8gaming.s8_roleplay_overhaul.S8_RolePlay_Overhaul;
import net.s8gaming.s8_roleplay_overhaul.Storage.Database;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.sql.*;
import java.util.logging.Level;
public abstract class MariaDB extends Database {
    String address;
    String dbname;
    String username;
    String password;
    Integer port;
    String tablePrefix;
    private static File file;
    private static FileConfiguration databaseConfigurationFile;
    public MariaDB(S8_RolePlay_Overhaul instance){
        super(instance);
        address = plugin.getConfig().getString("MariaDB.Filename", "localhost");
        dbname = plugin.getConfig().getString("MariaDB.Filename", "db_name");
        username = plugin.getConfig().getString("MariaDB.Filename", "username");
        password = plugin.getConfig().getString("MariaDB.Filename", "password");
        port = plugin.getConfig().getInt("MariaDB.Filename", 3306);
        tablePrefix = plugin.getConfig().getString("MariaDB.Filename", "password");
    }
    /*public String MariaDBCreateTokensTable = "CREATE TABLE IF NOT EXISTS table_name (" + // make sure to put your table name in here too.
            "`player` varchar(32) NOT NULL," + // This creates the different colums you will save data too. varchar(32) Is a string, int = integer
            "`kills` int(11) NOT NULL," +
            "`total` int(11) NOT NULL," +
            "PRIMARY KEY (`player`)" +  // This is creating 3 colums Player, Kills, Total. Primary key is what you are going to use as your indexer. Here we want to use player so
            ");"; // we can search by player, and get kills and total. If you some how were searching kills it would provide total and player.
    // SQL creation stuff, You can leave the blow stuff untouched.*/
    public Connection getSQLConnection() {
        try {
            if( connection !=null && !connection.isClosed() ){
                return connection;
            }
            connection = DriverManager.getConnection("jdbc:mysql://" + username + ":" + password + "@" + address + ":" + port + "/" + dbname);
            return connection;
        } catch (SQLException exception) {
            plugin.getLogger().log(Level.SEVERE,"MariaDB exception on initialize", exception);
        }
        return null;
    }

    public abstract void load();
}
