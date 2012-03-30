package com.Acrobot.ChestShop;

import org.bukkit.entity.Player;

/**
 * @author Acrobot
 */
public enum Permission {
    SHOP_CREATION_BUY("ChestShop.shop.create.buy"),
    SHOP_CREATION_SELL("ChestShop.shop.create.sell"),

    SHOP_CREATION_ID("ChestShop.shop.create."),

    BUY("ChestShop.shop.buy"),
    BUY_ID("ChestShop.shop.buy."),

    SELL_ID("ChestShop.shop.sell."),
    SELL("ChestShop.shop.sell"),

    ADMIN("ChestShop.admin"),
    MOD("ChestShop.mod"),
    NOFEE("ChestShop.nofee");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public static boolean has(Player player, Permission permission) {
        return has(player, permission.permission);
    }

    public static boolean has(Player player, String node) {
        return player.hasPermission(node) || player.hasPermission(node.toLowerCase());
    }

    public String toString() {
        return permission;
    }
}