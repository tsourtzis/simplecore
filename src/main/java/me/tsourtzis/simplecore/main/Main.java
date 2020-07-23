package me.tsourtzis.simplecore.main;

import java.util.Arrays;

import org.bukkit.plugin.java.JavaPlugin;

import me.tsourtzis.simplecore.commandexecutors.FeedCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.HealCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.KillCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.TeleportBlockCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.TeleportCommandExecutor;
import me.tsourtzis.simplecore.tabcompleters.FeedCommandTabCompleter;
import me.tsourtzis.simplecore.tabcompleters.HealCommandTabCompleter;
import me.tsourtzis.simplecore.tabcompleters.KillCommandTabCompleter;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		if(!(this.getDataFolder().exists())) {
			this.getDataFolder().mkdir();
			this.saveDefaultConfig();
		}
		
		this.registerEvents();
		this.setCommandExecutors();
		this.setTabCompleters();
		this.getLogger().info("Plugin is now enabled.");
	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("Plugin is now disabled.");
	}

	public void setCommandExecutors() {
		this.getCommand("heal").setExecutor(new HealCommandExecutor());
		this.getCommand("kill").setExecutor(new KillCommandExecutor());
		this.getCommand("feed").setExecutor(new FeedCommandExecutor());
		this.getCommand("teleport").setExecutor(new TeleportCommandExecutor());
		this.getCommand("teleportblock").setExecutor(new TeleportBlockCommandExecutor());
	}
	
	public void setCommandAliases() {
		this.getCommand("teleport").setAliases(Arrays.asList("tp"));
	}
	
	public void setTabCompleters() {
		this.getCommand("heal").setTabCompleter(new HealCommandTabCompleter());
		this.getCommand("kill").setTabCompleter(new KillCommandTabCompleter());
		this.getCommand("feed").setTabCompleter(new FeedCommandTabCompleter());
	}
	
	public void registerEvents() {
		
	}
}
