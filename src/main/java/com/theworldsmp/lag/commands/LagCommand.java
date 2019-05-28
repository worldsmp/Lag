package com.theworldsmp.lag.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.theworldsmp.lag.Lag;

public class LagCommand implements CommandExecutor {

	@SuppressWarnings("unused")
	private final Lag lag;

	public LagCommand(Lag lag) {
		this.lag = lag;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		final Player player = (Player) sender;
		final double[] tps = Bukkit.getServer().getTPS();

		if (player.hasPermission("worldsmp.commands.lag")) {

			player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Lag info:");

			if (tps[0] <= 16) {
				player.sendMessage(ChatColor.AQUA + "Current TPS: " + ChatColor.RED + String.format("%.2f", tps[0]) + " (LOW)");
			} else if (tps[0] <= 18) {
				player.sendMessage(ChatColor.AQUA + "Current TPS: " + ChatColor.YELLOW + String.format("%.2f", tps[0]) + " (MEDIUM)");
			} else {
				player.sendMessage(ChatColor.AQUA + "Current TPS: " + ChatColor.DARK_GREEN + String.format("%.2f", tps[0]) + " (HIGH)");
			}

			player.sendMessage(ChatColor.AQUA + "Max Memory: " + ChatColor.RED + Runtime.getRuntime().maxMemory() / 1024 / 1024 + " MB");
			player.sendMessage(ChatColor.AQUA + "Free Memory: " + ChatColor.GREEN + Runtime.getRuntime().freeMemory() / 1024 / 1024 + " MB");

			player.sendMessage(" ");
			player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Entity Info:");
			final List<World> worlds = Bukkit.getWorlds();

			for (final World w : worlds) {
				player.sendMessage("" + ChatColor.AQUA + w.getName() + ": " + ChatColor.DARK_AQUA + w.getLoadedChunks().length + ChatColor.AQUA + " chunks, " + ChatColor.DARK_AQUA + w.getEntities().size() + ChatColor.AQUA + " entities.");
			}

		} else {
			player.sendMessage(ChatColor.RED + "No permission.");
		}

		return false;
	}

}
