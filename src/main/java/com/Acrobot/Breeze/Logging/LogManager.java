package com.Acrobot.Breeze.Logging;

import java.util.logging.*;

/**
 * @author Acrobot
 */
public class LogManager {
    private final LogFormatter formatter = null;

    /**
     * Initializes log formatter
     *
     * @param log Logger
     * @return The same logger
     */
    public static Logger init(Logger log) {
        final LogFormatter formatter = new LogFormatter('[' + log.getName() + "] ");
        
        Handler handler = new LogHandler();
        handler.setFormatter(formatter);

        log.addHandler(handler);
        log.setUseParentHandlers(false);

        return log;
    }
}
