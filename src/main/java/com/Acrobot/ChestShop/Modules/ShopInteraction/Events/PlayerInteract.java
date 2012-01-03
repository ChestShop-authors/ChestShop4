package com.Acrobot.ChestShop.Modules.ShopInteraction.Events;

import com.Acrobot.Breeze.Events.Event;
import com.Acrobot.ChestShop.ChestShop;
import com.Acrobot.ChestShop.Modules.ShopInteraction.CustomEvents.BuyEvent;
import com.Acrobot.ChestShop.Modules.ShopInteraction.CustomEvents.SellEvent;
import com.Acrobot.ChestShop.Modules.ShopInteraction.CustomEvents.ShopInteractionEvent;
import com.Acrobot.ChestShop.Modules.Transaction.CustomEvents.SignDataEvent;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

/**
 * @author Acrobot
 */
public class PlayerInteract extends PlayerListener {

    @Event(type = org.bukkit.event.Event.Type.PLAYER_INTERACT)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null || !(block.getState() instanceof Sign)) return;

        Sign sign = (Sign) block.getState();
        Action action = event.getAction();

        ShopInteractionEvent e = new ShopInteractionEvent(sign.getLines(), action, event.getPlayer(), false);
        ChestShop.callEvent(e);
        String message = e.getCancelMessage();
        if (e.isCancelled() && message != null){
            event.getPlayer().sendMessage(message);
            return;
        }


        if (e.isBuy()) ChestShop.callEvent(formatBuyEvent(sign, event.getPlayer()));
        else ChestShop.callEvent(formatSellEvent(sign, event.getPlayer()));
    }
    
    private static BuyEvent formatBuyEvent(Sign sign, Player p){
        return (BuyEvent) formatEvent(sign, p, true);
    }
    
    private static SellEvent formatSellEvent(Sign sign, Player p){
        return (SellEvent) formatEvent(sign, p, false);
    }

    /**
     * Format a new Buy/SellEvent
     * @param sign The sign, which caused the event
     * @param p Player who touched the sign
     * @param buy Whether this action was a buy or sell event
     * @return Event
     */
    private static org.bukkit.event.Event formatEvent(Sign sign, Player p, boolean buy){
        SignDataEvent evt = new SignDataEvent(sign.getLines());
        ChestShop.callEvent(evt);
        return (buy ? 
                new BuyEvent (evt.getBuyPrice(), evt.getOwnerName(), p, evt.getItem(), sign) :
                new SellEvent(evt.getSellPrice(), evt.getOwnerName(), p, evt.getItem(), sign));
    }
}
