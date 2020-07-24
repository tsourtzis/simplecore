package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tsourtzis.simplecore.player.MyPlayer;

public class RestoreCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("restore")) {
			if(sender instanceof Player) {
				MyPlayer cmdSender = MyPlayer.getPlayerFromCommandSender(sender);
				
				if(args.length == 0) {
					if(!(cmdSender.hasPermission("simplecore.restore.self"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						if(cmdSender.isRestored()) {
							cmdSender.sendMessage(ChatColor.GRAY + "You are already fully restored.");
						}else {
							cmdSender.restore();
							cmdSender.sendMessage(ChatColor.GRAY + "You have been fully restored.");
						}
					}
				}else if(args.length == 1) {
					if(!(cmdSender.hasPermission("simplecore.restore.other"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						if(args[0].equalsIgnoreCase("@all")) {
							MyPlayer currentPlayer = null;
							
							for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
								currentPlayer = new MyPlayer(onlinePlayer);
								if(currentPlayer.isAlive() && !(currentPlayer.isRestored())) {
									currentPlayer.kill();
									currentPlayer.sendMessage(ChatColor.GRAY + "You have been fully restored.");
								}
							}
							
							cmdSender.sendMessage(ChatColor.GRAY + "You fully restored all alive online players.");
							
							currentPlayer = null;
						}else if(args[0].equalsIgnoreCase("@all-excluding-self")) {
							MyPlayer currentPlayer = null;
							
							for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
								currentPlayer = new MyPlayer(onlinePlayer);
								if(currentPlayer.isAlive() && !(currentPlayer.isRestored()) && !(currentPlayer.equals(cmdSender))) {
									currentPlayer.kill();
									currentPlayer.sendMessage(ChatColor.GRAY + "You have been fully restored.");
								}
							}
							
							cmdSender.sendMessage(ChatColor.GRAY + "You fully restored all alive online players, except yourself.");
							
							currentPlayer = null;
						}else {
							MyPlayer target = MyPlayer.getPlayerFromString(args[0]);
							
							if(target == null) {
								cmdSender.sendMessage(ChatColor.WHITE + args[0] + ChatColor.GRAY + " is not online.");
							}else {
								if(target.isAlive()) {
									if(target.isRestored()) {
										cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is already fully restored.");
									}else {
										target.restore();
										target.sendMessage(ChatColor.GRAY + "You have been fully restored.");
										cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is now fully restored.");
									}
								}else {
									cmdSender.sendMessage(ChatColor.GRAY + target.getName() + ChatColor.WHITE + " is not alive.");
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
