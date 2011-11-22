package com.Acrobot.ChestShop.Modules.TestModule;

import com.Acrobot.Breeze.Events.Event;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class TestListener extends PlayerListener {
    @Event(type = org.bukkit.event.Event.Type.PLAYER_JOIN)
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("Hey " + event.getPlayer().getName());
    }
}