package com.Acrobot.Breeze;

import com.Acrobot.Breeze.Commands.CommandManager;
import com.Acrobot.Breeze.Config.BreezeConfiguration;
import com.Acrobot.Breeze.Config.ConfigValue;
import com.Acrobot.Breeze.Events.EventManager;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.Breeze.Plugins.PluginManager;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Acrobot
 */
public class Breeze {
    private Plugin plugin;
    public Logger logger;

    private PluginManager manager;
    public CommandManager cmdManager;
    public EventManager eventManager;

    public Breeze(JavaPlugin plugin) {
        this.plugin = plugin;

        logger = plugin.getLogger();

        manager = new PluginManager(this);
        cmdManager = new CommandManager(this);
        eventManager = new EventManager(this);
    }

    /**
     * Gets the configuration object
     *
     * @param name Config's name
     * @return Configuration
     */
    public Configuration getConfig(String name) {
        return getConfig(name, new HashMap<String, ConfigValue>());
    }

    /**
     * @return The bukkit plugin
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the configuration object
     *
     * @param name     Config's name
     * @param defaults Default values
     * @return Configuration
     */
    public BreezeConfiguration getConfig(String name, Map<String, ConfigValue> defaults) {
        return getConfig(getPluginFolder(), name, defaults);
    }

    /**
     * Gets the configuration object from a specified directory
     *
     * @param folder   the folder
     * @param name     config's name
     * @param defaults Default value
     * @return Configuration
     */
    public BreezeConfiguration getConfig(File folder, String name, Map<String, ConfigValue> defaults) {
        BreezeConfiguration config = BreezeConfiguration.loadConfiguration(folder);

        config.addDefaultValues(defaults);
        return config;
    }

    /**
     * Registers the commands class
     *
     * @param clazz The class
     */
    public void registerCommands(BreezePlugin plugin, Class clazz) {
        cmdManager.registerCommand(clazz, plugin);
    }

    /**
     * Registers the event class
     *
     * @param plugin   Plugin to register the events for
     * @param listener the listener
     */
    public void registerEvents(BreezePlugin plugin, Listener listener) {
        eventManager.registerEvents(plugin, listener);
    }

    /**
     * Unregisters the event class
     *
     * @param listener the listener
     */
    public void unregisterEvents(Listener listener) {
        eventManager.unregisterEvents(listener);
    }

    /**
     * Registers a module (plugin)
     *
     * @param plugin The Breeze plugin to register
     */
    public void registerModule(BreezePlugin plugin) {
        manager.initializePlugin(plugin);
    }

    /**
     * Unregisters a module
     *
     * @param plugin Plugin to unregister
     */
    public void unregisterModule(BreezePlugin plugin) {
        manager.disablePlugin(plugin);
    }

    /**
     * @return The plugin manager
     */
    public PluginManager getPluginManager() {
        return manager;
    }

    /**
     * @return Breeze's logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Loads all plugins
     */
    public void loadPlugins() {
        manager.initializePlugins();
    }

    /**
     * @return The Breeze plugins folder
     */
    public File getPluginFolder() {
        return new File(plugin.getDataFolder(), "plugins");
    }

    /**
     * Returns a BreezePlugin named like parameter
     *
     * @param name plugin name
     * @return BreezePlugin found
     */
    public BreezePlugin getPlugin(String name) {
        return manager.getPlugin(name);
    }

    /**
     * Does everything it can during a reload
     */
    public void disable() {
        manager.disablePlugins();

        this.plugin = null;
        this.cmdManager = null;
        this.eventManager = null;
        this.logger = null;
        this.manager = null;
    }
}
