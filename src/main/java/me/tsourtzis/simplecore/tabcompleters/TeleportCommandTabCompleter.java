package me.tsourtzis.simplecore.tabcompleters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TeleportCommandTabCompleter implements TabCompleter{

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if(command.getName().equalsIgnoreCase("teleport")) {
			if(sender instanceof Player) {
				if(args.length == 1) {
					List<String> tabList = new ArrayList<String>();
					
					for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
						tabList.add(onlinePlayer.getName());
					}
					
					return tabList;
				}else if(args.length > 1) {
					return Arrays.asList("");
				}
			}else {
				
			}
		}
		
		return null;
	}

}
