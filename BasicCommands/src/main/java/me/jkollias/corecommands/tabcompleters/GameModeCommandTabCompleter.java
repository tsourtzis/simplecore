package me.jkollias.corecommands.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class GameModeCommandTabCompleter implements TabCompleter{

	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("gameMode")) {
			
			List<String> gameModeList = new ArrayList<String>();
			
			if(args.length == 1) {
				
				for(GameMode gameMode : GameMode.values()) {
					gameModeList.add(gameMode.toString());
				}
				
				return gameModeList;
			}
			
		}
		
		return null;
	}

}
