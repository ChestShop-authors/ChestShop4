package com.Acrobot.Breeze.Utils;

/**
 * @author Acrobot
 */
public class StringUtil {
    /**
     * Capitalizes every first letter of a word
     * @param string String to reformat
     * @param separator Word separator
     * @return Reformatted string
     */
    public static String capitalizeFirstLetter(String string, char separator) {
        string = string.toLowerCase();

        String[] split = string.split(Character.toString(separator));
        StringBuilder total = new StringBuilder(3);

        for (String s : split) total.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).append(' ');

        return total.toString().trim();
    }

    /**
     * Capitalizes every first letter of a word
     * @param string String to reformat
     * @see StringUtil#capitalizeFirstLetter(String, char)
     * @return Reformatted string
     */
    public static String capitalizeFirstLetter(String string){
        return capitalizeFirstLetter(string, ' ');
    }
}
