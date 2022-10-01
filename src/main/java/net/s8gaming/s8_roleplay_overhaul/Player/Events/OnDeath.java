package net.s8gaming.s8_roleplay_overhaul.Player.Events;

import net.s8gaming.s8_roleplay_overhaul.Player.DeathChest.DeathChest;
import net.s8gaming.s8_roleplay_overhaul.Player.InventoryRollback.InventoryRollback;
import net.s8gaming.s8_roleplay_overhaul.Player.PlayerConfig;
import net.s8gaming.s8_roleplay_overhaul.Player.PlayerObject;
import net.s8gaming.s8_roleplay_overhaul.S8_RolePlay_Overhaul;
import net.s8gaming.s8_roleplay_overhaul.Storage.DBInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Map;

public class OnDeath extends PlayerEvents implements Listener {
    public OnDeath(S8_RolePlay_Overhaul instance, DBInterface database, Map<String, PlayerObject> players, PlayerConfig playerConfig){
        plugin = instance;
        this.database = database;
        this.players = players;
        this.playerConfig = playerConfig;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void OnDeathEvent(PlayerDeathEvent event){
        Player victim = event.getEntity().getPlayer();
        assert victim != null;
        PlayerObject victimObject = players.get(victim.getUniqueId().toString());
        victimObject.increasePlayerDeaths();
        if(playerConfig.getConfigBoolean("Death Options.Death Chest.enabled")){
            new DeathChest(victim, victimObject, playerConfig);
        }
        if(playerConfig.getConfigBoolean("Death Options.Rollback Inventory.enabled")){
            new InventoryRollback(victim, victimObject, playerConfig);
        }
    }
}

