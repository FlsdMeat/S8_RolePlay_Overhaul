package net.s8gaming.s8_roleplay_overhaul.Storage;
import net.s8gaming.s8_roleplay_overhaul.S8_RolePlay_Overhaul;

import net.s8gaming.s8_roleplay_overhaul.ErrorHandling.Error;
import net.s8gaming.s8_roleplay_overhaul.ErrorHandling.Errors;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public abstract class Database {
    public S8_RolePlay_Overhaul plugin;
    public Connection connection;
    public Database(S8_RolePlay_Overhaul instance){
        plugin = instance;
    }

    public abstract Connection getSQLConnection(); //default abstraction to connect to a sql database

    public boolean checkIfItemExists(String table, String column, String colMatch){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT " + column + " FROM " + table + " WHERE " + column + " = '" + colMatch + "';");
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString(column).equalsIgnoreCase(colMatch.toLowerCase())){
                    return true; // Return true if item in column exists,
                }
            }
        } catch (SQLException exception){
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), exception);
        } finally {
            try{
                if ( ps != null ){
                    ps.close();
                }
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException exception){
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), exception);
            }
        }
        return false;
    }
    public Integer getCellIntegerValue(String table, String column, String colMatch, String cell){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE " + column + " = '" + colMatch + "';");
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString(column).equalsIgnoreCase(colMatch.toLowerCase())){
                    return rs.getInt(cell); // example case of getting kills a player has
                }
            }
        } catch (SQLException exception){
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), exception);
        } finally {
            try{
                if ( ps != null ){
                    ps.close();
                }
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException exception){
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), exception);
            }
        }
        return 0;
    }

    public Map<String, String> getCellValues(String column, String colMatch, String table){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("Result", "False");
        try{
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE " + column + " = '" + colMatch + "';");
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            try{
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getString(i));
                    plugin.getLogger().info(rsmd.getColumnLabel(i) + ", " + rs.getString(i));
                }
                map.put("Result", "True");
                return map;
            } catch (Exception exception) {
                plugin.getLogger().log(Level.SEVERE, "Database had an issue with getCellValues", exception);
            }
        } catch (SQLException exception){
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), exception);
        } finally {
            try{
                close(ps, rs);
            } catch (Exception exception){
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), exception);
            }
        }
        return map;
    }

    public void SetTable(String table, String columns, String data){
        Connection conn = null;
        Statement s = null;
        try {
            conn = getSQLConnection();
            s = conn.createStatement();
            s.executeUpdate("INSERT INTO " + table + " " + columns + " VALUES " + data);
            s.close();
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Storage wasn't able to push new data: ", ex);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
    }

    public void UpdateTable(String table, String statement){
        Connection conn = null;
        Statement s = null;
        try {
            conn = getSQLConnection();
            s = conn.createStatement();
            s.executeUpdate("UPDATE " + table + " SET " + statement);
            s.close();
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Storage wasn't able to push new data: ", ex);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
    }

    public void close(PreparedStatement ps,ResultSet rs){
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
            Error.close(plugin, ex);
        }
    }
}
