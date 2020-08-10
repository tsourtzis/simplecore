package me.jkollias.corecommands.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabCompleterForPlayerNames implements TabCompleter {

	/*
	 * Returns a list of all online player names in the first argument postion.
	 */
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("kill")
				|| cmd.getName().equalsIgnoreCase("heal")
				|| cmd.getName().equalsIgnoreCase("feed")
				|| cmd.getName().equalsIgnoreCase("tp")
				|| cmd.getName().equalsIgnoreCase("tpToMe")
				|| cmd.getName().equalsIgnoreCase("kick")) {
			
			List<String> nameList = new ArrayList<String>();
			
			if(args.length == 1) {
				
				for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
					
					nameList.add(onlinePlayer.getName());
					
				}
				
				return nameList;
			}
			
		}
		
		return null;
		
	}

}
