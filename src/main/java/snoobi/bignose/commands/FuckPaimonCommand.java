package snoobi.bignose.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.server.packet.send.PacketOpenStateChangeNotify;
import java.util.List;

@Command(label = "paimonbarrier", usage = "/pb [on/off/1/0]\n\ndescription: removes paimon barrier at will", aliases = {"pb","paimonb"}, permission = "player.spawn", permissionTargeted = "player.spawn.others")
public final class FuckPaimonCommand implements CommandHandler {
	
	
	@Override
	public void execute(Player sender, Player targetPlayer, List<String> args) {
		String state = "";
		
		if (targetPlayer == null) {
			targetPlayer = sender;
		}
		
		switch (args.size()) {
            case 1:
                state = args.get(0);
				break;
            default:
                CommandHandler.sendMessage(sender,"since u are too fking dumb to state a state, lemme just be so nice to turn paimon off for u");
				state = "off";
        }
		
		if (state.equals("on") || state.equals("1")) {
			targetPlayer.sendPacket(new PacketOpenStateChangeNotify(48,0));
			CommandHandler.sendMessage(sender,"paimon added back coz fk u");
		} else if (state.equals("off") || state.equals("0")) {
			targetPlayer.sendPacket(new PacketOpenStateChangeNotify(48,1));
			CommandHandler.sendMessage(sender,"paimon removed");
		} else {
			targetPlayer.sendPacket(new PacketOpenStateChangeNotify(48,1));
			CommandHandler.sendMessage(sender,"since u are too fking dumb to state a state that is in the 4 above, lemme just be so nice to turn paimon off for u");
        }
	}
}
