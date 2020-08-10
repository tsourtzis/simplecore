package me.jkollias.corecommands.commands.itemCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveItemCommand implements CommandExecutor{

	/*
	 * Gives an amount of a specified item to a specified online player.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("giveItem")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args.length == 3) {
					Player target = Bukkit.getPlayer(args[0]);
					
					if(target != null) {
						Material material = Material.getMaterial(args[1].toUpperCase());
						
						if(material != null) {
							ItemStack item = new ItemStack(material);
							int amount = 0;
							
							try {
								amount = Integer.parseInt(args[2]);
								
								if(amount > 0 && amount <= 64) {
									item.setAmount(amount);
									target.getInventory().addItem(item);
									
									return true;
								}
								
							}catch(NumberFormatException e) {
								throw new NumberFormatException("A parsing error occured!");
							}
						}else {
							player.sendMessage(ChatColor.RED + "This item does not exist!");
						}
					}else {
						player.sendMessage(ChatColor.RED + "This player does not exist!");
					}
				}
			}
		}
		
		return false;
	}

}
