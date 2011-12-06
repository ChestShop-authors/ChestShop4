package com.Acrobot.Breeze.Logging;

import java.util.logging.*;

/**
 * @author Acrobot
 */
public class LogManager {

    /**
     * Initializes the logger
     *
     * @param log Logger
     * @return The same logger
     */
    public static Logger init(Logger log) {
        Handler handler = new LogHandler('[' + log.getName() + "] ");

        log.setUseParentHandlers(false);
        log.addHandler(handler);

        return log;
    }
}
