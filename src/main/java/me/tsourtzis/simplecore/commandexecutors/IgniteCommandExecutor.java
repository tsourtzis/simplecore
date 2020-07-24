package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tsourtzis.simplecore.player.MyPlayer;
import net.md_5.bungee.api.ChatColor;

public class IgniteCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("ignite")) {
			if(sender instanceof Player) {
				MyPlayer cmdSender = MyPlayer.getPlayerFromCommandSender(sender);
				
				if(args.length == 0) {
					if(!(cmdSender.hasPermission("simplecore.ignite.self"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						if(cmdSender.isIgnited()) {
							cmdSender.sendMessage(ChatColor.GRAY + "You are already on fire.");
						}else {
							cmdSender.ignite();
							cmdSender.sendMessage(ChatColor.GRAY + "You have been ignited.");
						}
					}
				}else if(args.length == 1) {
					if(!(cmdSender.hasPermission("simplecore.ignite.other"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						if(args[0].equalsIgnoreCase("@all")) {
							MyPlayer currentPlayer = null;
							
							for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
								currentPlayer = new MyPlayer(onlinePlayer);
								if(currentPlayer.isAlive() && !(currentPlayer.isIgnited())) {
									currentPlayer.ignite();
									currentPlayer.sendMessage(ChatColor.GRAY + "You have been ignited.");
								}
							}
							
							cmdSender.sendMessage(ChatColor.GRAY + "You ignited all alive online players.");
							
							currentPlayer = null;
						}else if(args[0].equalsIgnoreCase("@all-excluding-self")) {
							MyPlayer currentPlayer = null;
							
							for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
								currentPlayer = new MyPlayer(onlinePlayer);
								if(currentPlayer.isAlive() && !(currentPlayer.equals(cmdSender)) && !(currentPlayer.isIgnited())) {
									currentPlayer.ignite();
									currentPlayer.sendMessage(ChatColor.GRAY + "You have been ignited.");
								}
							}
							
							cmdSender.sendMessage(ChatColor.GRAY + "You ignited all alive online players, except yourself.");
							
							currentPlayer = null;
						}else {
							MyPlayer target = MyPlayer.getPlayerFromString(args[0]);
							
							if(target == null) {
								cmdSender.sendMessage(ChatColor.WHITE + args[0] + ChatColor.GRAY + " is not online.");
							}else {
								if(!(target.isAlive())) {
									cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is not alive, thus they cannot be ignited.");
								}else {
									if(target.isIgnited()) {
										cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is already on fire.");
									}else {
										target.ignite();
										target.sendMessage(ChatColor.GRAY + "You have been ignited.");
										cmdSender.sendMessage(ChatColor.GRAY + "You ignited " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + ".");
									}
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
