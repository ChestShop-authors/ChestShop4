package com.Acrobot.Breeze.Events;

import com.Acrobot.Breeze.Breeze;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Acrobot
 */
public class EventManager {
    private Breeze br;
    private Map<BreezePlugin, Set<Listener>> listeners = new HashMap<BreezePlugin, Set<Listener>>();

    public EventManager(Breeze breeze) {
        this.br = breeze;
    }

    /**
     * Registers events in the most efficient way
     *
     * @param plugin   plugin that's linked to the events
     * @param listener The listener class
     */
    public void registerEvents(BreezePlugin plugin, Listener listener) {
        if (!listeners.containsKey(plugin)) {
            Set<Listener> lst = new HashSet<Listener>(1);
            lst.add(listener);

            listeners.put(plugin, lst);
        } else {
            listeners.get(plugin).add(listener);
        }

        Bukkit.getServer().getPluginManager().registerEvents(listener, br.getPlugin());
    }

    /**
     * Unregisters all events
     */
    public void unregisterEvents() {
        for (Set<Listener> listener : listeners.values()) {
            for (Listener l : listener) {
                unregisterEvents(l);
            }
        }
    }

    /**
     * Unregisters all events from one listener
     *
     * @param listener Listener to unregister
     */
    public void unregisterEvents(Listener listener) {
        HandlerList.unregisterAll(listener);
    }

    /**
     * Unregisters all events from one plugin
     *
     * @param plugin Plugin to unregister
     */
    public void unregisterEvents(BreezePlugin plugin) {
        if (!listeners.containsKey(plugin)) {
            return;
        }
        for (Listener l : listeners.get(plugin)) {
            unregisterEvents(l);
        }
    }
}
