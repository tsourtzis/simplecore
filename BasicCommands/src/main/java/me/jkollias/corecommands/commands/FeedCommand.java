package me.jkollias.corecommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class FeedCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("Feed")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if(args.length == 0) {
					player.sendMessage(ChatColor.RED + "You need to specify a player!");
					
				}else if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					
					if(target == null) {
						player.sendMessage(ChatColor.RED + "This player does not exist!");
						
					}else {
						if(target.getFoodLevel() < 20) {
							player.setFoodLevel(20);
							player.sendMessage(ChatColor.GOLD + "Your hunger has been restored!");
							
							return true;
							
						}else {
							player.sendMessage("You are full!");
							
						}
					}
					
				}else {
					player.sendMessage(ChatColor.RED + "You cannot feed more than one players!");
				}
			}
		}
		
		return false;
	}

}
