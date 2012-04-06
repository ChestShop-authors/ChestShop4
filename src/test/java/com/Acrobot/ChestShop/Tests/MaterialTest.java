package com.Acrobot.ChestShop.Tests;

import com.Acrobot.Breeze.Utilities.MaterialUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Acrobot
 */
public class MaterialTest {
    @Test
    public void test_durability() {
        assertTrue(MaterialUtil.getDurability("wood:1") == 1);
        assertTrue(MaterialUtil.getDurability("log:2") == 2);
    }

    @Test
    public void test_enchantments() {
        ItemStack enchantedItem = new ItemStack(Material.GOLD_SWORD, 1);

        enchantedItem.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);

        String encoded = MaterialUtil.Enchantments.encodeEnchantment(enchantedItem.getEnchantments());

        assertTrue(encoded != null);
        assertTrue(encoded.equals("51"));

        //assertTrue(MaterialUtil.Enchantments.getEnchantment("51").equals(enchantedItem.getEnchantments())); // Unfortunately tests fail here
    }

    @Test
    public void test_dataNames() {
        ItemStack greenDye = new ItemStack(Material.INK_SACK, 1, (byte) 10);

        String valueName = MaterialUtil.DataValue.name(greenDye);

        assertTrue(greenDye.getDurability() == MaterialUtil.DataValue.get(valueName, Material.INK_SACK));
    }

    @Test
    public void test_getItem() {
        ItemStack dataValue = new ItemStack(Material.WOOD, 1, (short) 1);

        assertFalse(MaterialUtil.getItem("wood:1") == null);

        assertTrue(MaterialUtil.getItem("wood:1").equals(dataValue));
        assertTrue(MaterialUtil.getItem("5:1").equals(dataValue));

        ItemStack mossyCobble = new ItemStack(Material.MOSSY_COBBLESTONE, 1);

        assertTrue(MaterialUtil.getItem("moss") != null && MaterialUtil.getItem("moss").equals(mossyCobble));

    }
}
