package com.Acrobot.ChestShop.Modules.ShopModule;

import com.Acrobot.Breeze.Config.BreezeConfiguration;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.ChestShop.Events.ShopSignClickEvent;
import com.Acrobot.ChestShop.Events.SignInformationEvent;
import com.Acrobot.ChestShop.Events.TransactionEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import java.io.File;

/**
 * @author Acrobot
 */
public class ShopModule extends BreezePlugin {
    private static BreezeConfiguration config;

    public void onEnable() {
        getBreeze().registerEvents(this, new ShopListener());

        config = BreezeConfiguration.loadConfiguration(new File(getBreeze().getPlugin().getDataFolder(), "config.yml"));
    }

    public void onDisable() {
    }

    public static class ShopListener implements Listener {
        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        public static void onShopClick(ShopSignClickEvent event) {
            SignInformationEvent evt = new SignInformationEvent(event.getSign());

            Bukkit.getPluginManager().callEvent(evt);

            TransactionEvent.Type type;

            if (config.getBoolean("REVERSE_BUTTONS")) {
                type = event.getAction() == Action.RIGHT_CLICK_BLOCK ? TransactionEvent.Type.SELL : TransactionEvent.Type.BUY;
            } else {
                type = event.getAction() == Action.RIGHT_CLICK_BLOCK ? TransactionEvent.Type.BUY : TransactionEvent.Type.SELL;
            }

            double price = (type == TransactionEvent.Type.BUY ? evt.getBuyPrice() : evt.getSellPrice());

            Bukkit.getPluginManager().callEvent(
                    new TransactionEvent(type, evt.getItem(), price, evt.getOwner(), event.getClicker(), evt.getSign()));
        }
    }
}
