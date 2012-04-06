package com.Acrobot.ChestShop.Modules.Language;

import com.Acrobot.Breeze.Config.BreezeConfiguration;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.ChestShop.Events.MessageEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.io.File;

/**
 * @author Acrobot
 */
public class Language extends BreezePlugin {
    private static BreezeConfiguration config;

    public void onEnable() {
        getBreeze().registerEvents(this, new LanguageListener());


        config = BreezeConfiguration.loadConfiguration(new File(getBreeze().getPlugin().getDataFolder(), "local.yml"));
    }

    public void onDisable() {
        config = null;
    }

    public static class LanguageListener implements Listener {
        @EventHandler(priority = EventPriority.LOWEST)
        public static void onLanguage(MessageEvent event) {
            String code = event.getCodeName();

            if (config.contains(code)) {
                event.setMessage(ChatColor.translateAlternateColorCodes('&', config.getString(code)));
            }
        }
    }
}
