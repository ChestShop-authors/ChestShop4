package com.Acrobot.Breeze.Events.CustomEvents;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * @author Acrobot
 */
public abstract class CancellableEvent extends Event implements Cancellable, CancellableMessage {
    protected String message;
    protected boolean cancelled;

    public CancellableEvent(String s) {
        super(s);
    }

    /**
     * Sets the cancel message
     *
     * @param msg The message
     */
    public void setCancelMessage(String msg) {
        message = msg;
    }

    /**
     * @return The cancel message
     */
    public String getCancelMessage() {
        return message;
    }

    /**
     * Is the event going to be cancelled?
     *
     * @param b is it?
     */
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    /**
     * @return Is the event cancelled?
     */
    public boolean isCancelled() {
        return cancelled;
    }
}
