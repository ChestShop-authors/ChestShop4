package com.Acrobot.ChestShop;

import com.Acrobot.Breeze.Breeze;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.ChestShop.Commands.ItemInfo;
import com.Acrobot.ChestShop.Modules.Debug.DebugModule;
import com.Acrobot.ChestShop.Modules.Language.Language;
import com.Acrobot.ChestShop.Modules.Prefix.Prefix;
import com.Acrobot.ChestShop.Modules.ShopModule.ShopModule;
import com.Acrobot.ChestShop.Modules.SignModule.SignModule;
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

        System.gc(); //This has to be here, otherwise old version of plugins can remain in memory
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
        registerCommands();

        breeze.registerModule(new SignModule());
        breeze.registerModule(new ShopModule());
        breeze.registerModule(new DebugModule());

        breeze.registerModule(new Language());
        breeze.registerModule(new Prefix());

        breeze.loadPlugins();
    }

    /**
     * Registers commands
     */
    private void registerCommands() {
        CommandModule module = new CommandModule();
        breeze.registerModule(module);

        breeze.registerCommands(module, ItemInfo.class);
    }

    /**
     * Returns a plugin logger
     *
     * @return A plugin logger
     */
    public static Logger getBukkitLogger() {
        return breeze.logger;
    }

    /**
     * A default module for using commands
     */
    private class CommandModule extends BreezePlugin {
        public void onEnable() {
        }

        public void onDisable() {
        }
    }
}
