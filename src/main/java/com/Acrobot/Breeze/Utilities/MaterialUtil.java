package com.Acrobot.Breeze.Utilities;

import org.bukkit.CoalType;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Acrobot
 */
public class MaterialUtil {
    private static final Pattern DURABILITY = Pattern.compile(":(\\d)*");
    private static final Pattern ENCHANTMENT = Pattern.compile("-([0-9a-zA-Z])*");

    /**
     * Gives you a Material from a String (doesn't have to be fully typed in)
     *
     * @param name Name of the material
     * @return Material found
     */
    public static Material getMaterial(String name) {
        Material material = Material.matchMaterial(name);

        if (material != null) {
            return material;
        }

        name = name.toUpperCase().replace(" ", "_");

        short length = 256;

        for (Material currentMaterial : Material.values()) {
            String matName = currentMaterial.name();

            if (matName.startsWith(name) && matName.length() < length) {
                length = (short) matName.length();
                material = currentMaterial;
            }
        }

        return material;
    }

    /**
     * Returns item's name
     *
     * @param itemStack ItemStack to name
     * @return ItemStack's name
     */
    public static String getName(ItemStack itemStack) {
        String dataName = DataValue.name(itemStack);

        if (dataName != null) {
            return StringUtil.capitalizeFirstLetter(dataName + '_' + itemStack.getType(), '_');
        } else {
            return StringUtil.capitalizeFirstLetter(itemStack.getType().toString(), '_');
        }
    }

    /**
     * Gives you an ItemStack from a String
     *
     * @param itemName Item name
     * @return ItemStack
     */
    public static ItemStack getItem(String itemName) {
        String[] split = itemName.trim().split(":|-");

        if (split.length == 0) {
            return null;
        }

        Material material = getMaterial(split[0]);

        if (material == null) {
            int index = split[0].indexOf(' ');
            if (index == -1) {
                return null;
            }

            material = getMaterial(split[0].substring(index + 1));

            if (material == null) {
                return null;
            }
        }

        ItemStack itemStack = new ItemStack(material, 1);

        short durability = getDurability(itemName);
        if (durability == 0) {
            String[] spaces = itemName.split(" ");
            if (spaces.length != 0) {
                durability = DataValue.get(spaces[0], material);
            }
        }
        itemStack.setDurability(durability);

        Map<Enchantment, Integer> enchantments = getEnchantments(itemName);

        if (!enchantments.isEmpty()) {
            itemStack.addEnchantments(enchantments);
        }

        return itemStack;
    }

    /**
     * Returns the durability from a string
     *
     * @param itemName Item name
     * @return Durability found
     */
    public static short getDurability(String itemName) {
        Matcher m = DURABILITY.matcher(itemName);

        if (!m.find()) {
            return 0;
        }

        String data = m.group();

        if (data == null || data.isEmpty()) {
            return 0;
        }

        data = data.substring(1);

        return NumberUtil.isInteger(data) ? Short.valueOf(data) : 0;
    }

    /**
     * Returns enchantments from a string
     *
     * @param itemName Item name
     * @return Enchantments found
     */
    public static Map<Enchantment, Integer> getEnchantments(String itemName) {
        Matcher m = ENCHANTMENT.matcher(itemName);

        if (!m.find()) {
            return new HashMap<Enchantment, Integer>();
        }

        String group = m.group().substring(1);
        return Enchantments.getEnchantment(group);
    }

    public static class Enchantments {
        /**
         * Returns enchantments this itemName contains
         *
         * @param base32 The encoded enchantment
         * @return Enchantments found
         */
        public static Map<Enchantment, Integer> getEnchantment(String base32) {
            if (base32 == null) {
                return new HashMap<Enchantment, Integer>();
            }

            Map<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();

            String integer = String.valueOf(Integer.parseInt(base32, 32));

            if (integer.length() < 3) {
                integer = '0' + integer;
            }

            for (int i = 0; i < integer.length() / 3; i++) {
                String item = integer.substring(i * 3, i * 3 + 3);

                Enchantment enchantment = Enchantment.getById(Integer.parseInt(item.substring(0, 2)));

                if (enchantment == null) {
                    continue;
                }

                int level = Integer.parseInt(item.substring(2));

                if (level > enchantment.getMaxLevel() || level < enchantment.getStartLevel()) {
                    continue;
                }

                map.put(enchantment, level);
            }

            return map;
        }

        /**
         * Encodes enchantments
         * They are being encoded in a string like XXL (XXLXXL), where L is the enchantment level and XX is the ID
         * Then the string is being encoded in base-32 string
         *
         * @param enchantments Enchantments to encode
         * @return Encoded enchantments
         */
        public static String encodeEnchantment(Map<Enchantment, Integer> enchantments) {
            int integer = 0;

            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                integer = integer * 1000 + (entry.getKey().getId()) * 10 + entry.getValue();
            }

            return integer != 0 ? Integer.toString(integer, 32) : null;
        }
    }

    public static class DataValue {
        /**
         * Gets the data value from a string
         *
         * @param type     Data Value string
         * @param material Material
         * @return data value
         */
        public static byte get(String type, Material material) {
            if (material == null || material.getData() == null) {
                return 0;
            }

            type = type.toUpperCase().replace(" ", "_");

            MaterialData materialData = material.getNewData((byte) 0);

            if (materialData instanceof TexturedMaterial) {
                TexturedMaterial texturedMaterial = (TexturedMaterial) materialData;

                for (Material mat : texturedMaterial.getTextures()) {
                    if (mat.name().startsWith(type)) {
                        return (byte) texturedMaterial.getTextures().indexOf(mat);
                    }
                }
            } else if (materialData instanceof Colorable) {
                DyeColor color;

                try {
                    color = DyeColor.valueOf(type);
                } catch (IllegalArgumentException exception) {
                    return 0;
                }

                if (material == Material.INK_SACK) {
                    color = DyeColor.getByData((byte) (15 - color.getData()));
                }

                return color.getData();
            } else if (material == Material.COAL) {
                try {
                    return CoalType.valueOf(type).getData();
                } catch (IllegalArgumentException ex) {
                    return 0;
                }
            }

            return 0;
        }

        /**
         * Returns a string with the DataValue
         *
         * @param itemStack ItemStack to describe
         * @return Data value string
         */
        public static String name(ItemStack itemStack) {
            MaterialData data = itemStack.getData();

            if (data == null) {
                return null;
            }

            if (data instanceof TexturedMaterial) {
                return ((TexturedMaterial) data).getMaterial().name();
            } else if (data instanceof Colorable) {
                return ((Colorable) data).getColor().name();
            } else if (data instanceof Tree) {
                //TreeSpecies specie = TreeSpecies.getByData((byte) (data.getData() & 3)); //This works, but not as intended
                TreeSpecies specie = ((Tree) data).getSpecies();
                return (specie != null && specie != TreeSpecies.GENERIC ? specie.name() : null);
            } else if (data instanceof Coal) {
                CoalType coal = ((Coal) data).getType();
                return (coal != CoalType.COAL ? coal.name() : null);
            } else {
                return null;
            }
        }
    }
}
