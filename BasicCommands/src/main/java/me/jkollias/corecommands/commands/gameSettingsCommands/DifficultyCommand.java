package me.jkollias.corecommands.commands.gameSettingsCommands;

import org.bukkit.Difficulty;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class DifficultyCommand implements CommandExecutor{

	/*
	 * Changes the difficulty of the game.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("difficulty")) {
			
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args.length == 0) {
					player.sendMessage(ChatColor.RED + "You need to specify the difficulty you want to set.");
					
				}else if (args.length == 1) {
					
					if(args[0].equalsIgnoreCase("Peaceful")) {
						
						if(player.getWorld().getDifficulty().equals(Difficulty.PEACEFUL)) {
							player.sendMessage("Difficulty is " + args[0] + ".");
							
						}else {
							player.getWorld().setDifficulty(Difficulty.PEACEFUL);
							player.sendMessage("Difficulty has been set to " + args[0] + ".");
							
							return true;
							
						}
					}
					
					if(args[0].equalsIgnoreCase("Easy")) {
						
						if(player.getWorld().getDifficulty().equals(Difficulty.EASY)) {
							player.sendMessage("Difficulty is " + args[0] + ".");
							
						}else {
							player.getWorld().setDifficulty(Difficulty.EASY);
							player.sendMessage("Difficulty has been set to " + args[0] + ".");
							
							return true;
							
						}
					}
					
					if(args[0].equalsIgnoreCase("Normal")) {
						
						if(player.getWorld().getDifficulty().equals(Difficulty.NORMAL)) {
							player.sendMessage("Difficulty is " + args[0] + ".");
							
						}else {
							player.getWorld().setDifficulty(Difficulty.NORMAL);
							player.sendMessage("Difficulty has been set to " + args[0] + ".");
							
							return true;
						}
					}
					
					if(args[0].equalsIgnoreCase("Hard")) {
						
						if(player.getWorld().getDifficulty().equals(Difficulty.HARD)) {
							player.sendMessage("Difficulty is " + args[0] + ".");
						
						}else {
							player.getWorld().setDifficulty(Difficulty.HARD);
							player.sendMessage("Difficulty has been set to " + args[0] + ".");
							
							return true;
						}
					}
					
				}else {
					player.sendMessage(ChatColor.RED + "You need to specify only one difficulty!");
				}
			}
		}
		
		return false;
	}

}
