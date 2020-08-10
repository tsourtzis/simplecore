package me.jkollias.corecommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor{

	/*
	 * A command that kicks a player from the server.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("kick")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args.length == 0) {
					player.sendMessage(ChatColor.RED + "You need to specify a player!");
					
				}else if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					
					if(target == null) {
						player.sendMessage(ChatColor.RED + "Targeted player "
								+ "does not exist!");
						
					}else {
						target.kickPlayer("You have been kicked from the server!");
						
						return true;
					}
					
				}else {
					
					for(int i = 0; i < args.length; i++) {
						Player target = Bukkit.getPlayer(args[i]);
						
						if(target == null) {
							player.sendMessage("Player " 
									+ args[i] + "does not exist!");
						
						}else {
							target.kickPlayer("You have been kicked from the server!");
							player.sendMessage("You have kicked player " + target.getName() + "!");
							
							return true;							
						}
					}
				}
				
			}
		}
		
		return false;
	}

}
