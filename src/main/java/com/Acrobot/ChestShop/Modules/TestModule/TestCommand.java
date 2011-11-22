package com.Acrobot.ChestShop.Modules.TestModule;

import com.Acrobot.Breeze.Commands.Command;
import org.bukkit.command.CommandSender;

/**
 * @author Acrobot
 */
public class TestCommand {
    @Command(command = "asd", aliases = {}, description = "ASd", syntax = "/asd")
    public static boolean test(CommandSender commandSender, String currentAlias, String[] args){
        commandSender.sendMessage("asdasd");
        return true;
    }
}
