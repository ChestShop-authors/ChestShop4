package com.Acrobot.ChestShop.Events;

import com.Acrobot.Breeze.Events.Cancelables.CancellableEvent;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;

/**
 * @author Acrobot
 */
public class ShopSignClickEvent extends CancellableEvent {
    private static final HandlerList handlers = new HandlerList();

    private Action action;
    private Sign sign;
    private Player clicker;

    public ShopSignClickEvent(Action action, Sign sign, Player clicker) {
        this.action = action;
        this.sign = sign;
        this.clicker = clicker;
    }

    public Player getClicker() {
        return clicker;
    }

    public Action getAction() {
        return action;
    }

    public Sign getSign() {
        return sign;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
