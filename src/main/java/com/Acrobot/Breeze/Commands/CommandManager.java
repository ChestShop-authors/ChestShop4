package com.Acrobot.Breeze.Commands;

import com.Acrobot.Breeze.Breeze;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.CraftServer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author Acrobot
 */
public class CommandManager {
    private SimpleCommandMap scm;
    private final Map<BreezePlugin, List<org.bukkit.command.Command>> commands = new HashMap<BreezePlugin, List<org.bukkit.command.Command>>();
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
    public void registerCommand(Class clazz, BreezePlugin plugin) {
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
                if (commands.containsKey(plugin)) {
                    commands.get(plugin).add(cmd);
                } else {
                    List<org.bukkit.command.Command> list = new ArrayList<org.bukkit.command.Command>();
                    list.add(cmd);
                    commands.put(plugin, list);
                }
            }
        }
    }

    /**
     * Unregisters a command
     *
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
        for (List<org.bukkit.command.Command> list : commands.values()) {
            for (org.bukkit.command.Command command : list) {
                unregisterCommand(command.getName());
            }
        }
    }

    /**
     * Unregisters all commands from one plugin
     *
     * @param plugin plugin that registered the commands
     */
    public void unregisterCommands(BreezePlugin plugin) {
        if (commands.containsKey(plugin)) {
            for (org.bukkit.command.Command cmd : commands.get(plugin)) {
                unregisterCommand(cmd.getName());
            }
        }
    }
}
