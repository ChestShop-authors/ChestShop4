package com.Acrobot.Breeze.Commands;

import com.Acrobot.Breeze.Breeze;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Acrobot
 */
public class CommandManager {
    private SimpleCommandMap scm;
    private Breeze br;

    public CommandManager(Breeze br) {
        this.br = br;
        try {
            Field f = br.getPlugin().getServer().getClass().getField("commandMap");
            f.setAccessible(true);
            scm = (SimpleCommandMap) f.get(br.getPlugin().getServer());
        } catch (Exception e) {
            br.logger.severe("It seems like you aren't using CraftBukkit o.0");
        }
    }

    /**
     * Registers a command
     *
     * @param command Bukkit command
     * @return was the registering successful
     */
    private boolean registerCommand(org.bukkit.command.Command command) {
        return scm.getCommand(command.getName()) != null && scm.register(br.getPlugin().getDescription().getName().toLowerCase(), command);
    }

    /**
     * Registers a command class
     *
     * @param clazz the command class
     */
    public void registerCommand(Class clazz) {
        for (final Method m : clazz.getMethods()) {
            if (!m.isAnnotationPresent(Command.class)) continue; //If there is no command annotation, skip this method

            final Command command = m.getAnnotation(Command.class); //Get command

            org.bukkit.command.Command cmd = new org.bukkit.command.Command( //Create a new bukkit command
                    command.command(),
                    command.description(),
                    command.syntax(),
                    Arrays.asList(command.aliases())) {

                @Override
                public boolean execute(CommandSender commandSender, String currentAlias, String[] args) {
                    try {
                        return (Boolean) m.invoke(null, commandSender, currentAlias, args);
                    } catch (Exception e) {
                        br.logger.severe("Error occurred while executing command " + command.command());
                        return false;
                    }
                }

            };

            if (!registerCommand(cmd)) br.logger.severe("Couldn't register command: " + command.command());
        }
    }
}