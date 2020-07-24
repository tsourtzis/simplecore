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
					if(!(cmdSender.hasPermission("simplecore.teleport.self"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						cmdSender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.WHITE + "/teleport <player> [anotherPlayer]" + ChatColor.GRAY + ".");
					}
				}else if(args.length == 1) {
					if(!(cmdSender.hasPermission("simplecore.teleport.self"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						MyPlayer target = MyPlayer.getPlayerFromString(args[0]);
						
						if(target == null) {
							cmdSender.sendMessage(ChatColor.WHITE + args[0] + ChatColor.GRAY + " is not online.");
						}else {
							if(cmdSender.equals(target)) {
								cmdSender.sendMessage(ChatColor.GRAY + "You cannot teleport to yourself.");
							}else {
								TeleportState state = cmdSender.teleport(target);
								
								if(state == TeleportState.TARGET_BLOCKING) {
									cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is blocking teleports.");
								}else if(state == TeleportState.COMMENCED) {
									cmdSender.sendMessage(ChatColor.GRAY + "You have been teleported to " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + ".");
								}else if(state == TeleportState.OP_BYPASS){
									cmdSender.sendMessage(ChatColor.GRAY + "You have been teleported to " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + ".");
								}else {
									cmdSender.sendMessage(ChatColor.GRAY + "Looks like the teleportation could not commence successfully.");
								}
								
								state = null;
							}
						}
						
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
