package me.prouser123.kodi.discordbot;

import org.bukkit.plugin.java.JavaPlugin;

import me.prouser123.kodicore.discord.Discord;
import me.prouser123.kodicore.discord.commands.CopyOwnerAvatar;
import me.prouser123.kodicore.discord.commands.ServerInfo;
import me.prouser123.kodicore.send.Console;

import java.lang.IllegalStateException;

public class Main extends JavaPlugin {
	

	// Instancing
	private static Main instance;
	public static String kodiCoreVersion;
	
    // On Enable
	@Override
	public void onEnable() {

		// Instancing
		instance = this;
		// Get version
		kodiCoreVersion = "0.2.1.1-SMD";
		// Create a instance of Console
		new Console(this);
		// Create an instance of Discord
		//new Discord("null");
		// Send a message
		Console.info("Welcome to KodiCore! Initializing...");
		
		// Setup config
		setupConfig();
		
		// Log to Console
		Console.info("Initializing...");
		
		// Setup Discord
		try {
	        new Discord(getConfig().getString("token"));
			Discord.createListener.discordCommand("!ping", "pong", Discord.api);
			Discord.api.addMessageCreateListener(new ServerInfo());
			Discord.api.addMessageCreateListener(new CopyOwnerAvatar("!getOwnerAvatar"));
		} catch (Exception e) {
			Console.warning("Exception caught! Did you put the token in the config?");
			getPluginLoader().disablePlugin(this);
		}
	}
	
	// On Disable
    @Override
    public void onDisable() {
    	try {
    		Discord.api.disconnect();
    	} catch (Exception e) {
    		Console.info("Could not disconnect from the Discord API.");
    	}
    }
	
	// Function to setup and load the config.yml file
    public void setupConfig() {
    	// Get config
    	getConfig().options().copyDefaults(true);
    	saveDefaultConfig();
    	reloadConfig();
    	// Load file
    	//File file = new File(getDataFolder(), "config.yml");
    	Console.info("Config file found.");
    }
    
    // Instancing
    public static Main inst() {
  	  return instance;
  }
}