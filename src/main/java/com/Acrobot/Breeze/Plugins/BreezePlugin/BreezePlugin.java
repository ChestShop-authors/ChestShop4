package com.Acrobot.Breeze.Plugins.BreezePlugin;

import com.Acrobot.Breeze.Breeze;

import java.io.File;
import java.util.logging.Logger;

/**
 * @author Acrobot
 */
public abstract class BreezePlugin implements EnablingPlugin, NamedPlugin {
    private File dataFolder;
    private Breeze breeze;

    public BreezePlugin() {
    }

    /**
     * Returns the folder in which the plugin should store its config
     *
     * @return the folder
     */
    public File getDataFolder() {
        return dataFolder;
    }

    /**
     * Returns the Breeze object
     *
     * @return Breeze
     */
    public Breeze getBreeze() {
        return breeze;
    }

    /**
     * Returns a plugin logger
     *
     * @return Logger for this plugin
     */
    public Logger getLogger() {
        return breeze.logger;
    }

    /**
     * Initializes the plugin
     * Shouldn't be called manually.
     *
     * @param dataFolder The folder where the plugin should store its files
     * @param breeze     The Breeze object
     */
    public final void initialize(File dataFolder, Breeze breeze) {
        this.dataFolder = dataFolder;
        this.breeze = breeze;

        onEnable();
    }

    /**
     * Executed when Breeze is being disabled
     */
    public final void disable() {
        onDisable();
    }
}
