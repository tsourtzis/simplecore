package me.jkollias.corecommands.commands.teleportationCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class TeleportToSpawnCommand implements CommandExecutor {

	/*
	 * Teleports a player back to spawn location.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("Spawn")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if(args.length == 0) {
					player.teleport(player.getWorld().getSpawnLocation());
					player.sendMessage(ChatColor.AQUA + "You have been teleported back to spawn!");
					
					return true;
					
				}else {
					player.sendMessage(ChatColor.RED + "You do not have to specify anything!");
					
				}
			}
		}
		
		return false;
	}

}
