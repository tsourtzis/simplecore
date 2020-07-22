package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tsourtzis.simplecore.player.MyPlayer;

public class SmiteCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("smite")) {
			if(sender instanceof Player) {
				MyPlayer cmdSender = new MyPlayer(sender);
				
				if(args.length == 0) {
					if(!(cmdSender.hasPermission("simplecore.smite.self"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						cmdSender.smite();
						cmdSender.sendMessage(ChatColor.GRAY + "You have been smited.");
					}
				}else if(args.length == 1) {
					if(!(cmdSender.hasPermission("simplecore.smite.other"))) {
						cmdSender.sendMessage("You do not have permission to perform this command.");
					}else {
						MyPlayer target = new MyPlayer(args[0]);
						
						if(!(target.exists())) {
							cmdSender.sendMessage(ChatColor.WHITE + args[0] + ChatColor.GRAY + " is not online.");
						}else {
							target.smite();
							target.sendMessage(ChatColor.GRAY + "You have been smited.");
							cmdSender.sendMessage(ChatColor.GRAY + "You smited " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + ".");
						}
						
						target = null;
					}
				}else {
					cmdSender.sendMessage(ChatColor.GRAY + "Too many command arguments.");
				}
				
				cmdSender = null;
			}else {
				
			}
			
			return true;
		}
		
		return false;
	}

}
