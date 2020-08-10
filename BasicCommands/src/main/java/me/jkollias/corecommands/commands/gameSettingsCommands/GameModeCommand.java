package me.jkollias.corecommands.commands.gameSettingsCommands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class GameModeCommand implements CommandExecutor{

	/*
	 * Changes the game mode of a specified online player.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("gameMode")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if(args.length == 0) {
					player.sendMessage(ChatColor.RED + "You need to specify a game mode!");
					
				}else if(args.length == 1) {
					
					if(args[0].equalsIgnoreCase("Survival")) {
						
						if(player.getGameMode().equals(GameMode.SURVIVAL)) {
							player.sendMessage("Game mode is " + args[0] + ".");
							
						}else {
							player.setGameMode(GameMode.SURVIVAL);
							player.sendMessage("Game mode has been set to " + args[0].toUpperCase() + ".");
							
							return true;
						}
					}
					
					if(args[0].equalsIgnoreCase("Creative")) {
						
						if(player.getGameMode().equals(GameMode.CREATIVE)) {
							player.sendMessage("Game mode is " + args[0] + ".");
							
						}else {
							player.setGameMode(GameMode.CREATIVE);
							player.sendMessage("Game mode has been set to " + args[0].toUpperCase() + ".");
							
							return true;
						}
					}
					
					if(args[0].equalsIgnoreCase("Adventure")) {
						
						if(player.getGameMode().equals(GameMode.ADVENTURE)) {
							player.sendMessage("Game mode is " + args[0].toUpperCase() + ".");
							
						}else {
							player.setGameMode(GameMode.ADVENTURE);
							player.sendMessage("Game mode has been set to " + args[0].toUpperCase() + ".");
							
							return true;
						}
					}
					
					if(args[0].equalsIgnoreCase("Spectator")) {
						
						if(player.getGameMode().equals(GameMode.SPECTATOR)) {
							
							if(player.getGameMode().equals(GameMode.SPECTATOR)) {
								player.sendMessage("Game mode is " + args[0].toUpperCase() + ".");
								
							}else {
								player.setGameMode(GameMode.SPECTATOR);
								player.sendMessage("Game mode has been set to" + args[0].toUpperCase() + ".");
								
								return true;
							}
						}
					}
					
				}else {
					player.sendMessage(ChatColor.RED + "You cannot specify more than one game modes!");
				}
			}
		}
		
		return false;
	}

}
