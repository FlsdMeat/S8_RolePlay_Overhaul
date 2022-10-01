package net.s8gaming.s8_roleplay_overhaul.Player.Events;

import net.s8gaming.s8_roleplay_overhaul.Player.PlayerConfig;
import net.s8gaming.s8_roleplay_overhaul.Player.PlayerObject;
import net.s8gaming.s8_roleplay_overhaul.S8_RolePlay_Overhaul;
import net.s8gaming.s8_roleplay_overhaul.Storage.DBInterface;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;

public class OnLeave extends PlayerEvents implements Listener {
    public OnLeave(S8_RolePlay_Overhaul instance, DBInterface database, Map<String, PlayerObject> players, PlayerConfig playerConfig){
        plugin = instance;
        this.database = database;
        this.players = players;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void OnLeaveEvent(PlayerQuitEvent event){
    }
}
