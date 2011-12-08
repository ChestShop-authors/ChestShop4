package com.Acrobot.ChestShop.Modules.ShopInteraction.Events;

import com.Acrobot.Breeze.Events.Event;
import com.Acrobot.ChestShop.ChestShop;
import com.Acrobot.ChestShop.Modules.ShopInteraction.CustomEvents.ShopInteractionEvent;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
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

        ShopInteractionEvent e = new ShopInteractionEvent(sign.getLines(), action, event.getPlayer());
        ChestShop.callEvent(e);
        String message = e.getCancelMessage();
        if (e.isCancelled() && message != null) event.getPlayer().sendMessage(message);
    }
}
