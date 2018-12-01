package me.prouser123.kodi.discordbot;

import org.bukkit.plugin.java.JavaPlugin;

import me.prouser123.kodicore.discord.Discord;
import me.prouser123.kodicore.discord.commands.CopyOwnerAvatar;
import me.prouser123.kodicore.discord.commands.ServerInfo;
import me.prouser123.kodicore.send.Console;

import java.lang.IllegalStateException;

public class Main extends JavaPlugin {

    // On Enable
	@Override
	public void onEnable() {
		// Setup config
		setupConfig();
		
		// Log to Console
		Console.info("[DiscordBot] Initializing...");
		
		// Setup Discord
		try {
	        new Discord(getConfig().getString("token"));
			Discord.createListener.discordCommand("!ping", "pong", Discord.api);
			Discord.api.addMessageCreateListener(new ServerInfo());
			Discord.api.addMessageCreateListener(new CopyOwnerAvatar("!getOwnerAvatar"));
		} catch (Exception e) {
			Console.warning("[DiscordBot] Exception caught! Did you put the token in the config?");
			getPluginLoader().disablePlugin(this);
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
}