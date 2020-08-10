package me.jkollias.corecommands.commands.teleportationCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class TeleportCommand implements CommandExecutor{

	/*
	 * Teleports the command caster to a specified online player's location.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("Tp")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if(args.length == 0) {
					player.sendMessage(ChatColor.RED + "You need to specify a player!");
					
				}else if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					
					if(target == null) {
						player.sendMessage(ChatColor.RED + "This player does not exist!");
						
					}else {
						player.teleport(target.getLocation());
						
						return true;
						
					}
					
				}else {
					player.sendMessage(ChatColor.RED + "You can teleport to only one player at a time!");
				}
			}

		}
		
		return false;
	}

}
