package com.Acrobot.ChestShop.Modules.ShopInteraction.CustomEvents;

import com.Acrobot.Breeze.Events.Cancelables.CancellableEvent;
import org.bukkit.entity.Player;

/**
 * @author Acrobot
 */
public class BuyEvent extends CancellableEvent {
    private Object object;
    private double price;
    private String owner;
    private Player buyer;
    private Object items;

    public BuyEvent(double price, String owner, Player buyer, Object items, Object cause) {
        super("BuyEvent");
        this.object = cause;
        this.price = price;
        this.owner = owner;
        this.buyer = buyer;
        this.items = items;
    }

    public Object getObject() {
        return object;
    }

    public double getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }

    public Player getBuyer() {
        return buyer;
    }

    public Object getItems() {
        return items;
    }
}

