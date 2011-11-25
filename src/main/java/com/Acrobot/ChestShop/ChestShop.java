package com.Acrobot.ChestShop;

import com.Acrobot.Breeze.Breeze;
import com.Acrobot.ChestShop.Modules.ShopInteraction.ShopInteraction;
import com.Acrobot.ChestShop.Modules.TestModule.TestModule;
import org.bukkit.Server;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Acrobot
 */
public class ChestShop extends JavaPlugin {

    private static Breeze breeze;
    private static Server server;

    /**
     * Called on plugin enable
     */
    public void onEnable() {
        breeze = new Breeze(this);
        server = getServer();

        registerDefaultModules();
    }

    /**
     * Called on plugin disable
     */
    public void onDisable() {
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
        breeze.registerModule(new ShopInteraction());

        breeze.loadPlugins();
    }

    /**
     * @return The bukkit's server
     */
    public static Server getBukkitServer() {
        return server;
    }

    /**
     * Calls an event
     *
     * @param e Event to call
     * @return If the event is cancelled
     */
    public static boolean callEvent(Event e) {
        server.getPluginManager().callEvent(e);

        return e instanceof Cancellable && ((Cancellable) e).isCancelled();
    }
}
