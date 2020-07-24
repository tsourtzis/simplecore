package me.tsourtzis.simplecore.main;

import java.util.Arrays;

import org.bukkit.plugin.java.JavaPlugin;

import me.tsourtzis.simplecore.commandexecutors.FeedCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.HealCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.IgniteCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.KillCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.SmiteCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.TeleportBlockCommandExecutor;
import me.tsourtzis.simplecore.commandexecutors.TeleportCommandExecutor;
import me.tsourtzis.simplecore.listeners.AsyncPlayerChatListener;
import me.tsourtzis.simplecore.listeners.ServerListPingListener;
import me.tsourtzis.simplecore.tabcompleters.FeedCommandTabCompleter;
import me.tsourtzis.simplecore.tabcompleters.HealCommandTabCompleter;
import me.tsourtzis.simplecore.tabcompleters.KillCommandTabCompleter;
import me.tsourtzis.simplecore.tabcompleters.SmiteCommandTabCompleter;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		if(!(this.getDataFolder().exists())) {
			this.getLogger().info("Generating default configuration...");
			this.getDataFolder().mkdir();
			this.saveDefaultConfig();
			this.getLogger().info("Default configuration generated.");
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
		this.getCommand("smite").setExecutor(new SmiteCommandExecutor());
		this.getCommand("ignite").setExecutor(new IgniteCommandExecutor());
		this.getCommand("teleport").setExecutor(new TeleportCommandExecutor());
		this.getCommand("teleportblock").setExecutor(new TeleportBlockCommandExecutor());
	}
	
	public void setCommandAliases() {
		this.getCommand("teleport").setAliases(Arrays.asList("tp"));
		this.getCommand("teleportblock").setAliases(Arrays.asList("tpblock", "tpb"));
	}
	
	public void setTabCompleters() {
		this.getCommand("heal").setTabCompleter(new HealCommandTabCompleter());
		this.getCommand("kill").setTabCompleter(new KillCommandTabCompleter());
		this.getCommand("feed").setTabCompleter(new FeedCommandTabCompleter());
		this.getCommand("smite").setTabCompleter(new SmiteCommandTabCompleter());
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new ServerListPingListener(this), this);
		this.getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(this), this);
	}
}
