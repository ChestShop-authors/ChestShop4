package com.Acrobot.ChestShop.Modules.ShopInteraction.Events;

import com.Acrobot.Breeze.Events.Event;
import com.Acrobot.ChestShop.Modules.ShopInteraction.Config;
import com.Acrobot.ChestShop.Modules.ShopInteraction.CustomEvents.ShopInteractionEvent;
import com.Acrobot.ChestShop.Modules.ShopInteraction.ShopInteraction;
import org.bukkit.entity.Player;
import org.bukkit.event.CustomEventListener;
import org.bukkit.event.block.Action;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Acrobot
 */
public class ShopInteract extends CustomEventListener {
    private static Map<Player, Long> time = new HashMap<Player, Long>();
    private static final Pattern[] Patterns = {
            Pattern.compile("^$|^\\\\w.+$"),
            Pattern.compile("[0-9]+"),
            Pattern.compile(".*B.*|.*S.*"),
            Pattern.compile("[\\w :]+")
    };

    @Event(type = org.bukkit.event.Event.Type.CUSTOM_EVENT, priority = org.bukkit.event.Event.Priority.Low)
    public void onCustomEvent(org.bukkit.event.Event e) {
        if (!(e instanceof ShopInteractionEvent)) return;
        ShopInteractionEvent event = (ShopInteractionEvent) e;
        if (event.isCancelled()) return;

        Player p = event.getPlayer();

        if (!checkTime(p, event)) return; //Check the last time the shop was used
        Action a = event.getAction();
        if (!correctSign(event.getLines()) || (a != Action.LEFT_CLICK_BLOCK && a != Action.RIGHT_CLICK_BLOCK)){
            event.setCancelled(true);
            return;
        }
        boolean reverse = ShopInteraction.config.getBoolean(Config.REVERSE_BUTTONS.name());
        event.setBuy(reverse ? a == Action.LEFT_CLICK_BLOCK : a == Action.RIGHT_CLICK_BLOCK);
    }

    /**
     * Is the sign correct?
     *
     * @param lines The sign lines
     * @return Is the sign correct
     */
    private static boolean correctSign(String[] lines) {
        boolean right = true;
        for (int i = 0; i < 4 && right; i++) right = Patterns[i].matcher(lines[i]).matches();
        return right;
    }

    private static boolean checkTime(Player p, ShopInteractionEvent e) {
        if (time.containsKey(p) && (System.currentTimeMillis() - time.get(p)) < 200) {
            e.setCancelled(true);
            return false;
        }
        time.put(p, System.currentTimeMillis());

        return true;
    }
}
