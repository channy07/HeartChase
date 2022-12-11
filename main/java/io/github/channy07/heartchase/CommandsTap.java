package io.github.channy07.heartchase;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandsTap implements TabCompleter
{

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            return null;
        }

        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("hc"))
        {
            if(args.length == 1)
            {
                List<String> commands = new ArrayList<String>();
                commands.add("join");
                commands.add("quit");

                if(p.isOp())
                {
                    commands.add("start");
                    commands.add("setting");
                }

                return commands;
            }
        }

        return null;
    }
}
