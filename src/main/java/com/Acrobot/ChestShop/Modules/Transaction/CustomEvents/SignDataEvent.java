package com.Acrobot.ChestShop.Modules.Transaction.CustomEvents;

import org.bukkit.event.Event;

/**
 * @author Acrobot
 */
public class SignDataEvent extends Event {
    private double buyPrice;
    private double sellPrice;
    private String ownerName;
    private Object item;

    public SignDataEvent(String[] lines) {
        super("SignDataEvent");
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
