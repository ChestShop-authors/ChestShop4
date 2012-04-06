package com.Acrobot.ChestShop.Modules.SignModule;

import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.Breeze.Utilities.MaterialUtil;
import com.Acrobot.Breeze.Utilities.NumberUtil;
import com.Acrobot.ChestShop.Events.ShopSignClickEvent;
import com.Acrobot.ChestShop.Events.SignInformationEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.regex.Pattern;

/**
 * @author Acrobot
 */
public class SignModule extends BreezePlugin {

    private static final Pattern[] SIGN_PATTERN = {
            Pattern.compile("^$|^\\w.+$"),
            Pattern.compile("[0-9]+"),
            Pattern.compile(".+"),
            Pattern.compile("[\\w : -]+")
    };

    public void onEnable() {
        getBreeze().registerEvents(this, new SignListener());
    }

    public void onDisable() {
    }

    public static class SignListener implements Listener {
        @EventHandler
        public static void onClick(PlayerInteractEvent event) {
            if (!event.hasBlock() || !(event.getClickedBlock().getState() instanceof Sign)) {
                return;
            }

            if (!isRightSign((Sign) event.getClickedBlock().getState())) {
                return;
            }

            Bukkit.getPluginManager().callEvent(
                    new ShopSignClickEvent(event.getAction(), (Sign) event.getClickedBlock().getState(), event.getPlayer()));
        }

        @EventHandler(priority = EventPriority.LOW)
        public static void onSign(SignInformationEvent event) {
            if (!isRightSign(event.getSign())) {
                return;
            }

            if (event.getOwner() == null) {
                event.setOwner(event.getSign().getLine(0));
            }

            if (event.getItem() == null) {
                ItemStack item = MaterialUtil.getItem(event.getSign().getLine(3));
                String amount = event.getSign().getLine(1).replaceAll("[0-9]", "");

                if (NumberUtil.isInteger(amount)) {
                    item.setAmount(Integer.parseInt(amount));
                }

                event.setItem(item);
            }

            if (event.getBuyPrice() == 0) {
                event.setBuyPrice(getPrice(event.getSign().getLine(2), 'b'));
            }

            if (event.getSellPrice() == 0) {
                event.setSellPrice(getPrice(event.getSign().getLine(2), 's'));
            }
        }

        private static boolean isRightSign(Sign sign) {
            for (int i = 0; i < 3; i++) {
                if (!SIGN_PATTERN[i].matcher(sign.getLine(i)).matches()) {
                    return false;
                }
            }

            return true;
        }

        private static double getPrice(String string, char price) {
            String[] split = string.toLowerCase().split(":");
            price = Character.toLowerCase(price);

            for (String str : split) {
                if (!str.contains(price + "")) {
                    continue;
                }

                str = str.replaceAll("b|s", "").replace(" ", "").trim();

                if (str.equals("free")) {
                    return 0D;
                } else if (NumberUtil.isDouble(str)) {
                    return Double.parseDouble(str);
                }
            }

            return -1;
        }
    }
}
