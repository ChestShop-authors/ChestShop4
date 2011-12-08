package com.Acrobot.Breeze.Config;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileWriter;
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

    public Config(File folder, String configName, Map<String, ConfigObject> values) {
        this(folder, configName);
        if (values != null) generateDefaults(values);
    }

    /**
     * Generates the default config values
     *
     * @param values Values to put in the config
     */
    private void generateDefaults(Map<String, ConfigObject> values) {
        try {
            FileWriter fw = new FileWriter(file, true);

            for (String o : values.keySet()) {
                if (config.contains(o)) continue;
                if (file.exists() && file.length() != 0) fw.write('\n');
                fw.write(o + ": " + retrieveValue(values.get(o)));
            }

            fw.close();
            config.load(file);
        } catch (Exception ignored) {
        }
    }

    /**
     * Retrieves the value of that ConfigObject
     *
     * @param o The configObject
     * @return The value
     */
    private String retrieveValue(ConfigObject o) {
        Object value = o.value;
        String comment = o.comment;
        StringBuilder toReturn = new StringBuilder();

        if (value instanceof Number || value instanceof Boolean) toReturn.append(String.valueOf(value));
        else toReturn.append('\"').append(String.valueOf(value)).append('\"');

        if (comment != null) toReturn.append('\n').append('#').append(comment);
        return toReturn.toString();
    }

    /**
     * @return Configuration object
     */
    public Configuration getConfiguration() {
        return config;
    }
}
