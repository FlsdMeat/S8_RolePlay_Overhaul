package net.s8gaming.s8_roleplay_overhaul.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Status implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label,String[] args){
        if (sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage("Good");
        }
        return true;
    }
}
