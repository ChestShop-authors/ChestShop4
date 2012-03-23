package com.Acrobot.ChestShop.Modules.ShopInteraction;

import com.Acrobot.Breeze.Config.ConfigValue;

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

    public static Map<String, ConfigValue> config = new HashMap<String, ConfigValue>();

    Config(Object value, String text) {
        this.value = value;
        this.comment = text;
    }

    Config(Object value) {
        this.value = value;
    }

    static {
        for (Config c : Config.values()) {
            ConfigValue obj = (c.comment == null ? new ConfigValue(c.value) : new ConfigValue(c.value, c.comment));
            config.put(c.name(), obj);
        }
    }
}
