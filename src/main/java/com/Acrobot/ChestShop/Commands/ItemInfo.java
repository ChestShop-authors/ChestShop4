package com.Acrobot.ChestShop.Commands;

import com.Acrobot.Breeze.Commands.Command;
import com.Acrobot.Breeze.Utilities.EventUtils;
import com.Acrobot.Breeze.Utilities.MaterialUtil;
import com.Acrobot.Breeze.Utilities.NumberUtil;
import com.Acrobot.Breeze.Utilities.StringUtil;
import com.Acrobot.ChestShop.Events.MessageEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * @author Acrobot
 */
public class ItemInfo {

    @Command(command = "iteminfo",
            aliases = {"iinfo"},
            description = "Shows information about items",
            syntax = "/iteminfo §2(what's the item in hand?) \n" +
                    "/iteminfo §712§f §2(what's the item with ID §712§2?) \n" +
                    "/iteminfo §7log§f §2(what's the item ID of §7LOG§2?)")

    public static boolean itemInfo(CommandSender sender, String currentAlias, String[] args) {
        ItemStack item;

        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                return false;
            }

            item = ((Player) sender).getItemInHand();
        } else {
            item = MaterialUtil.getItem(StringUtil.joinArray(args));
        }

        if (item == null || item.getType() == Material.AIR) {
            return false;
        }

        String durability = (item.getDurability() != 0 ? ChatColor.DARK_GREEN + ":" + item.getDurability() : "");

        String enchantmentString = MaterialUtil.Enchantments.encodeEnchantment(item.getEnchantments());
        String enchantment = (enchantmentString != null ? ChatColor.DARK_AQUA + "-" + enchantmentString : "");

        String itemName = MaterialUtil.getName(item);

        EventUtils.sendMessage(new MessageEvent("iteminfo"), sender);

        sender.sendMessage(ChatColor.GRAY + itemName + ChatColor.WHITE + "     " + item.getTypeId() + durability + enchantment);

        sendEnchantments(item.getEnchantments(), sender);

        return true;
    }

    private static void sendEnchantments(Map<Enchantment, Integer> enchantments, CommandSender sender) {
        for (Map.Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
            sender.sendMessage(ChatColor.DARK_GRAY
                    + StringUtil.capitalizeFirstLetter(enchantment.getKey().getName())
                    + ' '
                    + NumberUtil.numberToRoman(enchantment.getValue()));
        }
    }

}
