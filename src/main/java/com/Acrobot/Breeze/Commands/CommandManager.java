package com.Acrobot.Breeze.Commands;

import com.Acrobot.Breeze.Breeze;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.CraftServer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Acrobot
 */
public class CommandManager {
    private SimpleCommandMap scm;
    private final Set<org.bukkit.command.Command> commands = new HashSet<org.bukkit.command.Command>();
    private final Breeze br;

    public CommandManager(Breeze br) {
        this.br = br;
        try {
            Field f = CraftServer.class.getDeclaredField("commandMap");
            f.setAccessible(true);

            scm = (SimpleCommandMap) f.get(br.getPlugin().getServer()); //Please bring the dynamic command registering in Bukkit.
        } catch (Exception e) {
            br.logger.severe("It seems like you aren't using CraftBukkit - " +
                    "at the moment, dynamic command registering is not in Bukkit (so it had to be modded in), sorry!");
        }
    }

    /**
     * Registers a command
     *
     * @param command Bukkit command
     * @return was the registering successful
     */
    private boolean registerCommand(org.bukkit.command.Command command) {
        return scm.getCommand(command.getName()) == null && scm.register(br.getPlugin().getDescription().getName().toLowerCase(), command);
    }

    /**
     * Registers a command class
     *
     * @param clazz the command class
     */
    public void registerCommand(Class clazz) {
        for (final Method m : clazz.getMethods()) {
            if (!m.isAnnotationPresent(Command.class) || !Modifier.isStatic(m.getModifiers()))
                continue; //If there is no command annotation, skip this method

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
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    return false;
                }

            };

            if (!registerCommand(cmd)) {
                br.logger.severe("Couldn't register command: " + command.command());
            } else {
                commands.add(cmd);
            }
        }
    }

    /**
     * Unregisters a command
     * @param command command to unregister
     */
    public void unregisterCommand(String command) {
        for (org.bukkit.command.Command cmd : scm.getCommands()) {
            if (cmd.getName().equals(command)) {
                cmd.unregister(scm);
            }
        }
    }

    /**
     * Unregisters all commands from BreezePlugins
     */
    public void unregisterCommands() {
        for (org.bukkit.command.Command command : commands) {
            unregisterCommand(command.getName());
        }
    }
}
