package net.s8gaming.s8_roleplay_overhaul.Player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerObject{
    private final String playerID;
    public String playerName;
    private Integer playerWallet;
    private Integer playerKillsInThisLife;
    private Integer totalPlayerKills;
    private String longestLife;
    private String lastDeath;
    private Integer totalMobKills;
    private String mostKilledMob;
    private String mostKilledMonster;
    private Integer distancedTraveled;
    private Integer blocksBroken;
    private Integer playerDeaths;
    private String playerDescription;
    public PlayerObject(Player player){
        this.playerID = player.getUniqueId().toString();
        this.playerName = player.getName();
        this.playerWallet = 0;
        this.playerKillsInThisLife = 0;
        this.totalPlayerKills = 0;
        this.longestLife = "None";
        this.lastDeath = "None";
        this.totalMobKills = 0;
        this.mostKilledMob = "None";
        this.mostKilledMonster = "None";
        this.distancedTraveled = 0;
        this.blocksBroken = 0;
        this.playerDeaths = 0;
        this.playerDescription = "None";
    }

    public String getNewPlayerTable(){
        return this.playerID + ", " +                        // id
                this.playerName + ", " +                     // name
                this.playerWallet + ", " +                   // wallet
                this.playerKillsInThisLife + ", "+           // killsInLife
                this.totalPlayerKills + ", " +               // totalKills
                this.longestLife + ", " +                    // longestLife
                this.lastDeath + ", " +                      // lastDeath
                this.totalMobKills + ", " +                  // totalMobKills
                this.mostKilledMob + ", " +                  // mostKilledMob
                this.mostKilledMonster + ", " +              // mostKilledMonster
                this.distancedTraveled + ", " +              // distancedTraveled
                this.blocksBroken + ", " +                   // blocksBroken
                this.playerDeaths + ", " +                   // playerDeaths
                this.playerDescription + ");";
    }

    public String getPlayerID(){
        return this.playerID;
    }
    public String getPlayerName(){
        return this.playerName;
    }

    public void setLongestLife(String longestLife) {
        this.longestLife = longestLife;
    }
    public String getLongestLife(){
        return this.longestLife;
    }

    public void setLastDeath(String lastDeath) {
        this.lastDeath = lastDeath;
    }
    public String getLastDeath(){
        return this.lastDeath;
    }

    public void setMostKilledMob(String mostKilledMob) {
        this.mostKilledMob = mostKilledMob;
    }
    public String getMostKilledMob(){
        return this.mostKilledMob;
    }

    public void setMostKilledMonster(String mostKilledMonster) {
        this.mostKilledMonster = mostKilledMonster;
    }
    public String getMostKilledMonster() {
        return this.mostKilledMonster;
    }

    public void setPlayerDescription(String playerDescription) {
        this.playerDescription = playerDescription;
    }
    public String getPlayerDescription() {
        return this.playerDescription;
    }

    public void setPlayerKillsInThisLife(Integer playerKillsInThisLife) {
        this.playerKillsInThisLife = playerKillsInThisLife;
    }
    public Integer getPlayerKillsInThisLife() {
        return this.playerKillsInThisLife;
    }

    public void setTotalPlayerKills(Integer totalPlayerKills) {
        this.totalPlayerKills = totalPlayerKills;
    }
    public Integer getTotalPlayerKills() {
        return totalPlayerKills;
    }

    public void setPlayerWallet(Integer playerWallet) {
        this.playerWallet = playerWallet;
    }
    public Integer getPlayerWallet() {
        return this.playerWallet;
    }

    public void setTotalMobKills(Integer totalMobKills) {
        this.totalMobKills = totalMobKills;
    }
    public Integer getTotalMobKills() {
        return this.totalMobKills;
    }

    public void setPlayerDeaths(Integer playerDeaths) {
        this.playerDeaths = playerDeaths;
    }
    public void increasePlayerDeaths() {
        this.playerDeaths += 1;
    }
    public Integer getPlayerDeaths() {
        return this.playerDeaths;
    }

    public void setDistancedTraveled(Integer distancedTraveled) {
        this.distancedTraveled = distancedTraveled;
    }
    public Integer getDistancedTraveled() {
        return this.distancedTraveled;
    }


    public void setBlocksBroken(Integer blocksBroken) {
        this.blocksBroken = blocksBroken;
    }
    public Integer getBlocksBroken() {
        return this.blocksBroken;
    }
}
