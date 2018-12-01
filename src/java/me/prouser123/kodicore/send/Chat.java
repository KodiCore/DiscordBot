package me.prouser123.kodicore.send;

import java.util.Set;

import org.bukkit.World;
import org.bukkit.entity.Player;

import me.prouser123.kodicore.Utils;

public class Chat {
	/*
	static Plugin plugin = null; // Plugin instance
	
	public Chat(Plugin plugin) {
		Chat.plugin = plugin;
	}
	*/
	
	
	public static void toPlayer(String message, Player player) {
        player.sendMessage(message);
    }


    /**
     * Send a private message
     *
     * @param message Message to send
     * @param players Array of players to send message to
     */
    public static void toPlayers(String message, Set<Player> players) {
        for (Player player : players) {
            toPlayer(message, player);
        }
    }


    /**
     * Send a private message
     *
     * @param message    Message to send
     * @param permission Permission node
     */
    public static void toPermission(String message, String permission) {
        Utils.Broadcast.toPermission(message, permission);
    }

    /**
     * Send a broadcast to the server
     *
     * @param message Message to broadcast
     */
    public static void toServer(String message) {
        Utils.Broadcast.global(message);
    }

    /**
     * Send a message to a specific world
     *
     * @param message Message
     * @param world   World
     */
    public static void toWorld(String message, World world) {
        for (Player player : Utils.Player.getOnlinePlayersByNames()) {
            if (player.getWorld().equals(world)) {
                player.sendMessage(message);
            }
        }
    }

    /**
     * Send a message to a specific world
     *
     * @param message Message
     * @param world   World
     */
    public static void toWorldGroup(String message, World world) {
        for (Player player : Utils.Player.getOnlinePlayersByNames()) {
            if (player.getWorld().equals(world)
                    || player.getWorld().equals(
                    Utils.getWorld(world.getName() + "_nether"))
                    || player.getWorld().equals(
                    Utils.getWorld(world.getName() + "_the_end"))) {
                player.sendMessage(message);
            }
        }
    }
}