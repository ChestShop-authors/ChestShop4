package com.Acrobot.ChestShop.Modules.ShopCreation.Events;

import com.Acrobot.Breeze.Events.Event;
import com.Acrobot.Breeze.Utils.NumberUtil;
import com.Acrobot.Breeze.Utils.StringUtil;
import com.Acrobot.ChestShop.Modules.Permission;
import com.Acrobot.ChestShop.Modules.ShopCreation.Language;
import com.Acrobot.ChestShop.Modules.ShopCreation.ShopCreation;
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
    private static final Pattern[] GoodPattern = {
            Pattern.compile("^\\\\w.+$"),
            Pattern.compile("[0-9]+"),
            Pattern.compile(".*B.*|.*S.*"),
            Pattern.compile("[\\w :]+")
    };

    private static final Pattern[] AlmostGoodPattern = {
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
        int index = (s.indexOf(':') != -1 ? s.indexOf(':') : 9999);
        if (s.indexOf('-') < index && s.indexOf('-') != -1) index = s.indexOf('-');

        StringBuilder toReturn = new StringBuilder(3);
        String matName = s.split(":|-")[0];
        matName = matName.trim();
        if (NumberUtil.isInteger(matName)) matName = Items.getName(is, false);
        int iPos = 15 - (s.length() - index);
        if (index != 9999 && matName.length() > iPos) matName = matName.substring(0, iPos);
        if (Items.getItemStack(matName).getType() == is.getType()) toReturn.append(matName);
        else toReturn.append(is.getTypeId());

        if (index != -1 && index != 9999) toReturn.append(s.substring(index));
        return StringUtil.capitalizeFirst(toReturn.toString(), ' ');
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
        return isCorrect(lines, GoodPattern);
    }

    private static boolean isCorrectPreparedSign(String[] lines) {
        return isCorrect(lines, AlmostGoodPattern);
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
