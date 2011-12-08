package com.Acrobot.ChestShop.Modules.ShopCreation.Events;

import com.Acrobot.Breeze.Events.Event;
import com.Acrobot.ChestShop.Modules.Permission;
import com.Acrobot.ChestShop.Modules.ShopCreation.Language;
import com.Acrobot.ChestShop.Modules.ShopCreation.ShopCreation;
import com.Acrobot.ChestShop.Utils.NumberUtil;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.material.Sign;

import java.util.regex.Pattern;

/**
 * @author Acrobot
 */
public class SignPlaced extends BlockListener {
    private static final Pattern[] goodPattern = {
            Pattern.compile("^\\\\w.+$"),
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

    @Event(type = org.bukkit.event.Event.Type.SIGN_CHANGE, priority = org.bukkit.event.Event.Priority.High)
    public void onSignChange(SignChangeEvent event) {
        if (event.isCancelled()) return;

        Block block = event.getBlock();
        if (!isSign(block)) return;

        String[] line = event.getLines();
        if (!isCorrectPreparedSign(line)) return;

        Player player = event.getPlayer();
        if (!Permission.has(player, Permission.SHOP_CREATION)) {
            event.setCancelled(true);
            player.sendMessage(getLanguage(Language.YOU_CANNOT_CREATE_SHOP));
            return;
        }

        event.setLine(0, formatFirstLine(line[0], player)); //Set the first line of a sign
        event.setLine(3, formatFourthLine(line[3]));
    }

    private String formatFourthLine(String s) {
        s = s.replace("_", " ");
        String[] split = s.split(":");
        String first = split[0];

        if (NumberUtil.isInteger(s)); //TODO Finish this
    }

    /**
     * Can the player make a shop for another player?
     * @param s Shop's first line
     * @param p Player
     * @return The first line of a sign
     */
    private String formatFirstLine(String s, Player p) {
        if ("".equals(s) || !canCreateOtherName(s, p)) return stripName(p.getName());
        return s;
    }

    private boolean canCreateOtherName(String s, Player p) {
        return Permission.has(p, Permission.ADMIN) ||
                Permission.has(p, Permission.OTHER_NAME + s);
    }

    private String getLanguage(Language l) {
        return ShopCreation.language.getString(l.name());
    }

    private String stripName(String name) {
        if (name.length() > 15) return name.substring(15);
        return name;
    }

    private static boolean isCorrectSign(String[] lines) {
        return isCorrect(lines, goodPattern);
    }

    private static boolean isCorrectPreparedSign(String[] lines) {
        return isCorrect(lines, almostGoodPattern);
    }

    private static boolean isSign(Block b) {
        return b.getState() instanceof Sign;
    }

    private static boolean isCorrect(String[] lines, Pattern[] patterns) {
        boolean right = true;
        for (int i = 0; i < 4 && right; i++) right = patterns[i].matcher(lines[i]).matches();
        return right;
    }
}
