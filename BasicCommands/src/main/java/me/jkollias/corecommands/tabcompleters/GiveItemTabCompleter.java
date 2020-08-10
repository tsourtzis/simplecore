package me.jkollias.corecommands.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class GiveItemTabCompleter implements TabCompleter{

	/*
	 * Returns a list of online player names in the first argument position.
	 * In the second argument position returs a list of all the materials.
	 * Finally, in the third and final argument position returns the word amount,
	 * that tells the player to enter the amount of items to give.
	 */
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("giveItem")) {
			
			if(args.length == 1) {
				List<String> nameList = new ArrayList<String>();
				
				for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
					nameList.add(onlinePlayer.getName());
				}
				
				return nameList;
			}
			
			if(args.length == 2) {
				List<String> itemList = new ArrayList<String>();
				
				for(Material material : Material.values()) {
					itemList.add(material.toString());
				}
				
				return itemList;
			}
			
			if(args.length == 3) {
				List<String> amountList = new ArrayList<String>();
				
				amountList.add("<amount>");
				
				return amountList;
			}
			
		}
		
		return null;
	}

}
