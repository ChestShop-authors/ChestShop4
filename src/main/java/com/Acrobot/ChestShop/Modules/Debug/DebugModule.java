package com.Acrobot.ChestShop.Modules.Debug;

import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.ChestShop.Events.SignInformationEvent;
import com.Acrobot.ChestShop.Events.TransactionEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Acrobot
 */
public class DebugModule extends BreezePlugin {
    static int counter;
    static long time;

    public void onEnable() {
        getBreeze().registerEvents(this, new SignInfo());
    }

    public void onDisable() {
    }

    public static class SignInfo implements Listener {
        //@EventHandler
        public static void signClick(PlayerInteractEvent event) {
            if (!event.hasBlock() || !(event.getClickedBlock().getState() instanceof Sign)) {
                return;
            }

            SignInformationEvent evt = new SignInformationEvent((Sign) event.getClickedBlock().getState());

            Bukkit.getPluginManager().callEvent(evt);

            event.getPlayer().sendMessage("This sign belongs to " + evt.getOwner());
            event.getPlayer().sendMessage("It sells " + ((ItemStack) evt.getItem()).getAmount() + ' ' + ((ItemStack) evt.getItem()).getType());
            event.getPlayer().sendMessage("The prices are: buy for " + evt.getBuyPrice() + ", sell for " + evt.getSellPrice());
        }

        //@EventHandler
        public static void transaction(TransactionEvent event) {
            if (!(event.getClient() instanceof Player)) {
                return;
            }

            Player player = (Player) event.getClient();

            player.sendMessage("There was a transaction!");
            player.sendMessage(event.getOwner() + " " + event.getType().name() + " " + event.getItem());
            player.sendMessage("for " + event.getPrice());
        }

        @EventHandler
        public static void transactions(TransactionEvent event) {
            counter++;

            if (System.currentTimeMillis() - time > 1000) {
                ((Player) event.getClient()).sendMessage("There were " + counter + " transactions.");
                time = System.currentTimeMillis();
                counter = 0;
            }
        }
    }
}
