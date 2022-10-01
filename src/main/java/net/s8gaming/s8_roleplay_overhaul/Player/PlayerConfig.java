package net.s8gaming.s8_roleplay_overhaul.Player;

import net.s8gaming.s8_roleplay_overhaul.LoadConfigurationFiles;

import java.util.Collections;

public class PlayerConfig extends LoadConfigurationFiles {
    public PlayerConfig(){
        super();
        fileName = "playerConfig";
        setup();
        config().addDefault("New Player.Wallet", 500);
        //Death Options
        config().addDefault("Death Options.Rollback Inventory.enabled", false);
        config().addDefault("Death Options.Rollback Inventory.Limit", 7);
        config().setComments("Death Options.Rollback Inventory.Limit", Collections.singletonList("After the limit is reached, the oldest save will be removed"));
        config().addDefault("Death Options.Death Chest.enabled", true);
        config().setComments("Death Options", Collections.singletonList("It is recommended to only have either Rollback enabled or Chest enabled, not both. This is for storage reasons, but be my guest, do as you please."));
        config().addDefault("Death Options.Death Chest.On Death.Locked", true);
        config().setComments("Death Options.Death Chest.On Death.Locked", Collections.singletonList("Locked to the player who died"));
        config().addDefault("Death Options.Death Chest.On Death.Locked.Time Limit", false);
        config().setComments("Death Options.Death Chest.On Death.Locked.Time Limit", Collections.singletonList("Set a time limit in minutes for how long the chest will be locked..."));
        config().addDefault("Death Options.Death Chest.Limit", 1);
        config().setComments("Death Options.Death Chest.Limit", Collections.singletonList("How many chests can stay in world"));
        config().options().copyDefaults(true);
        save();
    }
}
