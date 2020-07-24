package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tsourtzis.simplecore.player.MyPlayer;

public class AntidoteCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("antidote")) {
			if(sender instanceof Player) {
				MyPlayer cmdSender = MyPlayer.getPlayerFromCommandSender(sender);
				
				if(args.length == 0) {
					if(!(cmdSender.hasPermission("simplecore.antidote.self"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						if(cmdSender.hasActivePotionEffects()) {
							cmdSender.antidote();
							cmdSender.sendMessage(ChatColor.GRAY + "You no longer have any active potion effects.");
						}else {
							cmdSender.sendMessage(ChatColor.GRAY + "You do not have any active potion effects.");
						}
					}
				}else if(args.length == 1) {
					if(!(cmdSender.hasPermission("simplecore.antidote.other"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						if(args[0].equalsIgnoreCase("@all")) {
							MyPlayer currentPlayer = null;
							
							for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
								currentPlayer = new MyPlayer(onlinePlayer);
								if(currentPlayer.isAlive() && currentPlayer.hasActivePotionEffects()) {
									currentPlayer.antidote();
									currentPlayer.sendMessage(ChatColor.GRAY + "You no longer have any active potion effects.");
								}
							}
							
							cmdSender.sendMessage(ChatColor.GRAY + "All alive online players no longer have any active potion effects.");
							
							currentPlayer = null;
						}else if(args[0].equalsIgnoreCase("@all-excluding-self")) {
							MyPlayer currentPlayer = null;
							
							for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
								currentPlayer = new MyPlayer(onlinePlayer);
								if(currentPlayer.isAlive() && currentPlayer.hasActivePotionEffects() && !(currentPlayer.equals(cmdSender))) {
									currentPlayer.antidote();
									currentPlayer.sendMessage(ChatColor.GRAY + "You no longer have any active potion effects.");
								}
							}
							
							cmdSender.sendMessage(ChatColor.GRAY + "All alive online players no longer have any active potion effects, except yourself.");
							
							currentPlayer = null;
						}else {
							MyPlayer target = MyPlayer.getPlayerFromString(args[0]);
							
							if(target == null) {
								cmdSender.sendMessage(ChatColor.WHITE + args[0] + ChatColor.GRAY + " is not online.");
							}else {
								if(target.isAlive()) {
									if(target.hasActivePotionEffects()) {
										target.antidote();
										target.sendMessage(ChatColor.GRAY + "You no longer have any active potion effects");
										cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " no longer has any active potion effects.");
									}else {
										cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " has no active potion effects.");
									}
								}else {
									cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is not alive.");
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
