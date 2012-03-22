package com.Acrobot.Breeze.Events.Cancelables;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author Acrobot
 */
public abstract class CancellableEvent extends Event {
    protected Result result;
    protected static final HandlerList handlers = new HandlerList();


    /**
     * What will be the result of the event?
     *
     * @param result the Result for this event
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * @return Is the event allowed?
     */
    public Result getResult() {
        return result;
    }

    /**
     * @return The HandlerList for this event
     */
    public HandlerList getHandlers() {
        return handlers;
    }
}
