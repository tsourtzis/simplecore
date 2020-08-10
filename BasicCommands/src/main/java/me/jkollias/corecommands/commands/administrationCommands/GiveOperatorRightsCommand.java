package me.jkollias.corecommands.commands.administrationCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveOperatorRightsCommand implements CommandExecutor {

	/*
	 * Makes a specified online player a server operator.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("op")) {
			
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					
					if(target != null) {
						if(!target.isOp()) {
							target.setOp(true);
							target.sendMessage("You are now a server operator!");
							player.sendMessage("Player " + target.getName()
							+ " is now an operator!");
							
							return true;
						}
					}
				}
			}
			
		}
		
		return false;
	}

}
