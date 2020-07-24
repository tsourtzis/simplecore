package me.jkollias.corecommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class KillCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equalsIgnoreCase("kill")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args.length == 0) {
					player.sendMessage(ChatColor.RED + "You need to specify a player!");
					
				}else if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					
					if(target != null) {
						
						if(target.isDead()) {
							player.sendMessage(ChatColor.RED + "You cannot kill an already dead player!");
						}else {
							target.setHealth(0D);
							target.sendMessage(ChatColor.AQUA + "You have been killed by " + player.getName() + "!");
							
							return true;
							
						}
						
					}else {
						player.sendMessage(ChatColor.RED + "This player does not exist!");
					}
					
				}else {
					player.sendMessage(ChatColor.RED + "You can kill only one player at a time!");
				}
			}
		}
		
		return false;
	}
	
}
