package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tsourtzis.simplecore.player.MyPlayer;
import me.tsourtzis.simplecore.teleport.TeleportState;

public class TeleportCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("teleport")) {
			if(sender instanceof Player) {
				MyPlayer cmdSender = MyPlayer.getPlayerFromCommandSender(sender);
				
				if(args.length == 0) {
					if(!(cmdSender.hasPermission("simplecore.teleport"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						cmdSender.sendMessage("Usage: " + "/teleport <player> [anotherPlayer]" + ".");
					}
				}else if(args.length == 1) {
					if(!(cmdSender.hasPermission("simplecore.teleport.self"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						MyPlayer target = MyPlayer.getPlayerFromString(args[0]);
						
						TeleportState state = cmdSender.teleport(target);
						
						if(state == TeleportState.TARGET_BLOCKING) {
							cmdSender.sendMessage(target.getName() + " is blocking teleports.");
						}else if(state == TeleportState.COMMENCED) {
							cmdSender.sendMessage("You have been teleported to " + target.getName() + ".");
						}else if(state == TeleportState.OP_BYPASS){
							cmdSender.sendMessage("You have been teleported to " + target.getName() + ".");
						}else {
							cmdSender.sendMessage("Something went wrong.");
						}
						
						state = null;
						
						target = null;
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
