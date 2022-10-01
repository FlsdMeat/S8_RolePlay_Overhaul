package net.s8gaming.s8_roleplay_overhaul.Storage;

import net.s8gaming.s8_roleplay_overhaul.S8_RolePlay_Overhaul;
import net.s8gaming.s8_roleplay_overhaul.Storage.DatabaseSQL.MariaDB;
import net.s8gaming.s8_roleplay_overhaul.Storage.DatabaseSQL.SQLite;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class DBInterface {
    private final S8_RolePlay_Overhaul plugin;
    private SQLite sqLiteDB;
    private MariaDB mariaDB;
    private final String dbType;
    private final String dbTablePrefix;

    public DBInterface(S8_RolePlay_Overhaul instance, DBConfig databaseConfig){
        plugin = instance;
        dbType = databaseConfig.getConfigString("Database Type");
        this.dbTablePrefix = databaseConfig.getConfigString("Database Table Prefix");
        try{
            if (dbType.equals("SQLite")){
                sqLiteDB = new SQLite(plugin);
            } else if (dbTablePrefix.equals("")){
                plugin.getLogger().log(Level.SEVERE, "DBInterface table prefix was empty, please specify a name for the database table prefix");
            } else if (dbType.equals("MariaDB")){
                mariaDB = new MariaDB(plugin, databaseConfig);
            } else {
                plugin.getLogger().log(Level.SEVERE, "DBInterface couldn't read the dbType correctly, make sure its of these types:\n" +
                        "SQLite, MariaDB, MySQL, MongoDB, PostgreDB");
            }
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "DBInterface couldn't load the database options correctly: ", exception);
        }

    }

    public boolean checkIfItemExists(String table, String column, String colMatch){
        try{
            if(dbType.equals("SQLite")) {
                    return sqLiteDB.checkIfItemExists(table, column, colMatch);
            } else if (dbType.equals("MariaDB")) {
                    return mariaDB.checkIfItemExists(table, column, colMatch);
            } else {
                plugin.getLogger().warning("DBInterface warning in checkIfItemExists");
            }
        }catch( Exception exception){
            plugin.getLogger().log(Level.SEVERE, "DBInterface could not retrieve data properly: ", exception);
        }
        return false;
    }

    public Map<String, String> getTableData(String column, String colMatch, String table){
        Map<String, String> map = new HashMap<String, String>();
        map.put("Result", "False");
        try{
            if(dbType.equals("SQLite")) {
                    map = sqLiteDB.getCellValues(column, colMatch, table);
            } else if (dbType.equals("MariaDB")) {
                    map = mariaDB.getCellValues(column, colMatch, table);
            } else {
                plugin.getLogger().warning("DBInterface warning in getTableData");
            }
        }catch( Exception exception){
            plugin.getLogger().log(Level.SEVERE, "DBInterface could not retrieve data properly: ", exception);
        }
        return map;
    }

    public void SetTable(String table, String columns, String data){
        try{
            if(dbType.equals("SQLite")) {
                    sqLiteDB.SetTable(table, columns, data);
            } else if (dbType.equals("MariaDB")) {
                    mariaDB.SetTable(table, columns, data);
            } else {
                plugin.getLogger().warning("DBInterface warning in SetTable");
            }
        }catch( Exception exception){
            plugin.getLogger().log(Level.SEVERE, "DBInterface could not set data properly: ", exception);
        }
    }
    public void UpdateTable(String table, String statement){
        try{
            if(dbType.equals("SQLite")) {
                sqLiteDB.UpdateTable(table, statement);
            } else if (dbType.equals("MariaDB")) {
                mariaDB.UpdateTable(table, statement);
            } else {
                plugin.getLogger().warning("DBInterface warning in UpdateTable");
            }
        }catch( Exception exception){
            plugin.getLogger().log(Level.SEVERE, "DBInterface could not update data properly: ", exception);
        }
    }
    public void load(String tableCreation){
        try{
            if(dbType.equals("SQLite")) {
                sqLiteDB.load(tableCreation);
            } else if (dbType.equals("MariaDB")) {
                mariaDB.load(tableCreation);
            } else {
                plugin.getLogger().warning("DBInterface warning in load");
            }
        } catch (Exception exception){
            plugin.getLogger().log(Level.SEVERE, "DBInterface could not create tables properly: ", exception);
        }

    }

    public String getDBTablePrefix(){
        return this.dbTablePrefix;
    }

}
