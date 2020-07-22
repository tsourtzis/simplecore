package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tsourtzis.simplecore.player.MyPlayer;

public class HealCommandExecutor implements CommandExecutor{
	
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("heal")) {
			if(sender instanceof Player) {
				MyPlayer cmdSender = new MyPlayer(sender);
				
				if(args.length == 0) {
					if(!(cmdSender.hasPermission("simplecore.heal.self"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						if(cmdSender.isHealthy()) {
							cmdSender.sendMessage(ChatColor.GRAY + "You are already at full health.");
						}else {
							cmdSender.heal();
							cmdSender.sendMessage(ChatColor.GRAY + "You have been healed.");
						}
					}
				}else if(args.length == 1) {
					if(!(cmdSender.hasPermission("simplecore.heal.other"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						if(args[0].equalsIgnoreCase("@all")) {
							MyPlayer currentPlayer = null;
							
							for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
								currentPlayer = new MyPlayer(onlinePlayer);
								if(!(currentPlayer.isHealthy())) {
									currentPlayer.heal();
									currentPlayer.sendMessage(ChatColor.GRAY + "You have been healed.");
								}
							}
							
							currentPlayer = null;
						}else if(args[0].equalsIgnoreCase("@all-excluding-self")) {
							MyPlayer currentPlayer = null;
							
							for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
								currentPlayer = new MyPlayer(onlinePlayer);
								
								if(!(currentPlayer.equals(cmdSender))) {
									if(!(currentPlayer.isHealthy())) {
										currentPlayer.heal();
										currentPlayer.sendMessage(ChatColor.GRAY + "You have been healed.");
									}
								}
							}
							
							currentPlayer = null;
						}else {
							MyPlayer target = new MyPlayer(args[0]);
							
							if(!(target.exists())) {
								cmdSender.sendMessage(ChatColor.WHITE + args[0] + ChatColor.GRAY + " is not online.");
							}else {
								if(target.isHealthy()) {
									cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is already at full health.");
								}else if(!(target.isAlive())) {
									cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is already dead, thus they cannot be healed.");
								}else {
									target.heal();
									target.sendMessage(ChatColor.GRAY + "You have been healed.");
									cmdSender.sendMessage(ChatColor.GRAY + "You healed " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + ".");
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
