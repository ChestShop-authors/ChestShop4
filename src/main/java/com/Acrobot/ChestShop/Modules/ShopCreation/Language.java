package com.Acrobot.ChestShop.Modules.ShopCreation;

import com.Acrobot.Breeze.Config.ConfigObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Acrobot
 */
public enum Language {
    YOU_CANNOT_CREATE_SHOP("You can't create this type of shop!");
    
    public String text;
    public static Map<String, ConfigObject> language = new HashMap<String, ConfigObject>();
    
    Language(String text){
        this.text = text;
    }
    
    static {
        for (Language l : Language.values()){
            language.put(l.name(), new ConfigObject(l.text));
        }
    }
}
