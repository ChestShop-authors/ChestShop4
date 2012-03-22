package com.Acrobot.Breeze.Events;

import com.Acrobot.Breeze.Breeze;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Acrobot
 */
public class EventManager {
    private Breeze br;
    private Set<Listener> listeners = new HashSet<Listener>();

    public EventManager(Breeze breeze) {
        this.br = breeze;
    }

    /**
     * Registers events in the most efficient way
     *
     * @param listener The listener class
     */
    public void registerEvents(Listener listener) {
        listeners.add(listener);
        Bukkit.getServer().getPluginManager().registerEvents(listener, br.getPlugin());
    }

    /**
     * Unregisters all events
     */
    public void unregisterEvents() {
        for (Listener listener : listeners) {
            unregisterEvent(listener);
        }
    }

    public void unregisterEvent(Listener listener) {
        HandlerList.unregisterAll(listener);
    }
}
