package com.Acrobot.ChestShop.Modules.ShopInteraction.CustomEvents;

import com.Acrobot.Breeze.Events.CustomEvents.CancellableEvent;

/**
 * @author Acrobot
 */
public class BuyEvent extends CancellableEvent{
    public BuyEvent() {
        super("BuyEvent");
    }
}
