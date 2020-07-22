package me.tsourtzis.simplecore.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class HealCommandTabCompleter implements TabCompleter{

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if(command.getName().equalsIgnoreCase("heal")) {
			if(args.length == 1) {
				List<String> onlinePlayers = new ArrayList<String>();
				
				for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
					onlinePlayers.add(onlinePlayer.getName());
				}
				
				return onlinePlayers;
			}
		}
		
		return null;
	}

}
