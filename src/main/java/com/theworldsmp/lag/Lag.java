package com.theworldsmp.lag;

import org.bukkit.plugin.java.JavaPlugin;

import com.theworldsmp.lag.commands.LagCommand;

public class Lag extends JavaPlugin {

	public static Lag instance;

	@Override
	public void onEnable() {
		instance = this;

		getCommand("lag").setExecutor(new LagCommand(this));
	}

	@Override
	public void onDisable() {
		instance = null;
	}

}
