package com.Acrobot.ChestShop.Modules.ShopInteraction;

import com.Acrobot.Breeze.Config.ConfigValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Acrobot
 */
public enum Language {
    prefix("&a[Shop] &f");
    public Object value;

    public static Map<String, ConfigValue> language = new HashMap<String, ConfigValue>();

    Language(Object value) {
        this.value = value;
    }

    static {
        for (Language c : Language.values()) {
            ConfigValue obj = new ConfigValue(c.value);
            language.put(c.name(), obj);
        }
    }
}
