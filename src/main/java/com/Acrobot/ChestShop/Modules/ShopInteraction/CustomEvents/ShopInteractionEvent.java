package com.Acrobot.ChestShop.Modules.ShopInteraction.CustomEvents;

import com.Acrobot.Breeze.Events.CustomEvents.CancellableEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

/**
 * @author Acrobot
 */
public class ShopInteractionEvent extends CancellableEvent {
    private String[] lines;
    private Action action;
    private Player player;

    public ShopInteractionEvent(String[] signText, Action action, Player player) {
        super("ShopInteraction");
        this.lines = signText;
        this.action = action;
        this.player = player;
    }

    public String[] getLines() {
        return lines;
    }

    public Action getAction() {
        return action;
    }

    public Player getPlayer() {
        return player;
    }
}
