package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tsourtzis.simplecore.player.MyPlayer;

public class ExtinguishCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("extinguish")) {
			if(sender instanceof Player) {
				MyPlayer cmdSender = MyPlayer.getPlayerFromCommandSender(sender);
				
				if(args.length == 0) {
					if(!(cmdSender.hasPermission("simplecore.extinguish.self"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						if(cmdSender.isIgnited()) {
							cmdSender.extinguish();
							cmdSender.sendMessage(ChatColor.GRAY + "You are no longer on fire.");
						}else {
							cmdSender.sendMessage(ChatColor.GRAY + "You are not on fire.");
						}
					}
				}else if(args.length == 1) {
					if(!(cmdSender.hasPermission("simplecore.extinguish.other"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						if(args[0].equalsIgnoreCase("@all")) {
							MyPlayer currentPlayer = null;
							
							for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
								currentPlayer = new MyPlayer(onlinePlayer);
								if(currentPlayer.isAlive() && currentPlayer.isIgnited()) {
									currentPlayer.extinguish();
									currentPlayer.sendMessage(ChatColor.GRAY + "You are no longer on fire.");
								}
							}
							
							cmdSender.sendMessage(ChatColor.GRAY + "You put out all alive online players that were on fire.");
							
							currentPlayer = null;
						}else if(args[0].equalsIgnoreCase("@all-excluding-self")) {
							MyPlayer currentPlayer = null;
							
							for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
								currentPlayer = new MyPlayer(onlinePlayer);
								if(currentPlayer.isAlive() && currentPlayer.isIgnited() && !(currentPlayer.equals(cmdSender))) {
									currentPlayer.extinguish();
									currentPlayer.sendMessage(ChatColor.GRAY + "You are no longer on fire.");
								}
							}
							
							cmdSender.sendMessage(ChatColor.GRAY + "You put out all alive online players that were on fire, except yourself.");
							
							currentPlayer = null;
						}else {
							MyPlayer target = MyPlayer.getPlayerFromCommandSender(sender);
							
							if(target == null) {
								cmdSender.sendMessage(ChatColor.WHITE + args[0] + ChatColor.GRAY + " is not online.");
							}else {
								if(target.isIgnited()) {
									target.extinguish();
									target.sendMessage(ChatColor.GRAY + "You are no longer on fire.");
								}else {
									cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is not on fire.");
								}
							}
							
							target = null;
						}
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
