package me.tsourtzis.simplecore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import lombok.Getter;
import me.tsourtzis.simplecore.main.Main;
import net.md_5.bungee.api.ChatColor;

public class AsyncPlayerChatListener implements Listener{

	@Getter private Main plugin;

	public AsyncPlayerChatListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		event.setFormat(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("general.chat-format")
				.replace("[player]", event.getPlayer().getName())
				.replace("[message]", event.getMessage())
				.replace("%", "%%")));
	}
}
