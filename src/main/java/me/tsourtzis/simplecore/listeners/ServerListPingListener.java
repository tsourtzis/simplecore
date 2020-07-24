package me.tsourtzis.simplecore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import lombok.Getter;
import me.tsourtzis.simplecore.main.Main;
import net.md_5.bungee.api.ChatColor;

public class ServerListPingListener implements Listener{

	@Getter private Main plugin;
	
	public ServerListPingListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onServerListPing(ServerListPingEvent event) {
		
		String motd = plugin.getConfig().getString("general.motd");
		
		motd.replace("%", "%%");
		
		event.setMotd(ChatColor.translateAlternateColorCodes('&', motd));
	}
}
