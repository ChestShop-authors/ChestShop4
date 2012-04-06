package com.Acrobot.Breeze.Events.Cancelables;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * @author Acrobot
 */
public abstract class CancellableEvent extends Event implements Cancellable {
    protected boolean cancelled;

    /**
     * @return Is the event cancelled?
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Cancels the event
     *
     * @param cancelled Whether to cancel the event
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
