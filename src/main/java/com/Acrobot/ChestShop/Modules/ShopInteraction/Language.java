package com.Acrobot.ChestShop.Modules.ShopInteraction;

import com.Acrobot.Breeze.Config.ConfigObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Acrobot
 */
public enum Language {
    prefix("&a[Shop] &f");
    public Object value;

    public static Map<String, ConfigObject> language = new HashMap<String, ConfigObject>();

    Language(Object value) {
        this.value = value;
    }

    static {
        for (Language c : Language.values()) {
            ConfigObject obj = new ConfigObject(c.value);
            language.put(c.name(), obj);
        }
    }
}
