package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tsourtzis.simplecore.player.MyPlayer;

public class TeleportBlockCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("teleportblock")) {
			if(sender instanceof Player) {
				MyPlayer cmdSender = MyPlayer.getPlayerFromCommandSender(sender);
				
				if(args.length == 0) {
					cmdSender.blockTeleports();
					
					if(cmdSender.isBlockingTeleports()) {
						cmdSender.sendMessage("You are now blocking teleports.");
					}else {
						cmdSender.sendMessage("You are no longer blocking teleports.");
					}
				}else {
					
				}
				
				cmdSender = null;
			}else {
				
			}
			
			return true;
		}
		
		return false;
	}
	
	
}
