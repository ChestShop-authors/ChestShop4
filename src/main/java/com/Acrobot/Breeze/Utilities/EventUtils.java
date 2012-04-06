package com.Acrobot.Breeze.Utilities;

import com.Acrobot.ChestShop.Events.MessageEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * @author Acrobot
 */
public class EventUtils {
    /**
     * Sends a message from the given MessageEvent
     *
     * @param event  Event that contains the message
     * @param sender Person to send the message to
     */
    public static void sendMessage(MessageEvent event, CommandSender sender) {
        Bukkit.getPluginManager().callEvent(event);

        if (event.getMessage() != null && !event.getMessage().isEmpty()) {
            sender.sendMessage(event.getMessage());
        }
    }
}
