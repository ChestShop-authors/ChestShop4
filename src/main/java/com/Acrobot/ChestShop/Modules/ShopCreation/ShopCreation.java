package com.Acrobot.ChestShop.Modules.ShopCreation;

import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.ChestShop.Modules.ShopCreation.Events.SignPlaced;

/**
 * @author Acrobot
 */
public class ShopCreation extends BreezePlugin {
    public void onEnable() {
        getBreeze().registerEvents(SignPlaced.class);
    }

    public void onDisable() {
    }

    public String getName() {
        return "ShopCreation";
    }
}
