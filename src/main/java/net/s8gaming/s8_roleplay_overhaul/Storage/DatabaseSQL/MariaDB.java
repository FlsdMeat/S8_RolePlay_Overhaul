package net.s8gaming.s8_roleplay_overhaul.Storage.DatabaseSQL;

import net.s8gaming.s8_roleplay_overhaul.S8_RolePlay_Overhaul;
import net.s8gaming.s8_roleplay_overhaul.Storage.DBConfig;
import net.s8gaming.s8_roleplay_overhaul.Storage.Database;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.sql.*;
import java.util.Map;
import java.util.logging.Level;
public class MariaDB extends Database {
    String address;
    String dbname;
    String username;
    String password;
    Integer port;
    String tablePrefix;
    private static File file;
    private static FileConfiguration databaseConfigurationFile;
    public MariaDB(S8_RolePlay_Overhaul instance, DBConfig databaseConfig){
        super(instance);
        address = databaseConfig.getConfigString("Database Address (IP or FQDN)");
        dbname =  databaseConfig.getConfigString("Database Name");
        username = databaseConfig.getConfigString("Database Username");
        password =  databaseConfig.getConfigString("Database Password");
        port = databaseConfig.getConfigInteger("Database Port");
        tablePrefix =  databaseConfig.getConfigString("Database Table Prefix");
    }
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
    public void load(String tableCreation) {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(tableCreation);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
