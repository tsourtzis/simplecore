package me.jkollias.corecommands.commands.gameSettingsCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeTimeOfDayCommand implements CommandExecutor{

	/*
	 * Changes the time of the world.
	 * For example sets the time to night.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("time")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("set")) {
						
						if(args[1].equalsIgnoreCase("sunrise")) {
							player.getWorld().setTime(23000);
							player.sendMessage("Game time has been changed to Sunrise.");
							
							return true;
						}else if(args[1].equalsIgnoreCase("day")) {
							player.getWorld().setTime(1000);
							player.sendMessage("Game time has been changed to Day.");
							
							return true;
						}else if(args[1].equalsIgnoreCase("noon")){
							player.getWorld().setTime(6000);
							player.sendMessage("Game time has been changed to Noon.");
							
							return true;
						}else if(args[1].equalsIgnoreCase("sunset")) {
							player.getWorld().setTime(12000);
							player.sendMessage("Game time has been changed to Sunset");
							
							return true;
						}else if(args[1].equalsIgnoreCase("night")) {
							player.getWorld().setTime(13000);
							player.sendMessage("Game time has been changed to Night.");
							
							return true;
						}else if(args[1].equalsIgnoreCase("midnight")) {
							player.getWorld().setTime(18000);
							player.sendMessage("Game time has been changed to Midnight.");
							
							return true;
						}else {
							player.sendMessage(ChatColor.RED
									+ "You need to specify a valid day time!");
						}
						
					}
				}
			}
		}
		
		return false;
	}

}
