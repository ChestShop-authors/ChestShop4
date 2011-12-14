package com.Acrobot.ChestShop.Modules.ShopInteraction;

import com.Acrobot.Breeze.Breeze;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.ChestShop.Modules.ShopInteraction.Events.PlayerInteract;
import com.Acrobot.ChestShop.Modules.ShopInteraction.Events.ShopInteract;
import org.bukkit.configuration.Configuration;

/**
 * @author Acrobot
 */
public class ShopInteraction extends BreezePlugin {
    public static Configuration config;
    public static Configuration language;

    public void onEnable() {
        Breeze br = getBreeze();
        config = br.getConfig("config", Config.config);
        language = br.getConfig("local");

        br.registerEvents(PlayerInteract.class); //When the player touches a sign
        br.registerEvents(ShopInteract.class); //Custom event listener
    }

    public void onDisable() {
        config = null;
    }

    public String getName() {
        return "ShopInteraction";
    }
}