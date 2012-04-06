package com.Acrobot.ChestShop.Modules.Prefix;

import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.ChestShop.Events.MessageEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author Acrobot
 */
public class Prefix extends BreezePlugin {
    public void onEnable() {
        getBreeze().registerEvents(this, new PrefixListener());
    }

    public void onDisable() {
    }

    public static class PrefixListener implements Listener {
        @EventHandler
        public static void onPrefix(MessageEvent event) {
            if ("prefix".equals(event.getCodeName())) {
                return;
            }

            MessageEvent evt = new MessageEvent("prefix");
            Bukkit.getPluginManager().callEvent(evt);

            if (evt.getMessage() != null && !evt.getMessage().isEmpty()) {
                event.setMessage(evt.getMessage() + event.getMessage());
            }
        }
    }
}
