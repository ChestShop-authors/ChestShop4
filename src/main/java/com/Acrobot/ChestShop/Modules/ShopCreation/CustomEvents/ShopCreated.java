package com.Acrobot.ChestShop.Modules.ShopCreation.CustomEvents;

import com.Acrobot.Breeze.Events.CustomEvents.CancellableEvent;
import org.bukkit.entity.Player;

/**
 * @author Acrobot
 */
public class ShopCreated extends CancellableEvent {
    private String[] lines;
    private Player player;

    public ShopCreated(String[] lines, Player player) {
        super("ShopCreated");
        this.lines = lines;
        this.player = player;
    }

    public String[] getLines() {
        return lines;
    }

    public Player getPlayer() {
        return player;
    }
}
