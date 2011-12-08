package com.Acrobot.ChestShop.Modules.ShopCreation;

import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.ChestShop.Modules.ShopCreation.Events.SignPlaced;
import org.bukkit.configuration.Configuration;

/**
 * @author Acrobot
 */
public class ShopCreation extends BreezePlugin {
    public static Configuration language;

    public void onEnable() {
        getBreeze().registerEvents(SignPlaced.class);

        language = getBreeze().getConfig("language", Language.language);
    }

    public void onDisable() {
    }

    public String getName() {
        return "ShopCreation";
    }
}
