package com.Acrobot.Breeze.Plugins.BreezePlugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author Acrobot
 */
public abstract class BreezePlugin implements EnablingPlugin{
    private File dataFolder;
    private JavaPlugin plugin;

    public BreezePlugin() {}

    /**
     * Returns the folder in which the plugin should store its config
     * @return the folder
     */
    public File getDataFolder(){
        return dataFolder;
    }

    /**
     * Returns the main plugin class
     * @return plugin class
     */
    public JavaPlugin getMainPlugin(){
        return plugin;
    }

    /**
     * Initializes the plugin
     * Shouldn't be called manually.
     * @param dataFolder The folder where the plugin should store its files
     * @param plugin The main plugin class
     */
    public final void initialize(File dataFolder, JavaPlugin plugin){
        this.dataFolder = dataFolder;
        this.plugin = plugin;

        onEnable();
    }
}
