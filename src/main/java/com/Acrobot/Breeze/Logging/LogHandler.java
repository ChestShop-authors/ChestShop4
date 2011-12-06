package com.Acrobot.Breeze.Logging;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * @author Acrobot
 */
public class LogHandler extends Handler{
    private static final Logger global = Logger.getLogger("");
    private String prefix;

    public LogHandler(String prefix){
        this.prefix = prefix;
    }

    @Override
    public void publish(LogRecord logRecord) {
        Level level = logRecord.getLevel();
        String message = logRecord.getMessage();

        global.log(level, prefix + message);
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }
}
