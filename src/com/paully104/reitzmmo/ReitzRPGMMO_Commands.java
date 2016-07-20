package com.paully104.reitzmmo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Paul on 3/22/2016.
 */
public class ReitzRPGMMO_Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if ((cmd.getName().equalsIgnoreCase("ReitzRPGMMO") || cmd.getName().equalsIgnoreCase("RRM")) && args.length == 0)
        {
            sender.sendMessage("This is working");
            return true;
        }
        else
        {
            return false;
        }

    }
}
