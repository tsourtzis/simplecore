package me.tsourtzis.simplecore.player;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class MyPlayer {
	
	@Getter private Player player;
	
	public MyPlayer(CommandSender sender) {
		if(sender instanceof Player) {
			this.player = (Player) sender;
		}else {
			this.player = null;
		}
	}
	
	public MyPlayer(String name) {
		this.player = Bukkit.getPlayer(name);
	}
	
	public boolean isHealthy() {
		if(player.getHealth() >= 20D) {
			return true;
		}
		
		return false;
	}
	
	public void heal() {
		player.setHealth(20D);
	}
	
	public boolean isAlive() {
		if(player.getHealth() > 0D) {
			return true;
		}
		
		return false;
	}
	
	public void kill() {
		player.damage(player.getHealth());
	}
	
	public boolean exists() {
		if(player != null) {
			return true;
		}
		
		return false;
	}
	
	public boolean isSated() {
		if(player.getFoodLevel() >= 20) {
			return true;
		}
		
		return false;
	}
	
	public void sate() {
		player.setFoodLevel(20);
		player.setSaturation(20F);
		player.setExhaustion(0F);
	}
	
	public void smite() {
		player.getWorld().strikeLightning(player.getLocation());
	}
	
	public String getName() {
		return player.getName();
	}

	public boolean hasPermission(String permission) {
		if(player.hasPermission(permission)) {
			return true;
		}
		
		return false;
	}
	
	public void sendMessage(String message) {
		player.sendMessage(message);
	}
}
