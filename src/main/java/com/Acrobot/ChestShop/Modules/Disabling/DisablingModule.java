package com.Acrobot.ChestShop.Modules.Disabling;

import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import org.bukkit.Bukkit;

/**
 * @author Acrobot
 */
public class DisablingModule extends BreezePlugin {
    public void onEnable() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(getBreeze().getPlugin(), new Runnable() {
            public void run() {
                BreezePlugin plugin = getBreeze().getPlugin("TestModule");
                if (plugin != null) {
                    getBreeze().getPluginManager().disablePlugin(plugin);
                }
            }
        }, 100L);
    }

    public void onDisable() {
    }

    public String getName() {
        return "DisablingModule";
    }
}
