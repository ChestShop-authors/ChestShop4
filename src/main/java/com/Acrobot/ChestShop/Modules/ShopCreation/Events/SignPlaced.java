package com.Acrobot.ChestShop.Modules.ShopCreation.Events;

import com.Acrobot.Breeze.Events.Event;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.SignChangeEvent;

import java.util.regex.Pattern;

/**
 * @author Acrobot
 */
public class SignPlaced extends BlockListener {
    private static final Pattern[] goodPattern = {
            Pattern.compile("^$|^\\\\w.+$"),
            Pattern.compile("[0-9]+"),
            Pattern.compile(".*B.*|.*S.*"),
            Pattern.compile("[\\w :]+")
    };

    private static final Pattern[] almostGoodPattern = {
            Pattern.compile("^$|^\\\\w.+$"),
            Pattern.compile("[0-9]+"),
            Pattern.compile(".*"),
            Pattern.compile("[\\w :]+")
    };

    @Event(type = org.bukkit.event.Event.Type.SIGN_CHANGE, priority = org.bukkit.event.Event.Priority.Normal)
    public void onSignChange(SignChangeEvent event) {

    }

    private static boolean isCorrectSign(String[] lines) {
        return isCorrect(lines, goodPattern);
    }

    private static boolean isCorrectPreparedSign(String[] lines) {
        return isCorrect(lines, almostGoodPattern);
    }

    private static boolean isCorrect(String[] lines, Pattern[] patterns) {
        boolean right = true;
        for (int i = 0; i < 4 && right; i++) right = patterns[i].matcher(lines[i]).matches();
        return right;
    }
}
