package me.jkollias.corecommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class HealCommand implements CommandExecutor{

	/*
	 * A command that restores the health of a specified online player.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("heal")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args.length == 0) {
					player.sendMessage(ChatColor.RED + "You need to specify a player!");
				}else if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					
					if(target != null) {
						
						if(target.getHealth() < 20D) {
							target.setHealth(20D);
							target.sendMessage(ChatColor.GOLD + "Your health has been restored!");
							
							return true;
							
						}else {
							target.sendMessage(ChatColor.GOLD + "Your health is full!");
						}
						
					}else {
						player.sendMessage(ChatColor.RED + "This player does not exist!");
					}
				
				}else {
					
					for(int i = 0; i < args.length; i++) {
						Player target = Bukkit.getPlayer(args[i]);
						
						if(target == null) {
							player.sendMessage(ChatColor.RED + "Player "
									+ args[0] + "does not exist!");
						
						}else {
							
							if(target.getHealth() < 20D) {
								target.setHealth(20D);
								target.sendMessage(ChatColor.GOLD + "Your health has been restored!");
								
								player.sendMessage("You have healed player " + target.getName() + "!");
								
								return true;
							
							}else {
								target.sendMessage(ChatColor.GOLD + "Your health is full!");
							}
						}
					}
				}
			}
		}
		
		return false;
	}

	
}
