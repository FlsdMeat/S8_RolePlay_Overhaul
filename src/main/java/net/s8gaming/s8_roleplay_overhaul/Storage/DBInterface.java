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
        dbType = databaseConfig.getDBConfiguration("dbType");
        dbTablePrefix = databaseConfig.getDBConfiguration("tablePrefix");
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
            switch (dbType){
                case "SQLite":
                    return sqLiteDB.checkIfItemExists(table, column, colMatch);
                case "MariaDB":
                    return mariaDB.checkIfItemExists(table, column, colMatch);
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
            switch (dbType){
                case "SQLite":
                    map = sqLiteDB.getCellValues(column, colMatch, table);
                case "MariaDB":
                    map = mariaDB.getCellValues(column, colMatch, table);
            }
        }catch( Exception exception){
            plugin.getLogger().log(Level.SEVERE, "DBInterface could not retrieve data properly: ", exception);
        }
        return map;
    }

    public void SetTable(String table, String columns, String data){
        try{
            switch (dbType){
                case "SQLite":
                    sqLiteDB.SetTable(table, columns, data);
                case "MariaDB":
                    mariaDB.SetTable(table, columns, data);
            }
        }catch( Exception exception){
            plugin.getLogger().log(Level.SEVERE, "DBInterface could not update data properly: ", exception);
        }
    }
    public void load(String tableCreation){
        try{
            switch (dbType){
                case "SQLite":
                    sqLiteDB.load(tableCreation);
                case "MariaDB":
                    mariaDB.load(tableCreation);
            }
        } catch (Exception exception){
            plugin.getLogger().log(Level.SEVERE, "DBInterface could not create tables properly: ", exception);
        }

    }

    public String getDBTablePrefix(){
        return dbTablePrefix;
    }

}
