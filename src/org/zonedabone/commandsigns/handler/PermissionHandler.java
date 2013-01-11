package org.zonedabone.commandsigns.handler;

import org.zonedabone.commandsigns.SignExecutor;
import org.zonedabone.commandsigns.CommandSigns;

public class PermissionHandler extends Handler {

	@Override
	public void handle(SignExecutor e, String command, boolean silent,
			boolean negate) {
		plugin = e.getPlugin();
		if (e.getPlayer() != null && CommandSigns.permission != null
				&& CommandSigns.permission.isEnabled()
				&& command.startsWith("&")) {
			boolean allowed = false;
			for (String s : command.substring(1).split(",")) {
				allowed = allowed
						|| e.getPlugin().hasPermission(e.getPlayer(), s);
			}
			if (allowed ^ negate) {
				e.getRestrictions().push(true);
			} else {
				e.getRestrictions().push(false);
				if (!silent)
					plugin.messenger.sendMessage(e.getPlayer(),
							"restriction.not_permitted");
			}
		}
	}

}