package com.Acrobot.Breeze.Plugins;

import com.Acrobot.Breeze.Breeze;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Acrobot
 */
public class PluginLoader {
    public List<BreezePlugin> plugins = new ArrayList<BreezePlugin>();
    private Breeze breeze;

    public PluginLoader(Breeze br) {
        breeze = br;
    }

    /**
     * Loads the Breeze plugins from the folder
     *
     * @param pluginFolder the plugin folder
     */
    public void loadPlugins(File pluginFolder) {
        if (pluginFolder == null) return;
        if (!pluginFolder.exists()) pluginFolder.mkdir();

        for (File file : pluginFolder.listFiles()) {
            loadPlugin(file);
        }
    }

    /**
     * Loads a Breeze plugin
     *
     * @param plugin the breeze plugin file
     */
    private void loadPlugin(File plugin) {
        BreezePlugin bPlugin = getPluginFromJar(plugin);
        if (bPlugin == null) return;

        plugins.add(bPlugin);
        breeze.logger.info("Loaded a Breeze plugin: " + plugin.getName());
    }

    /**
     * Gets the Breeze plugin from a jar file
     *
     * @param plugin plugin file
     * @return BreezePlugin plugin
     */
    private BreezePlugin getPluginFromJar(File plugin) {
        try {
            URLClassLoader loader = new URLClassLoader(new URL[]{plugin.toURI().toURL()});
            return (BreezePlugin) loader.getResource("Main.class").getContent();
        } catch (Exception ex) {
            breeze.logger.severe("Couldn't load Breeze plugin: " + plugin.getName());
            return null;
        }
    }
}
