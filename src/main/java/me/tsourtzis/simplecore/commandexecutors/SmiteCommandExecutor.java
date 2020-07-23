package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.tsourtzis.simplecore.player.MyPlayer;

public class SmiteCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("smite")) {
			MyPlayer cmdSender = MyPlayer.getPlayerFromCommandSender(sender);
			
			if(args.length == 0) {
				if(!(cmdSender.hasPermission("simplecore.smite.self"))) {
					cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
				}else {
					cmdSender.smite();
					cmdSender.sendMessage(ChatColor.GRAY + "You have been smited.");
				}
			}else if(args.length == 1) {
				if(!(cmdSender.hasPermission("simplecore.smite.other"))) {
					cmdSender.sendMessage("");
				}else {
					
				}
			}else {
				cmdSender.sendMessage(ChatColor.GRAY + "Too many command arguments.");
			}
			
			cmdSender = null;
			
			return true;
		}
		
		return false;
	}

}
