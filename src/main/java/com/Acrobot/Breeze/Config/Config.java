package com.Acrobot.Breeze.Config;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Map;

/**
 * @author Acrobot
 */
public class Config {
    /**
     * Configuration file
     */
    YamlConfiguration config;
    File file;

    public Config(File folder, String configName) {
        if (!folder.exists()) folder.mkdir();
        file = new File(folder, configName + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public Config(File folder, String configName, Map<String, Object> values) {
        this(folder, configName);
        if (values != null) generateDefaults(values);
    }

    /**
     * Generates the default config values
     *
     * @param values Values to put in the config
     */
    private void generateDefaults(Map<String, Object> values) {
        for (String o : values.keySet()) config.addDefault(o, values.get(o));
        config.options().copyDefaults();
        try {
            config.save(file);
        } catch (Exception ignored) {}
    }

    public Configuration getConfiguration() {
        return config;
    }
}
