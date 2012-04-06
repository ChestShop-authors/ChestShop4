package com.Acrobot.ChestShop.Modules.NameModule;

import com.Acrobot.Breeze.Config.BreezeConfiguration;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.ChestShop.Events.SignInformationEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;

/**
 * @author Acrobot
 */
public class NameModule extends BreezePlugin {
    private static BreezeConfiguration config;

    public void onEnable() {
        getBreeze().registerEvents(this, new NameListener());

        config = BreezeConfiguration.loadConfiguration(new File(getBreeze().getPlugin().getDataFolder(), "longName.storage"));
    }

    public void onDisable() {
        config = null;
    }

    public static class NameListener implements Listener {
        @EventHandler
        public static void onName(SignInformationEvent event) {
            if (!(event.getOwner() instanceof String)) {
                return;
            }

            String owner = (String) event.getOwner();
            String strippedName = stripName(owner);

            if (config.contains(strippedName)) {
                event.setOwner(config.getString(strippedName));
            }
        }

        private static String stripName(String name) {
            if (name.length() <= 15) {
                return name;
            } else {
                return name.substring(0, 15);
            }
        }
    }
}
