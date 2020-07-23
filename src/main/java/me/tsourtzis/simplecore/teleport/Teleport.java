package me.tsourtzis.simplecore.teleport;

import lombok.Getter;
import lombok.Setter;
import me.tsourtzis.simplecore.player.MyPlayer;

public class Teleport {
	
	@Getter private MyPlayer sender;
	@Getter private MyPlayer target;
	
	@Getter @Setter private TeleportState state;
	
	public Teleport(MyPlayer sender, MyPlayer target) {
		this.sender = sender;
		this.target = target;
		this.state = TeleportState.NOT_COMMENCED;
	}

	public void commence() {
		if(sender.isOp()) {
			sender.getPlayer().teleport(target.getPlayer().getLocation());
			setState(TeleportState.OP_BYPASS);
		}else {
			if(target.isBlockingTeleports()) {
				setState(TeleportState.TARGET_BLOCKING);
			}else {
				sender.getPlayer().teleport(target.getPlayer().getLocation());
				setState(TeleportState.COMMENCED);
			}
		}
	}
}
