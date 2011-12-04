package com.Acrobot.Breeze;

import com.Acrobot.Breeze.Commands.CommandManager;
import com.Acrobot.Breeze.Config.Config;
import com.Acrobot.Breeze.Config.ConfigObject;
import com.Acrobot.Breeze.Events.EventManager;
import com.Acrobot.Breeze.Logging.LogManager;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.Breeze.Plugins.PluginLoader;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Acrobot
 */
public class Breeze {
    private JavaPlugin plugin;
    public Logger logger;
    public PluginLoader loader;
    public CommandManager cmdManager;
    public EventManager eventManager;

    public Breeze(JavaPlugin plugin) {
        this.plugin = plugin;

        logger = LogManager.init(Logger.getLogger(plugin.getDescription().getName()));

        loader = new PluginLoader(this);
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
        return getConfig(name, new HashMap<String, ConfigObject>());
    }

    /**
     * @return The bukkit plugin
     */
    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the configuration object
     *
     * @param name Config's name
     * @param defaults Default values
     * @return Configuration
     */
    public Configuration getConfig(String name, Map<String, ConfigObject> defaults) {
        return new Config(plugin.getDataFolder(), name, defaults).getConfiguration();
    }

    /**
     * Registers the commands class
     *
     * @param clazz The class
     */
    public void registerCommands(Class clazz) {
        cmdManager.registerCommand(clazz);
    }

    /**
     * Registers the event class
     *
     * @param clazz The class
     */
    public void registerEvents(Class clazz) {
        eventManager.registerEvents(clazz);
    }

    /**
     * Registers a module (plugin)
     *
     * @param plugin The Breeze plugin to register
     */
    public void registerModule(BreezePlugin plugin) {
        loader.plugins.add(plugin);
    }

    /**
     * Loads all plugins
     */
    public void loadPlugins() {
        loader.loadPlugins(new File(plugin.getDataFolder() + File.separator + "plugins"));
    }

    /**
     * Does everything it can during a reload
     */
    public void disable(){
        loader.plugins = null;

        this.plugin = null;
        this.cmdManager = null;
        this.eventManager = null;
        this.logger = null;
        this.loader = null;
    }
}
