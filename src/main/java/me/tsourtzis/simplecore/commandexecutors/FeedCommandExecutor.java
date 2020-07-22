package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tsourtzis.simplecore.player.MyPlayer;

public class FeedCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("feed")) {
			if(sender instanceof Player) {
				MyPlayer cmdSender = new MyPlayer(sender);
				
				if(args.length == 0) {
					if(!(cmdSender.hasPermission("simplecore.feed.self"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						if(cmdSender.isSated()) {
							cmdSender.sendMessage(ChatColor.GRAY + "Your hunger is already sated.");
						}else {
							cmdSender.sate();
							cmdSender.sendMessage(ChatColor.GRAY + "Your hunger has been sated.");
						}
					}
				}else if(args.length == 1) {
					if(!(cmdSender.hasPermission("simplecore.feed.other"))) {
						cmdSender.sendMessage(ChatColor.GRAY + "You do not have permission to perform this command.");
					}else {
						MyPlayer target = new MyPlayer(args[0]);
						
						if(!(target.exists())) {
							cmdSender.sendMessage(ChatColor.WHITE + args[0] + ChatColor.GRAY + " is not online.");
						}else {
							if(target.isSated()) {
								cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " hunger is already sated.");
							}else {
								target.sate();
								target.sendMessage(ChatColor.GRAY + "Your hunger has been sated.");
								cmdSender.sendMessage(ChatColor.GRAY + "You sated " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " hunger.");
							}
						}
						
						target = null;
					}
				}else {
					cmdSender.sendMessage(ChatColor.GRAY + "Too many command arguments.");
				}
				
				cmdSender = null;
			}else {
				
			}
			
			return true;
		}
		return false;
	}

}
