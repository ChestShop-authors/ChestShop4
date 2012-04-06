package com.Acrobot.ChestShop.Events;

import com.Acrobot.Breeze.Events.Cancelables.CancellableEvent;
import org.bukkit.event.HandlerList;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Acrobot
 */
public class TransactionEvent extends CancellableEvent {
    private static final HandlerList handlers = new HandlerList();

    private Type type;
    private Object item;
    private Object price;
    private Object owner;
    private Object client;
    private Object origin;

    private Map<String, Object> metadata = new HashMap<String, Object>();

    public TransactionEvent(Type type, Object item, Object price, Object owner, Object client, Object origin) {
        this.type = type;
        this.item = item;
        this.price = price;
        this.owner = owner;
        this.origin = origin;
        this.client = client;
    }

    public Type getType() {
        return type;
    }

    public Object getItem() {
        return item;
    }

    public Object getPrice() {
        return price;
    }

    public Object getOwner() {
        return owner;
    }

    public Object getOrigin() {
        return origin;
    }

    public Object getClient() {
        return client;
    }

    public Object getMetadata(String name) {
        return metadata.get(name);
    }

    public void setMetadata(String name, Object value) {
        metadata.put(name, value);
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public static enum Type {
        BUY,
        SELL
    }
}
