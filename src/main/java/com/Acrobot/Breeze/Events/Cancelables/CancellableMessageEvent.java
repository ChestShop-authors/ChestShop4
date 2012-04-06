package com.Acrobot.Breeze.Events.Cancelables;

/**
 * @author Acrobot
 */
public abstract class CancellableMessageEvent extends CancellableEvent {
    protected String message;

    /**
     * Sets the cancel message
     *
     * @param msg The message
     */
    public void setCancelMessage(String msg) {
        message = msg;

        if (msg == null || msg.isEmpty()){
            setResult(Result.DEFAULT);
        } else {
            setResult(Result.DENY);
        }
    }

    /**
     * @return The cancel message
     */
    public String getCancelMessage() {
        return message;
    }
}
