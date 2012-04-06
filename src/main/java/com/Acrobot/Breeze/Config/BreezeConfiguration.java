package com.Acrobot.Breeze.Config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Acrobot
 */
public class BreezeConfiguration extends YamlConfiguration {
    protected File file;
    protected Map<String, ConfigValue> defaultValues = new HashMap<String, ConfigValue>();

    protected BreezeConfiguration(File file) {
        this.file = file;
    }

    /**
     * Adds default values for the config
     *
     * @param map The default values to add
     */
    public void addDefaultValues(Map<String, ? extends ConfigValue> map) {
        defaultValues.putAll(map);
    }

    /**
     * Creates a new BreezeConfiguration object
     *
     * @param file file to load config from
     * @return BreezeConfiguration object
     */
    public static BreezeConfiguration loadConfiguration(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        BreezeConfiguration config = new BreezeConfiguration(file);

        config.load();

        return config;
    }

    /**
     * Loads the config
     */
    public void load() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            super.load(file);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (createDefaultValues()) {
            save();
            load();
        }
    }

    /**
     * Creates default values
     *
     * @return were any values added?
     */
    private boolean createDefaultValues() {
        boolean changed = false;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

            for (String key : defaultValues.keySet()) {
                if (this.contains(key)){
                    continue;
                }
                if (file.exists() && file.length() != 0){
                    bw.write('\n'); //New line
                }

                changed = true;
                bw.write(key + ": " + defaultValues.get(key).retrieveValue());
            }

            bw.close();

            if (changed) {
                load();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return changed;
    }

    /**
     * Saves the config
     */
    public void save() {
        try {
            super.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
