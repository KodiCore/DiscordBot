package me.prouser123.kodicore.discord.commands;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import me.prouser123.kodicore.Main;

public class ServerInfo implements MessageCreateListener {
	
	/**
	 * Listener Command to show server information
	 * Usage: api.addMessageCreateListener(new ServerInfo());
	 */
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        // Check if the message content equals "!copyAvatar"
        if (event.getMessage().getContent().equalsIgnoreCase("!serverinfo")) {
        	/*
        	// Create the embed
        	EmbedBuilder embed = new EmbedBuilder()
        	    //.setTitle("Title")
        	    //.setDescription("Description")
        	    .setAuthor("KodiCore", "https://github.com/", "https://cdn.discordapp.com/embed/avatars/0.png")
        	    .addField("A field", "Some text inside the field")
        	    .addInlineField("An inline field", "More text")
        	    .addInlineField("Another inline field", "Even more text")
        	    .setColor(Color.CYAN);
        	// Send the embed
        	event.getChannel().sendMessage(embed);
        	*/
        	
        	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
            //formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        	String uptime = formatter.format(ManagementFactory.getRuntimeMXBean().getUptime());
        	String[] uptime_split = uptime.split(":");
        	
        	String uptime_hours = Integer.toString(new Integer(Integer.parseInt(uptime_split[0])) - 1);
        	String uptime_minutes = uptime_split[1];
        	String uptime_seconds_2 = uptime_split[2];
        	String uptime_seconds = uptime_seconds_2.substring(0, uptime_seconds_2.indexOf("."));
        	
        	String uptime_output = "";
        	
        	if(Integer.parseInt(uptime_hours) != 0 ) {
        		uptime_output += uptime_hours + " hrs";
        	}
        	
        	if(Integer.parseInt(uptime_minutes) != 0 ) {
        		
        		if(Integer.parseInt(uptime_minutes) != 1) {
            		uptime_output += " " + uptime_minutes + " mins ";
        		} 
        		else {
            		uptime_output += " " + uptime_minutes + " min ";
        		}
        	}
        	
        	uptime_output += uptime_seconds + " secs";
        	
        	// Get Bot Owner
        	String bot_owner = "<@";
        	try {
				//bot_owner = event.getApi().getApplicationInfo().get().getOwnerName().toString();
				bot_owner += Long.toString(event.getApi().getApplicationInfo().get().getOwnerId());
				bot_owner += ">";
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	EmbedBuilder embed2 = new EmbedBuilder()
                //.setTitle("Title")
                //.setDescription("Description")
        		.setAuthor("Server Information", "https://github.com/Prouser123/KodiCore", "https://cdn.discordapp.com/embed/avatars/0.png")
            	.addInlineField("Players", Integer.toString(Main.inst().getServer().getOnlinePlayers().size()) + "/" + Integer.toString(Main.inst().getServer().getMaxPlayers()))
            	.addInlineField("Uptime", uptime_output)
            	.addInlineField("Memory", Long.toString(Runtime.getRuntime().freeMemory() / 1024 / 1024 ) + "/" + Long.toString(Runtime.getRuntime().totalMemory() / 1024 / 1024) + " MB free")
            	.addInlineField("Plugins", Integer.toString(Main.inst().getServer().getPluginManager().getPlugins().length))
            	.addInlineField("Difficulty", getDifficulty())
            	.addInlineField("Bot Owner", bot_owner)
            	.addInlineField("Server Version", Main.inst().getServer().getVersion().toString())
            	.setFooter("KodiCore " + Main.version.toString()/*.split("-")[0]*/, "https://cdn.discordapp.com/avatars/215119410103451648/575d90fdda8663b633e36f8b8c06c719.png");
            	// Send the embed
            event.getChannel().sendMessage(embed2);
            //Console.info(embed2.toString());
            return;
        }
        return;
    }
    
    public String getDifficulty() {
    	
    	// Get Difficulty as lower case
    	String currentDifficulty = Bukkit.getWorlds().get(0).getDifficulty().toString().toLowerCase();
    	// Capitalise the first letter
    	String output = currentDifficulty.substring(0, 1).toUpperCase() + currentDifficulty.substring(1);
    	
    	//Console.info("Current Difficulty: " + output);
    	
    	return output;
    }

}