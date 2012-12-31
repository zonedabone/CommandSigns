package org.zonedabone.commandsigns.handlers;

import org.bukkit.ChatColor;
import org.zonedabone.commandsigns.SignExecutor;
import org.zonedabone.commandsigns.CommandSigns;

public class GroupHandler extends Handler {

	@Override
	public void handle(SignExecutor e, String command, boolean silent,
			boolean negate) {
		if (e.getPlayer() != null && CommandSigns.permission != null
				&& CommandSigns.permission.isEnabled()
				&& command.startsWith("@")) {
			boolean allowed = false;
			for (String s : command.substring(1).split(",")) {
				allowed = allowed
						|| CommandSigns.permission.playerInGroup(e.getPlayer(),
								s);
			}
			if (allowed ^ negate) {
				e.getRestrictions().push(true);
			} else {
				e.getRestrictions().push(false);
				if (!silent)
					e.getPlayer()
							.sendMessage(
									ChatColor.RED
											+ "You are not in the required group to run this command.");
			}
		}
	}

}
