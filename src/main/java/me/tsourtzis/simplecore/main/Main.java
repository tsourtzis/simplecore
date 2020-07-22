package me.tsourtzis.simplecore.main;

import java.util.Arrays;

import org.bukkit.plugin.java.JavaPlugin;

import me.tsourtzis.simplecore.commandexecutors.FeedCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.HealCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.KillCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.SpawnCommandExecutor;
import me.tsourtzis.simplecore.listeners.AsyncPlayerChatListener;
import me.tsourtzis.simplecore.listeners.ServerListPingListener;
import me.tsourtzis.simplecore.tabcompleters.HealCommandTabCompleter;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		if(!(this.getDataFolder().exists())) {
			this.getLogger().info("Generating default configuration...");
			this.getDataFolder().mkdir();
			this.saveDefaultConfig();
			this.getLogger().info("Default configuration is now generated.");
		}
		
		this.getLogger().info("Registering events...");
		this.registerEvents();
		this.getLogger().info("Events registered.");
		this.getLogger().info("Setting command executors...");
		this.setCommandExecutors();
		this.getLogger().info("Command executors are now set.");
		this.getLogger().info("Setting command aliases...");
		this.setCommandAliases();
		this.getLogger().info("Command aliases are now set.");
		this.getLogger().info("Setting tab completers...");
		this.setTabCompleters();
		this.getLogger().info("Tab completers are now set.");
		this.getLogger().info("The plugin is now enabled.");
	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("The plugin is now disabled.");
	}
	
	public void setCommandExecutors() {
		this.getCommand("heal").setExecutor(new HealCommandExecutor());
		this.getCommand("kill").setExecutor(new KillCommandExecutor());
		this.getCommand("feed").setExecutor(new FeedCommandExecutor());
		this.getCommand("spawn").setExecutor(new SpawnCommandExecutor());
	}
	
	public void setCommandAliases() {
		this.getCommand("feed").setAliases(Arrays.asList("sate"));
	}
	
	public void setTabCompleters() {
		this.getCommand("heal").setTabCompleter(new HealCommandTabCompleter());
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new ServerListPingListener(this), this);
		this.getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
	}
}
