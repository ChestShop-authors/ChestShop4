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

        if (time.containsKey(p) && (time.get(p) - System.currentTimeMillis()) < 200) {
            event.setCancelled(true);
            return;
        }

        if (!correctSign(event.getLines())){
            event.setCancelled(true);
            event.setCancelMessage("Asd"); //TODO: Add the stuff from config
        }
    }

    /**
     * Is the sign correct?
     * @param lines The sign lines
     * @return Is the sign correct
     */
    private static boolean correctSign(String[] lines) {
        boolean right = true;
        for (int i = 0; i < 4 && right; i++){
            right = patterns[i].matcher(lines[i]).matches();
            System.out.println("Pattern: " + patterns[i].pattern() + " matches? " + lines[i] + " ? " + patterns[i].matcher(lines[i]).matches()); //TODO Change that
        }
        return right;
    }
}
