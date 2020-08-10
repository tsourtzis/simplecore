package me.jkollias.corecommands.commands.administrationCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveOperatorRightsCommand implements CommandExecutor{

	/*
	 * Removes the rights of a server of operator
	 * from a specified player.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("removeOp")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					
					if(target != null) {
						if(target.isOp()) {
							target.setOp(false);
							target.sendMessage("You are not an operator anymore!");
							player.sendMessage("Player " + target.getName() 
							+ " is not an operator anymore!");
							
							return true;
						}
					}
				}
			}
			
		}
		
		return false;
	}

}
