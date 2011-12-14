package com.Acrobot.ChestShop.Modules.ShopInteraction;

import com.Acrobot.Breeze.Config.ConfigObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Acrobot
 */
public enum Config {
    SHOP_INTERACTION_INTERVAL(150, "How often should the player be allowed to buy/sell items? (In 1/1000th of second)"),
    REVERSE_BUTTONS(false, "If true, people will buy with left-click and sell with right-click.");

    private Object value;
    private String comment;

    public static Map<String, ConfigObject> config = new HashMap<String, ConfigObject>();

    Config(Object value, String text) {
        this.value = value;
        this.comment = text;
    }

    Config(Object value) {
        this.value = value;
    }

    static {
        for (Config c : Config.values()) {
            ConfigObject obj = (c.comment == null ? new ConfigObject(c.value) : new ConfigObject(c.value, c.comment));
            config.put(c.name(), obj);
        }
    }
}
