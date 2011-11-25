package com.Acrobot.Breeze.Commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Acrobot
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Command {
    /**
     * @return The command
     */
    String command();

    /**
     * @return Aliases of the command
     */
    String[] aliases();

    /**
     * @return Command's description
     */
    String description();

    /**
     * @return Command's syntax
     */
    String syntax();
}
