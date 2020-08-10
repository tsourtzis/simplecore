package me.jkollias.corecommands;

import org.bukkit.plugin.java.JavaPlugin;

import me.jkollias.corecommands.commands.FeedCommand;
import me.jkollias.corecommands.commands.HealCommand;
import me.jkollias.corecommands.commands.KickCommand;
import me.jkollias.corecommands.commands.KillCommand;
import me.jkollias.corecommands.commands.administrationCommands.GiveOperatorRightsCommand;
import me.jkollias.corecommands.commands.administrationCommands.RemoveOperatorRightsCommand;
import me.jkollias.corecommands.commands.gameSettingsCommands.ChangeTimeOfDayCommand;
import me.jkollias.corecommands.commands.gameSettingsCommands.DifficultyCommand;
import me.jkollias.corecommands.commands.gameSettingsCommands.GameModeCommand;
import me.jkollias.corecommands.commands.itemCommands.GiveItemCommand;
import me.jkollias.corecommands.commands.teleportationCommands.TeleportCommand;
import me.jkollias.corecommands.commands.teleportationCommands.TeleportToMeCommand;
import me.jkollias.corecommands.commands.teleportationCommands.TeleportToSpawnCommand;
import me.jkollias.corecommands.tabcompleters.ChangeTimeOfDayCommandTabCompleter;
import me.jkollias.corecommands.tabcompleters.DifficultyCommandTabCompleter;
import me.jkollias.corecommands.tabcompleters.GameModeCommandTabCompleter;
import me.jkollias.corecommands.tabcompleters.GiveItemTabCompleter;
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
		this.getCommand("kick").setExecutor(new KickCommand());
		this.getCommand("op").setExecutor(new GiveOperatorRightsCommand());
		this.getCommand("removeOp").setExecutor(new RemoveOperatorRightsCommand());
		this.getCommand("time").setExecutor(new ChangeTimeOfDayCommand());
		this.getCommand("giveitem").setExecutor(new GiveItemCommand());
	}
	
	public void setCommandTabCompleters() {
		this.getCommand("difficulty").setTabCompleter(new DifficultyCommandTabCompleter());
		this.getCommand("gameMode").setTabCompleter(new GameModeCommandTabCompleter());
		this.getCommand("kill").setTabCompleter(new TabCompleterForPlayerNames());
		this.getCommand("heal").setTabCompleter(new TabCompleterForPlayerNames());
		this.getCommand("tp").setTabCompleter(new TabCompleterForPlayerNames());
		this.getCommand("feed").setTabCompleter(new TabCompleterForPlayerNames());
		this.getCommand("tptome").setTabCompleter(new TabCompleterForPlayerNames());
		this.getCommand("kick").setTabCompleter(new TabCompleterForPlayerNames());
		this.getCommand("op").setTabCompleter(new TabCompleterForPlayerNames());
		this.getCommand("removeOp").setTabCompleter(new TabCompleterForPlayerNames());
		this.getCommand("time").setTabCompleter(new ChangeTimeOfDayCommandTabCompleter());
		this.getCommand("giveItem").setTabCompleter(new GiveItemTabCompleter());
	}
	
}
