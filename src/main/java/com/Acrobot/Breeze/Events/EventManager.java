package com.Acrobot.Breeze.Events;

import com.Acrobot.Breeze.Breeze;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Acrobot
 */
public class EventManager {
    private Breeze br;

    public EventManager(Breeze breeze) {
        this.br = breeze;
    }

    /**
     * Registers events in the most efficient way
     *
     * @param clazz The listener class
     */
    public void registerEvents(Class clazz) {
        List<Event> events = new LinkedList<Event>();

        for (Method m : clazz.getMethods()) {
            if (!m.isAnnotationPresent(Event.class)) continue;
            events.add(m.getAnnotation(Event.class));
        }

        PluginManager pm = br.getPlugin().getServer().getPluginManager();
        Listener listener;

        try {
            listener = (Listener) clazz.newInstance();
        } catch (Exception e) {
            br.logger.severe("Failed to register the listener: " + clazz.getName());
            e.printStackTrace();
            return;
        }

        for (Event event : events) pm.registerEvent(event.type(), listener, event.priority(), br.getPlugin());
    }
}
