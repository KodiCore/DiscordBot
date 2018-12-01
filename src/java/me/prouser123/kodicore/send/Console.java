package me.prouser123.kodicore.send;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import me.prouser123.kodi.discordbot.Main;

public class Console {
	
	public static Plugin plugin = null; // Plugin instance
	private static String prefix; 
	
	public Console(Plugin plugin) {
		Console.plugin = plugin;
		Console.prefix = "[" + plugin.getName() + " " + Main.kodiCoreVersion + "] ";
	}
	
	/**
	 * Log an INFO Message to the Console
	 * Displays as: [KodiCore (version)] message
	 * 
	 * @param message message
	 */
	public static void info(String message) {
		Bukkit.getLogger().info(prefix + message);
	}
	
	/**
	 * Log a WARNING Message to the Console
	 * @param message message
	 */
	public static void warning(String message) {
		Bukkit.getLogger().warning(prefix + message);
	}

	/**
	 * Log a SEVERE Message to the Console
	 * @param message message
	 */
	public static void severe(String message) {
		Bukkit.getLogger().severe(prefix + message);
	}
	
}