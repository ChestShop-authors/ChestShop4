package com.Acrobot.ChestShop.Modules.ShopCreation.CustomEvents;

import com.Acrobot.Breeze.Events.CustomEvents.CancellableEvent;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

/**
 * @author Acrobot
 */
public class SignCreated extends CancellableEvent {
    private Sign sign;
    private Player player;

    public SignCreated(Sign sign, Player player) {
        super("SignCreated");
        this.sign = sign;
        this.player = player;
    }

    public Sign getSign() {
        return sign;
    }

    public Player getPlayer() {
        return player;
    }
}
