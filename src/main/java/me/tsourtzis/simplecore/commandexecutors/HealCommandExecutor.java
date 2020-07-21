package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Check if the Command is /heal.
		if(command.getName().equalsIgnoreCase("heal")) {
			// Check if the CommandSender is a Player. 
			if(sender instanceof Player) {
				Player cmdSender = (Player) sender; // The Player that sent the command.
				
				if(args.length == 0) {
					// Check if the player has the simplecore.heal.self permission to execute the command, this allows to heal themselves.
					if(!(cmdSender.hasPermission("simplecore.heal.self"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to execute this command.");
					}else {
						// Check if the player is already at full health.
						if(cmdSender.getHealth() >= 20D) {
							cmdSender.sendMessage(ChatColor.GRAY + "You are already at full health.");
						}else {
							cmdSender.setHealth(20D);
							cmdSender.sendMessage(ChatColor.GRAY + "You have been healed.");
						}
					}
				}else if(args.length == 1) {
					// Check if the player has the simplecore.heal.other permission to execute the command, this allows to heal other players.
					if(!(cmdSender.hasPermission("simplecore.heal.other"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to execute this command.");
					}else {
						Player target = Bukkit.getPlayer(args[0]); // The target player, we get this from the command argument, args[0].
						
						// Check if the getPlayer() method returned a valid Player object.
						if(target == null) {
							cmdSender.sendMessage(ChatColor.WHITE + args[0] + ChatColor.GRAY + " is not an online player.");
						}else {
							// Now we know that we have a valid Player object.
							if(target.getHealth() >= 20D) {
								cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is already at full health.");
							}else if(target.getHealth() == 0D) {
								cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is not alive, thus they cannot be healed.");
							}else {
								target.setHealth(20D);
								target.sendMessage(ChatColor.GRAY + "You have been healed.");
								cmdSender.sendMessage(ChatColor.GRAY + "You healed " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + ".");
							}
						}
					}
				}else {
					cmdSender.sendMessage(ChatColor.GRAY + "Too many command arguments.");
				}
			}else {
				
			}
			
			return true;
		}
		
		return false;
	}

}
