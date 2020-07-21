package me.tsourtzis.simplecore.main;

import org.bukkit.plugin.java.JavaPlugin;

import me.tsourtzis.simplecore.commandexecutors.HealCommandExecutor;
import me.tsourtzis.simplecore.listeners.ServerListPingListener;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable(){
		this.saveDefaultConfig();
		this.getLogger().info("Registering events...");
		this.registerEvents();
		this.getLogger().info("Events registered.");
		this.getLogger().info("Setting command executors...");
		this.setCommandExecutors();
		this.getLogger().info("Command executors are now set.");
		this.getLogger().info("The plugin is now enabled.");
	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("The plugin is now disabled.");
	}
	
	public void setCommandExecutors() {
		this.getCommand("heal").setExecutor(new HealCommandExecutor());
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new ServerListPingListener(this), this);
	}
}
