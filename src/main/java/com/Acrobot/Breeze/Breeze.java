package com.Acrobot.Breeze;

import com.Acrobot.Breeze.Commands.CommandManager;
import com.Acrobot.Breeze.Config.Config;
import com.Acrobot.Breeze.Logging.LogManager;
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

    public PluginLoader loader = new PluginLoader(this);

    public CommandManager cmdManager = new CommandManager(this);

    public Breeze(JavaPlugin plugin) {
        this.plugin = plugin;
        logger = LogManager.init(Logger.getLogger(plugin.getDescription().getName()));

        loader.loadPlugins(new File(plugin.getDataFolder() + File.separator + "plugins")); //Load all plugins from the folder
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

    public void registerCommands(Class clazz){
        cmdManager.registerCommand(clazz);
    }
}
