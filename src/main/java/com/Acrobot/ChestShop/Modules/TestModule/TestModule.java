package com.Acrobot.ChestShop.Modules.TestModule;

import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;

/**
 * @author Acrobot
 */
public class TestModule extends BreezePlugin {
    public void onEnable() {
        getBreeze().registerEvents(TestListener.class);
        getBreeze().registerCommands(TestCommand.class);
    }

    public void onDisable() {
    }

    public String getName() {
        return "TestModule";
    }
}
