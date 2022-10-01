package net.s8gaming.s8_roleplay_overhaul.Storage;
import net.s8gaming.s8_roleplay_overhaul.S8_RolePlay_Overhaul;

import net.s8gaming.s8_roleplay_overhaul.ErrorHandling.Error;
import net.s8gaming.s8_roleplay_overhaul.ErrorHandling.Errors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.entity.Player;

public abstract class Database {
    public S8_RolePlay_Overhaul plugin;
    public Connection connection;
    public String table = "table_name"; //"default" name for a table within a database
    public int tokens = 0;
    public Database(S8_RolePlay_Overhaul instance){
        plugin = instance;
    }

    public abstract Connection getSQLConnection(); //default abstraction to connect to a sql database

    public void initialize(){
        connection = getSQLConnection();
        try { //default statement for getting data base upon player id or just player in general
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE player = ?");
            ResultSet rs = ps.executeQuery();
            close(ps, rs);
        } catch (SQLException exception){
            plugin.getLogger().log(Level.SEVERE, "Unable to retrieve connection", exception);
        }
    }

    public Integer getCellIntegerValue(String column, String colMatch, String cell){
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
    public void setCellValue(Player player, Integer tokens, Integer total){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = getSQLConnection();
            ps = conn.prepareStatement("REPLACE INTO " + table + " (player,kills,total) VALUES(?,?,?)");
            ps.setString(1, player.getName().toLowerCase());
            ps.setInt(2, tokens);
            ps.setInt(3, total);
        } catch (SQLException exception){
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), exception);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException exception){
                plugin.getLogger().log(Level.SEVERE,  Errors.sqlConnectionClose(), exception);
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
