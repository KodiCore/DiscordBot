package me.prouser123.kodicore.discord;

import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.DiscordApi;

import me.prouser123.kodicore.Utils;
import me.prouser123.kodicore.send.Console;

public class Discord {
	
	public static String token = null; // Bot token
	public static DiscordApi api = null; // Api Instance
	
	public Discord(String token) {
		Discord.token = token;
		
		// Create an Instance of the DiscordApi
		api = new DiscordApiBuilder().setToken(token).login().join();
		
        // Print the invite url of the bot
        Console.info("[Discord] Bot Invite Link: " + api.createBotInvite());
        
        // Create server join Listeners
        api.addServerJoinListener(event -> Console.info("[Discord] Joined Server: " + event.getServer().getName()));
        api.addServerLeaveListener(event -> Console.info("[Discord] Left Server: " + event.getServer().getName()));
	}
	
	public static class createListener {
		/**
		 * Create a Listener which will reply with text on Discord.
		 * 
		 * @param api JavaCord DiscordApi Instance 
		 * @param command Command (e.g. !ping)
		 * @param output Output Message
		 */
		public static void discordCommand(String command, String output, DiscordApi api) {
			// Add a listener which answers with "Pong!" if someone writes "!ping"
		    api.addMessageCreateListener(event -> {
		        if (event.getMessageContent().equalsIgnoreCase(command)) {
		            event.getChannel().sendMessage(output);
		        }
		    });
		}
		
		/**
		 * Execute a command in the console, triggered by a discord command
		 * 
		 * @param discordCommand command to trigger the event
		 * @param consoleCommand command to execute in console (e.g. stop (leave out the /))
		 * @param api JavaCord DiscordApi Instance
		 */
		public static void discordToConsoleCommand(String discordCommand, String consoleCommand, DiscordApi api) {
			// Add a listener to run a console command when the given discord command is recieved
			api.addMessageCreateListener(event -> {
		        if (event.getMessageContent().equalsIgnoreCase(discordCommand)) {
		            event.getChannel().sendMessage("Running console command: " + consoleCommand);
		            Console.info("[Discord] + Running console command '" + consoleCommand + "' from user '" + event.getMessage().getAuthor().getName() + "'...");
		            Utils.runConsoleCommand(consoleCommand);
		            
		            
		        }
		    });
		}
	}
}