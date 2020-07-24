package me.jkollias.corecommands.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Difficulty;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class DifficultyCommandTabCompleter implements TabCompleter{

	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("difficulty")) {
			
			List<String> difficultyList = new ArrayList<String>();
			
			if(args.length == 1) {
				
				for(Difficulty difficulty : Difficulty.values()) {
					difficultyList.add(difficulty.toString());
				}
				
				return difficultyList;
				
			}
			
		}
		
		return null;
	}

}
