package me.prouser123.kodicore;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.prouser123.kodicore.Main;
import me.prouser123.kodicore.send.Console;

public class Utils {
	
	public static class Player {
		
		/**
		 * Return online players (List of usernames)
		 * 
		 * @return Array of online player names
		 */
		public static Collection<? extends org.bukkit.entity.Player> getOnlinePlayersByNames() {
			return Main.inst().getServer().getOnlinePlayers();
		}
		
		/**
		 * Return online players (amount of players as an int)
		 * 
		 * @return Int amount of online players
		 */
		public static int getOnlinePlayersByAmount() {
			return Main.inst().getServer().getOnlinePlayers().size();
		}
	}
	
	
	public static class Broadcast {
		
		/**
		 * Broadcast to permission (message, permission node) 
		 * 
		 * @param message Message to send
		 * @param permission Permission node
		 */
		public static void toPermission(String message, String permission) {
			Main.inst().getServer().broadcast(message, permission);
		}
		
		/**
		 * Broadcast to server (message)
		 * 
		 * @param message Message to send
		 */
		public static void global (String message) {
			Main.inst().getServer().broadcastMessage(message);
		}
	}
	
	
	public static class Config {
		
		/**
		 * Get a String from getConfig() (plugin config file) using path and storing pluginName for logging to the console
		 * 
		 * @param path String path in config to value
		 * @param getConfig getConfig() instance
		 * @param pluginName Name of plugin for logging
		 * @return String value from config
		 */
		// Get String
		public static String getString(String path, FileConfiguration getConfig, String pluginName) {
			// Store String
			String destination = getConfig.getString(path);

			// Log to console
			Console.info("[" + pluginName + "] [getConfig] Loading: " + path + " = " + destination);
			
			// Return String
			return destination;
		}
		
		/**
		 * Get an Int from getConfig() (plugin config file) using path and storing pluginName for logging to the console
		 * 
		 * @param path Int path in config to value
		 * @param getConfig getConfig() instance
		 * @param pluginName Name of plugin for logging
		 * @return Int value from config
		 */// Get Int
		public static int getInt(String path, FileConfiguration getConfig, String pluginName) {
			// Store Int
			int destination = getConfig.getInt(path);

			// Log to console
			Console.info("[" + pluginName + "] [getConfig] Loading: " + path + " = " + destination);
			
			// Return Int
			return destination;
		}
	}
	
	
	/**
	 * Get World (by name)
	 * @param world String
	 * @return world World
	 * @see org.bukkit.World
	 */
	public static World getWorld(String world){
		return Main.inst().getServer().getWorld(world);
	}
	
	/**
	 * Add item to inventory (using arguments)
	 * 
	 * @param inv Bukkit Inventory
	 * @see org.bukkit.inventory.Inventory
	 * 
	 * @param material Material
	 * @see org.bukkit.Material
	 * 
	 * @param displayName Item Name
	 * @param lore Item Lore
	 * @param position Item Position
	 */
		public static void addInventoryItem(Inventory inv, Material material, String displayName, String lore, int position) {
			// Create the ItemStack
			ItemStack stack = new ItemStack(material);

			// Get the item's meta
			ItemMeta meta = stack.getItemMeta();

			// Set the display name
			meta.setDisplayName(displayName);

			// Set the lore
			meta.setLore(Arrays.asList(lore));

			// Set the meta
			stack.setItemMeta(meta);
					
			// Add the item to the inventory
			inv.setItem(position, stack);
		}
		
		/**
		 * Run a command in the console.
		 * @param command command
		 */
		public static void runConsoleCommand(String command) {
			Bukkit.dispatchCommand(Main.inst().getServer().getConsoleSender(), command);
		}
}