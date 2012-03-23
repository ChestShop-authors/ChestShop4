package com.Acrobot.ChestShop;

import com.Acrobot.Breeze.Breeze;
import com.Acrobot.ChestShop.Modules.ShopCreation.ShopCreation;
import com.Acrobot.ChestShop.Modules.ShopInteraction.ShopInteraction;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Acrobot
 */
public class ChestShop extends JavaPlugin {
    private static Breeze breeze;

    /**
     * Called on plugin enable
     */
    public void onEnable() {
        breeze = new Breeze(this);

        registerDefaultModules();
    }

    /**
     * Called on plugin disable
     */
    public void onDisable() {
        breeze.disable();

        breeze = null;
    }

    /**
     * Gets a Breeze instance
     *
     * @return breeze instance
     */
    public static Breeze getBreeze() {
        return breeze;
    }

    /**
     * Registers the default modules
     */
    private void registerDefaultModules() {
        breeze.registerModule(new ShopCreation());
        breeze.registerModule(new ShopInteraction());

        breeze.loadPlugins();
    }
}
