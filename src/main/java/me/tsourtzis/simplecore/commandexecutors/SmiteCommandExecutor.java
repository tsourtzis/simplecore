package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.Bukkit;
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
					cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
				}else {
					if(args[0].equalsIgnoreCase("@all")) {
						MyPlayer currentPlayer = null;
						
						for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
							currentPlayer = new MyPlayer(onlinePlayer);
							if(currentPlayer.isAlive()) {
								currentPlayer.smite();
								currentPlayer.sendMessage(ChatColor.GRAY + "You have been smited.");
							}
						}
						
						cmdSender.sendMessage(ChatColor.GRAY + "You killed all alive online players.");
						
						currentPlayer = null;
					}else if(args[0].equalsIgnoreCase("@all-excluding-self")) {
						MyPlayer currentPlayer = null;
						
						for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
							currentPlayer = new MyPlayer(onlinePlayer);
							if(currentPlayer.isAlive() && !(currentPlayer.equals(cmdSender))) {
								currentPlayer.kill();
								currentPlayer.sendMessage(ChatColor.GRAY + "You have been smited.");
							}
						}
						
						cmdSender.sendMessage(ChatColor.GRAY + "You smited all alive online players, except yourself.");
						
						currentPlayer = null;
					}else {
						MyPlayer target = MyPlayer.getPlayerFromString(args[0]);
						
						if(target == null) {
							cmdSender.sendMessage(ChatColor.WHITE + args[0] + ChatColor.GRAY + " is not online.");
						}else {
							if(target.isAlive()) {
								target.smite();
								target.sendMessage(ChatColor.GRAY + "You have been smited.");
								cmdSender.sendMessage(ChatColor.GRAY + "You smited " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + ".");
							}else {
								cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is not alive, thus they cannot be smited.");
							}
						}
						
						target = null;
					}
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
