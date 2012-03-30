package com.Acrobot.ChestShop.Modules.Disabling;

import com.Acrobot.Breeze.Breeze;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import org.bukkit.Bukkit;

/**
 * @author Acrobot
 */
public class DisablingModule extends BreezePlugin {
    public void onEnable() {
        final Breeze breeze = getBreeze();
        final BreezePlugin plugin = breeze.getPlugin("TestModule");

        Bukkit.getScheduler().scheduleSyncDelayedTask(getBreeze().getPlugin(), new Runnable() {
            public void run() {
                breeze.getPluginManager().disablePlugin(plugin);
            }
        }, 100L);
    }

    public void onDisable() {
    }

    public String getName() {
        return "DisablingModule";
    }
}
