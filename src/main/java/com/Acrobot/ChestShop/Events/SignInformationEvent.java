package com.Acrobot.ChestShop.Events;

import org.bukkit.block.Sign;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author Acrobot
 */
public class SignInformationEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private Sign sign;
    private double buyPrice;
    private double sellPrice;
    private Object item;
    private Object owner;

    public SignInformationEvent(Sign sign) {
        this.sign = sign;
    }

    public Sign getSign() {
        return sign;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
