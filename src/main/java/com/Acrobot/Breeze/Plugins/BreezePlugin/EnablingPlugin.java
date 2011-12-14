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
     * Usually events should be unregistered here (if possible) and static values nullified
     */
    public void onDisable();
}
