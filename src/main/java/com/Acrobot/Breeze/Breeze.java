package com.Acrobot.Breeze;

import com.Acrobot.Breeze.Commands.CommandManager;
import com.Acrobot.Breeze.Config.Config;
import com.Acrobot.Breeze.Events.EventManager;
import com.Acrobot.Breeze.Logging.LogManager;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.Breeze.Plugins.PluginLoader;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
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

    public Configuration getConfig(String name) {
        return getConfig(name, null);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Configuration getConfig(String name, Map<String, Object> defaults) {
        return new Config(plugin.getDataFolder(), name, defaults).getConfiguration();
    }

    public void registerCommands(Class clazz) {
        cmdManager.registerCommand(clazz);
    }

    public void registerEvents(Class clazz) {
        eventManager.registerEvents(clazz);
    }

    public void registerModule(BreezePlugin plugin) {
        loader.plugins.add(plugin);
    }

    public void loadPlugins(){
        loader.loadPlugins(new File(plugin.getDataFolder() + File.separator + "plugins"));
    }
}
