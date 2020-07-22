package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("spawn")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args.length == 0) {
					player.teleport(new Location(player.getWorld(), -173D, 64D, 10D, 90F, 0F));
				}else {
					player.sendMessage(ChatColor.GRAY + "Too many arguments.");
				}
			}else{
				
			}
		}
		
		return false;
	}

	
}
