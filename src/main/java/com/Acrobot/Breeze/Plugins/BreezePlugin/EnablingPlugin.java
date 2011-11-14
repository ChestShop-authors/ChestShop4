package com.Acrobot.Breeze.Plugins.BreezePlugin;

/**
 * @author Acrobot
 */
public interface EnablingPlugin {
    /**
     * Called when the plugin is enabled
     */
    public void onEnable();

    /**
     * Called when the plugin is disabled
     */
    public void onDisable();
}
