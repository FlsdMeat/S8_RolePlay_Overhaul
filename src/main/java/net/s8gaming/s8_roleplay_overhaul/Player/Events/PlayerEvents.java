package net.s8gaming.s8_roleplay_overhaul.Player.Events;

import net.s8gaming.s8_roleplay_overhaul.Player.PlayerConfig;
import net.s8gaming.s8_roleplay_overhaul.Player.PlayerObject;
import net.s8gaming.s8_roleplay_overhaul.S8_RolePlay_Overhaul;
import net.s8gaming.s8_roleplay_overhaul.Storage.DBInterface;
import org.bukkit.entity.Player;

import java.util.Map;

public abstract class PlayerEvents {
    public S8_RolePlay_Overhaul plugin;
    public DBInterface database;
    public Map<String, PlayerObject> players;
    public PlayerConfig playerConfig;
}
