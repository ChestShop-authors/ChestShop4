package com.Acrobot.Breeze.Plugins.Loader;

import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.InvalidDescriptionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Acrobot
 */
public class JavaPluginLoader {
    protected ClassLoader loader;
    protected ClassLoader parent;
    protected Class parentClass;

    public JavaPluginLoader(Class parent) {
        this.parent = parent.getClassLoader();
        this.parentClass = parent;
    }

    /**
     * Loads all plugins
     *
     * @param folder Folder to load the plugins from
     * @return Plugins loaded
     */
    public Set<BreezePlugin> load(File folder) {
        List<URL> urls = new ArrayList<URL>();
        Set<BreezePlugin> plugins = new HashSet<BreezePlugin>();
        Set<File> files = new HashSet<File>();

        if (!folder.exists()) {
            folder.mkdir();
        }

        for (File file : folder.listFiles(new JarFilter())) {
            if (!isCorrectPlugin(file)) {
                continue;
            }

            try {
                urls.add(file.toURI().toURL());
                files.add(file);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        loader = URLClassLoader.newInstance(urls.toArray(new URL[urls.size()]), parent);

        for (File file : files) {
            try {
                plugins.add(loadPlugin(file));
            } catch (InvalidDescriptionException e) {
                e.printStackTrace();
            }
        }

        return plugins;
    }

    /**
     * Loads a specific plugin
     *
     * @param file plugin to load
     * @return BreezePlugin
     * @throws org.bukkit.plugin.InvalidDescriptionException
     *          The main class wasn't found
     */
    private BreezePlugin loadPlugin(File file) throws InvalidDescriptionException {
        try {
            String mainClass = getMainClass(file);

            Class<?> clazz = Class.forName(mainClass, true, loader);
            Class<? extends BreezePlugin> pluginClass = clazz.asSubclass(BreezePlugin.class);

            Constructor<? extends BreezePlugin> ctor = pluginClass.getConstructor(parentClass.getClass());
            return ctor.newInstance(getClass());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private String getMainClass(File file) throws InvalidDescriptionException {
        try {
            JarFile jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("plugin.info");

            Configuration config = YamlConfiguration.loadConfiguration(jar.getInputStream(entry));

            String mainClass = config.getString("main-class");

            if (mainClass == null || mainClass.isEmpty()) {
                throw new InvalidDescriptionException(new FileNotFoundException("Couldn't find the main-class!"));
            }

            return mainClass;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Checks if the file is a correct plugin
     *
     * @param file file to check
     * @return is the file a correct plugin?
     */
    private boolean isCorrectPlugin(File file) {
        if (!file.getName().endsWith(".jar")) {
            return false;
        }

        try {
            JarFile jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("plugin.info");

            return entry != null;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    class JarFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return name.endsWith(".jar");
        }
    }
}
