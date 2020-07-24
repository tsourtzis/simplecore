package me.jkollias.corecommands;

import org.bukkit.plugin.java.JavaPlugin;

import me.jkollias.corecommands.commands.DifficultyCommand;
import me.jkollias.corecommands.commands.FeedCommand;
import me.jkollias.corecommands.commands.GameModeCommand;
import me.jkollias.corecommands.commands.HealCommand;
import me.jkollias.corecommands.commands.KillCommand;
import me.jkollias.corecommands.commands.TeleportCommand;
import me.jkollias.corecommands.commands.TeleportToMeCommand;
import me.jkollias.corecommands.commands.TeleportToSpawnCommand;
import me.jkollias.corecommands.tabcompleters.DifficultyCommandTabCompleter;
import me.jkollias.corecommands.tabcompleters.GameModeCommandTabCompleter;
import me.jkollias.corecommands.tabcompleters.TabCompleterForPlayerNames;

public class MainCommands extends JavaPlugin{

	@Override
	public void onEnable() {
		this.setCommandExecutors();
		this.setCommandTabCompleters();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void setCommandExecutors() {
		
		this.getCommand("kill").setExecutor(new KillCommand());
		this.getCommand("heal").setExecutor(new HealCommand());
		this.getCommand("difficulty").setExecutor(new DifficultyCommand());
		this.getCommand("tp").setExecutor(new TeleportCommand());
		this.getCommand("spawn").setExecutor(new TeleportToSpawnCommand());
		this.getCommand("feed").setExecutor(new FeedCommand());
		this.getCommand("tptome").setExecutor(new TeleportToMeCommand());
		this.getCommand("gamemode").setExecutor(new GameModeCommand());
	
	}
	
	public void setCommandTabCompleters() {
		this.getCommand("difficulty").setTabCompleter(new DifficultyCommandTabCompleter());
		this.getCommand("gameMode").setTabCompleter(new GameModeCommandTabCompleter());
		this.getCommand("kill").setTabCompleter(new TabCompleterForPlayerNames());
		this.getCommand("heal").setTabCompleter(new TabCompleterForPlayerNames());
		this.getCommand("tp").setTabCompleter(new TabCompleterForPlayerNames());
		this.getCommand("feed").setTabCompleter(new TabCompleterForPlayerNames());
		this.getCommand("tptome").setTabCompleter(new TabCompleterForPlayerNames());
	}
	
}
