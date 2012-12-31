package org.zonedabone.commandsigns.utils;

import org.bukkit.configuration.Configuration;
import org.zonedabone.commandsigns.CommandSigns;

public class Config extends ConfigStore {

	public Config(CommandSigns plugin) {
		super(plugin);
	}
	
	public void load() {
		Configuration config = YamlLoader.loadResource(plugin, "config.yml");

		for (String k : config.getKeys(true)) {
			if (config.isString(k)) {
				this.put(k, config.getString(k));
			}
		}
		plugin.getLogger().info("Config file loaded.");
	}
	
}
