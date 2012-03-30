package com.Acrobot.ChestShop;

import com.Acrobot.Breeze.Breeze;
import com.Acrobot.ChestShop.Modules.BasicCommands.ItemInfo;
import com.Acrobot.ChestShop.Modules.Disabling.DisablingModule;
import com.Acrobot.ChestShop.Modules.TestModule.TestModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

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
        
        System.gc();
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
        breeze.registerModule(new TestModule());
        breeze.registerModule(new DisablingModule());

        breeze.registerModule(new ItemInfo());

        breeze.loadPlugins();
    }

    /**
     * Returns a plugin logger
     *
     * @return A plugin logger
     */
    public static Logger getBukkitLogger() {
        return breeze.logger;
    }
}
