package com.Acrobot.Breeze.Config;

/**
 * @author Acrobot
 */
public class ConfigValue {
    public Object value;
    public String comment;

    public ConfigValue(Object value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    public ConfigValue(Object value) {
        this.value = value;
    }

    /**
     * Retrieves the value of that ConfigValue
     *
     * @return The value
     */
    public String retrieveValue() {
        StringBuilder toReturn = new StringBuilder();

        if (value instanceof Number || value instanceof Boolean) toReturn.append(String.valueOf(value));
        else toReturn.append('\"').append(String.valueOf(value)).append('\"');

        if (comment != null) toReturn.append('\n').append('#').append(comment);
        return toReturn.toString();
    }
}
