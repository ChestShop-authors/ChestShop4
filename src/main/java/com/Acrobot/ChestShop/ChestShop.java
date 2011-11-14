package com.Acrobot.ChestShop;

import com.Acrobot.Breeze.Breeze;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Acrobot
 */
public class ChestShop extends JavaPlugin {

    private static Breeze breeze;

    /**
     * Called on plugin enable
     */
    public void onEnable() {
        breeze = new Breeze(this);
    }

    /**
     * Called on plugin disable
     */
    public void onDisable() {
    }

    /**
     * Gets a Breeze instance
     * @return breeze instance
     */
    public static Breeze getBreeze() {
        return breeze;
    }
}
