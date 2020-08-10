package me.jkollias.corecommands.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class ChangeTimeOfDayCommandTabCompleter implements TabCompleter{

	/*
	 * Returns a list of methods for the specified command in the first argument position.
	 * In the second argument position, returns a list of all available daytime cycles.
	 */
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("time")) {
			List<String> methodList;
			List<String> timeList;
			
			if(args.length == 1) {
				methodList = new ArrayList<String>();
				
				methodList.add("set");
			
				return methodList;
			}
			
			if(args.length == 2) {
				timeList = new ArrayList<String>();
				
				timeList.add("sunrise");
				timeList.add("day");
				timeList.add("noon");
				timeList.add("sunset");
				timeList.add("night");
				timeList.add("midnight");
				
				return timeList;
			}
		}
		
		return null;
	}
	
}
