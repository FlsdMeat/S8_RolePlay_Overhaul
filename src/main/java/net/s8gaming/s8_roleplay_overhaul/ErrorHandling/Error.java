package net.s8gaming.s8_roleplay_overhaul.ErrorHandling;

import net.s8gaming.s8_roleplay_overhaul.S8_RolePlay_Overhaul;

import java.util.logging.Level;

public class Error{
    public static void execute(S8_RolePlay_Overhaul plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
    }
    public static void close(S8_RolePlay_Overhaul plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
    }
}
