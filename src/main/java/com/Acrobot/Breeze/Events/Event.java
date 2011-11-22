package com.Acrobot.Breeze.Events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Acrobot
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Event {
    org.bukkit.event.Event.Type type();
    org.bukkit.event.Event.Priority priority() default org.bukkit.event.Event.Priority.Normal;
}
