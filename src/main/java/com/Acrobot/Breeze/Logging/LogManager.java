package com.Acrobot.Breeze.Logging;

import java.util.logging.Logger;

/**
 * @author Acrobot
 */
public class LogManager {
    /**
     * Initializes log formatter
     *
     * @param log Logger
     * @return The same logger
     */
    public static Logger init(Logger log) {
        LogFormatter formatter = new LogFormatter('[' + log.getName() + "] ");

        log.setParent(Logger.getLogger("Minecraft"));

        log.getHandlers()[0].setFormatter(formatter); //Probably a bad idea, we'll see.
        //TODO: Change the thing above if it won't work

        return log;
    }
}
