package com.Acrobot.Breeze.Utilities;

/**
 * @author Acrobot
 */
public class NumberUtil {
    /**
     * Checks if the string is a integer
     *
     * @param string string to check
     * @return Is the string integer?
     */
    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the string is a float
     *
     * @param string string to check
     * @return Is the string float?
     */
    public static boolean isFloat(String string) {
        try {
            Float.parseFloat(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the string is a double
     *
     * @param string string to check
     * @return Is the string double?
     */
    public static boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Converts a number to roman
     *
     * @param number number to convert
     * @return Converted number
     */
    public static String numberToRoman(int number) {
        if (number < 1 || number > 9) {
            throw new IllegalArgumentException("The number must be in range 1-9 (This is only for enchantment level decoration)");
        }

        switch (number) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            default:
                return Integer.toString(number);
        }
    }
}
