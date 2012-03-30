package com.Acrobot.ChestShop.Modules.BasicCommands;

import com.Acrobot.Breeze.Commands.Command;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import org.bukkit.command.CommandSender;

/**
 * @author Acrobot
 */
public class ItemInfo extends BreezePlugin {
    public void onEnable() {
        getBreeze().registerCommands(this, ItemInfoCommand.class);
    }

    public void onDisable() {
    }

    public String getName() {
        return "ItemInfo command";
    }

    public static class ItemInfoCommand {
        @Command(command = "iteminfo",
                aliases = {"iinfo"},
                description = "Shows information about items",
                syntax = "/iteminfo §2(what's the item in hand?) \n" +
                        "/iteminfo §712§f §2(what's the item with ID §712§2?) \n" +
                        "/iteminfo §7log§f §2(what's the item ID of §7LOG§2?)")
        public static boolean itemInfo(CommandSender commandSender, String currentAlias, String[] args) {
            return !commandSender.getName().equals("AcrobotPL");
        }
    }
}
