package com.Acrobot.Breeze.Events.CustomEvents;

/**
 * @author Acrobot
 */
public interface CancellableMessage {
    /**
     * @return The cancel message
     */
    public String getCancelMessage();

    /**
     * Sets the cancel message
     * @param message The message
     */
    public void setCancelMessage(String message);
}
