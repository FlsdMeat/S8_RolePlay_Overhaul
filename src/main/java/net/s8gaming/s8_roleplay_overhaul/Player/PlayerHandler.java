package net.s8gaming.s8_roleplay_overhaul.Player;

import net.s8gaming.s8_roleplay_overhaul.Player.Events.OnDeath;
import net.s8gaming.s8_roleplay_overhaul.Player.Events.OnLeave;
import net.s8gaming.s8_roleplay_overhaul.S8_RolePlay_Overhaul;
import net.s8gaming.s8_roleplay_overhaul.Storage.DBInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class PlayerHandler implements Listener {
    private static S8_RolePlay_Overhaul plugin;
    private final DBInterface database;
    private final String dbTable;
    String fullColumns = "(id, name, wallet, killsInLife, totalKills, " +
            "longestLife, lastDeath, totalMobKills, mostKilledMob, " +
            "mostKilledMonster, distancedTraveled, blocksBroken, " +
            "playerDeaths, playerDescription)";
    public Map<String, PlayerObject> players;
    public PlayerConfig playerConfig;
    public PlayerHandler(S8_RolePlay_Overhaul instance, DBInterface database){
        plugin = instance;
        this.database = database;
        dbTable = database.getDBTablePrefix() + "_players";
        this.players = new HashMap<String, PlayerObject>();
        registerEvents();
    }

    private void registerEvents(){
        new OnLeave(plugin, database, players, playerConfig);
        new OnDeath(plugin, database, players, playerConfig);
    }

    private void setupTable(){
        String tableCreate = "CREATE TABLE IF NOT EXISTS " + dbTable + " (" + // make sure to put your table name in here too.
            "`id` varchar(32) NOT NULL," +
            "`name` varchar(32) NOT NULL," +
            "`wallet` int(16) NOT NULL," +
            "`killsInThisLife` int(11) NOT NULL," +
            "`totalKills` int(11) NOT NULL," +
            "`longestLife` varchar(16) NOT NULL," +
            "`lastDeath` varchar(16) NOT NULL," +
            "`totalMobKills` int(7) NOT NULL," +
            "`mostKilledMob` varchar(14) NOT NULL," +
            "`mostKilledMonster` varchar(14) NOT NULL," +
            "`distancedTraveled` int(7) NOT NULL," +
            "`blocksBroken` int(7) NOT NULL," +
            "`deaths` int(7) NOT NULL," +
            "`playerDescription` varchar(256) NOT NULL," +
            "PRIMARY KEY (`id`)" +  // This is creating 3 colums Player, Kills, Total. Primary key is what you are going to use as your indexer. Here we want to use player so
            ");";
        database.load(tableCreate);
    }

    public void updateNewPlayer(PlayerObject player, String data){
        database.SetTable(dbTable, fullColumns, data);
    }
    public void returningPlayer(PlayerObject player, Map<String, String> playerData){
        player.setPlayerWallet(Integer.parseInt(playerData.get("wallet")));
        player.setPlayerKillsInThisLife(Integer.parseInt(playerData.get("killsInThisLife")));
        player.setTotalPlayerKills(Integer.parseInt(playerData.get("totalKills")));
        player.setLongestLife(playerData.get("longestLife"));
        player.setLastDeath(playerData.get("lastDeath"));
        player.setTotalMobKills(Integer.parseInt(playerData.get("totalMobKills")));
        player.setMostKilledMob(playerData.get("mostKilledMob"));
        player.setMostKilledMonster(playerData.get("mostKilledMonster"));
        player.setDistancedTraveled(Integer.parseInt(playerData.get("distancedTraveled")));
        player.setBlocksBroken(Integer.parseInt(playerData.get("blocksBroken")));
        player.setPlayerDeaths(Integer.parseInt(playerData.get("deaths")));
        player.setPlayerDescription(playerData.get("description"));
    }

    @EventHandler
    public void OnJoin(PlayerJoinEvent event){
        Player newPlayer = event.getPlayer();
        PlayerObject player = new PlayerObject(newPlayer);
        String playerID = newPlayer.getUniqueId().toString();
        String playerName = newPlayer.getDisplayName();
        boolean playerInDB = false;
        try{
            playerInDB = database.checkIfItemExists(dbTable, "id", playerID);
        } catch (Exception exception){
            plugin.getLogger().log(Level.WARNING, "PlayerHandler could not see if player exists in storage: ", exception);
        }
        try {
            if (!playerInDB){
                updateNewPlayer(player, player.getNewPlayerTable());
                newPlayer.sendMessage("Welcome " + playerName + " to S8 New World!\n" +
                        "If you need any help, check out our discord: DISCORD\n" +
                        "Be sure to check out our rules with /rules, within discord, or WEBSITE");
            } else {
                newPlayer.sendMessage("Welcome back " + playerName + "!");
                Map<String, String> playerData = database.getTableData("id", playerID, dbTable);
                returningPlayer(player, playerData);
            }
            players.put(player.getPlayerID(), player);
        } catch (Exception exception){
            plugin.getLogger().log(Level.WARNING, "PlayerHandler could not handle player joining: ", exception);
        }
    }


}
