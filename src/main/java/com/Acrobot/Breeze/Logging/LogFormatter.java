package com.Acrobot.Breeze.Logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author Acrobot
 */
public class LogFormatter extends Formatter {
    String prefix;

    public LogFormatter(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Formats the Breeze log
     *
     * @param logRecord log record
     * @return formatter message
     */
    @Override
    public String format(LogRecord logRecord) {
        StringBuilder sb = new StringBuilder();

        sb.append(prefix);
        sb.append(logRecord.getMessage());

        return sb.toString();
    }
}
