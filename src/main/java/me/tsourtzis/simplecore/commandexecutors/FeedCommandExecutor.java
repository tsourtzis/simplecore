package me.tsourtzis.simplecore.commandexecutors;

import org.bukkit.Bukkit;
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
				MyPlayer cmdSender = MyPlayer.getPlayerFromCommandSender(sender);
				
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
					if(args[0].equalsIgnoreCase("@all")) {
						MyPlayer currentPlayer = null;
						
						for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
							currentPlayer = new MyPlayer(onlinePlayer);
							
							if(!(currentPlayer.isSated()) && currentPlayer.isAlive()) {
								currentPlayer.sate();
								currentPlayer.sendMessage(ChatColor.GRAY + "Your hunger has been sated.");
							}
						}
						
						cmdSender.sendMessage(ChatColor.GRAY + "You sated the hunger of all hungry online players.");
						
						currentPlayer = null;
					}else if(args[0].equalsIgnoreCase("@all-excluding-self")) {
						MyPlayer currentPlayer = null;
						
						for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
							currentPlayer = new MyPlayer(onlinePlayer);
							
							if(!(currentPlayer.isSated()) && currentPlayer.isAlive() && !(currentPlayer.equals(cmdSender))) {
								currentPlayer.sate();
								currentPlayer.sendMessage(ChatColor.GRAY + "Your hunger has been sated.");
							}
						}
						
						cmdSender.sendMessage(ChatColor.GRAY + "You sated the hunger of all hungry online players, except your own.");
						
						currentPlayer = null;
					}else {
						MyPlayer target = MyPlayer.getPlayerFromString(args[0]);
						
						if(target == null) {
							cmdSender.sendMessage(ChatColor.WHITE + args[0] + ChatColor.GRAY + " is not online.");
						}else {
							if(!(target.isAlive())) {
								cmdSender.sendMessage(ChatColor.WHITE + target.getName() + ChatColor.GRAY + " is not alive, thus their hunger cannot be sated.");
							}else {
								if(target.isSated()) {
									cmdSender.sendMessage(target.getName().charAt(target.getName().length()-1) == 's' 
											? ChatColor.WHITE + target.getName() + ChatColor.GRAY + "' hunger is already sated." 
											: ChatColor.WHITE + target.getName() + ChatColor.GRAY + "'s hunger is already sated.");
								}else {
									target.sate();
									target.sendMessage(ChatColor.GRAY + "Your hunger has been sated.");
									cmdSender.sendMessage(target.getName().charAt(target.getName().length()-1) == 's' 
											? ChatColor.GRAY + "You sated " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + "' hunger." 
											: ChatColor.GRAY + "You sated " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + "'s hunger.");
								}
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
