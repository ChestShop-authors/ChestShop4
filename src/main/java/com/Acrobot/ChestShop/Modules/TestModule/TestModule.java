package com.Acrobot.ChestShop.Modules.TestModule;

import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.ChestShop.ChestShop;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author Acrobot
 */
public class TestModule extends BreezePlugin {
    public void onEnable() {
        getBreeze().registerEvents(this, new TestListener());
    }

    public void onDisable() {
    }

    public String getName() {
        return "TestModule";
    }

    public class TestListener implements Listener {
        @EventHandler
        public void onEvent(PlayerMoveEvent event) {
            if (!event.getFrom().getBlock().equals(event.getTo().getBlock())) {
                ChestShop.getBukkitLogger().info("WORKS!");
            }
        }
    }
}
