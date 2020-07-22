package me.tsourtzis.simplecore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener{

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		event.setFormat(ChatColor.WHITE + event.getPlayer().getName() + ChatColor.GRAY + " : " + ChatColor.WHITE + event.getMessage().replace("%", "%%"));
	}
}
