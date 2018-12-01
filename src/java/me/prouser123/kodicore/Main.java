package me.prouser123.kodicore;

import org.bukkit.plugin.java.JavaPlugin;

import me.prouser123.kodicore.send.Console;

public class Main extends JavaPlugin {
	
	/**
	 * A variable with the plugin [KodiCore] version.
	 */
    public static String version;

	// Instancing
	private static Main instance;
	
	/**
	 * Instance of Main for accessing JavaPlugin from other classes.
     * @see org.bukkit.plugin.java.JavaPlugin
     * @return instance Main
	 */
    public static Main inst() {
    	  return instance;
    }
    
    /**
     * This will run when the plugin has been enabled.
     * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
     */
    // On Enable
	@Override
	public void onEnable() {
		// Instancing
		instance = this;
		// Get version
		version = getDescription().getVersion();
		// Create a instance of Console
		new Console(this);
		// Create an instance of Discord
		//new Discord("null");
		// Send a message
		Console.info("Welcome to KodiCore! Initializing...");
	}
}