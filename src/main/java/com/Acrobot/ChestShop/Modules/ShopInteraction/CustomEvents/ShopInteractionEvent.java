package com.Acrobot.ChestShop.Modules.ShopInteraction.CustomEvents;

import com.Acrobot.Breeze.Events.Cancelables.CancellableEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

/**
 * @author Acrobot
 */
public class ShopInteractionEvent extends CancellableEvent {
    private String[] lines;
    private Action action;
    private Player player;
    private boolean buy;

    public ShopInteractionEvent(String[] signText, Action action, Player player, boolean buy) {
        super("ShopInteraction");
        this.lines = signText;
        this.action = action;
        this.player = player;
        this.buy = buy;
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

    public boolean isBuy(){
        return buy;
    }

    public void setBuy(boolean buy){
        this.buy = buy;
    }
}
