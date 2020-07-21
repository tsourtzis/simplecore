package me.tsourtzis.simplecore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import me.tsourtzis.simplecore.main.Main;

public class ServerListPingListener implements Listener {
	
	private final Main plugin;
	
	public Main getPlugin() {
		return plugin;
	}
	
	public ServerListPingListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onServerListPing(ServerListPingEvent event) {
		
	}

}
