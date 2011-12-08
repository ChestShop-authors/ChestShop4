package com.Acrobot.ChestShop.Modules.ShopInteraction.Events;

import com.Acrobot.Breeze.Events.Event;
import com.Acrobot.ChestShop.Modules.ShopInteraction.CustomEvents.ShopInteractionEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.CustomEventListener;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * @author Acrobot
 */
public class ShopInteract extends CustomEventListener {
    private static HashMap<Player, Long> time = new HashMap<Player, Long>();
    private static final Pattern[] patterns = {
            Pattern.compile("^$|^\\\\w.+$"),
            Pattern.compile("[0-9]+"),
            Pattern.compile(".*B.*|.*S.*"),
            Pattern.compile("[\\w :]+")
    };

    @Event(type = org.bukkit.event.Event.Type.CUSTOM_EVENT, priority = org.bukkit.event.Event.Priority.Lowest)
    public void onCustomEvent(org.bukkit.event.Event e) {
        if (!(e instanceof ShopInteractionEvent)) return;
        ShopInteractionEvent event = (ShopInteractionEvent) e;
        Player p = event.getPlayer();

        p.sendMessage("It actually works!");

        if (!checkTime(p, event)) return; //Check the last time the shop was used

        if (!correctSign(event.getLines())) {
            p.sendMessage("Hey! That's an incorrect sign!"); //TODO Delete this
            event.setCancelled(true);
            return;
        }
    }

    /**
     * Is the sign correct?
     *
     * @param lines The sign lines
     * @return Is the sign correct
     */
    private static boolean correctSign(String[] lines) {
        boolean right = true;
        for (int i = 0; i < 4 && right; i++) right = patterns[i].matcher(lines[i]).matches();
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
