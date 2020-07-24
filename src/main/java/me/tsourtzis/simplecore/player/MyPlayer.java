package me.tsourtzis.simplecore.player;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import me.tsourtzis.simplecore.teleport.Teleport;
import me.tsourtzis.simplecore.teleport.TeleportState;

@EqualsAndHashCode
public class MyPlayer {
	
	static final int TICKS_IN_SECOND = 20;
	static final int FIRE_SECONDS = 60;
	
	// Holds the player UUIDs that are blocking forced teleports, this is reset on server restart.
	static HashSet<UUID> tpBlock = new HashSet<UUID>();

	@Getter private Player player;
	
	public static MyPlayer getPlayerFromCommandSender(CommandSender sender) {
		if(sender instanceof Player) {
			return new MyPlayer((Player) sender);
		}else {
			return null;
		}
	}
	
	public static MyPlayer getPlayerFromString(String str) {
		Player player = Bukkit.getPlayer(str);
		
		if(player == null) {
			return null;
		}else {
			return new MyPlayer(player);
		}
	}
	
	public static MyPlayer getPlayerFromUUID(UUID uuid) {
		Player player = Bukkit.getPlayer(uuid);
		
		if(player == null) {
			return null;
		}else {
			return new MyPlayer(player);
		}
	}
	
	public MyPlayer(Player player) {
		this.player = player;
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
	
	public boolean isHealthy() {
		if(player.getHealth() >= 20D) {
			return true;
		}
		
		return false;
	}
	
	public boolean isAlive() {
		if(player.getHealth() > 0) {
			return true;
		}
		
		return false;
	}
	
	public void heal() {
		player.setHealth(20D);
	}
	
	public void kill() {
		player.setHealth(0D);
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
	
	public TeleportState teleport(MyPlayer target) {
		Teleport tp = new Teleport(this, target);
		
		tp.commence();
		
		return tp.getState();
	}
	
	public boolean isBlockingTeleports() {
		if(tpBlock.contains(this.getPlayer().getUniqueId())) {
			return true;
		}
		
		return false;
	}
	
	public void blockTeleports() {
		if(isBlockingTeleports()) {
			tpBlock.remove(this.getPlayer().getUniqueId());
		}else {
			tpBlock.add(this.getPlayer().getUniqueId());
		}
	}
	
	public boolean isOp() {
		if(player.isOp()) {
			return true;
		}
		
		return false;
	}
	
	public boolean isIgnited() {
		if(player.getFireTicks() > 0) {
			return true;
		}
		
		return false;
	}
	
	public void ignite() {
		player.setFireTicks(TICKS_IN_SECOND * FIRE_SECONDS);
	}
}
