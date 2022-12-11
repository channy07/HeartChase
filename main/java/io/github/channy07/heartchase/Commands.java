package io.github.channy07.heartchase;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            System.out.println("콘솔에서는 명령어를 입력할 수 없습니다");

            return false;
        }

        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("hc"))
        {
            if(args[0].equalsIgnoreCase("join"))
            {
                GameManager.joinGame(p);
            }
            else if(args[0].equalsIgnoreCase("quit"))
            {
                GameManager.quitGame(p);
            }
        }

        if(p.isOp())
        {
            if(command.getName().equalsIgnoreCase("hc"))
            {
                if(args[0].equalsIgnoreCase("start"))
                {
                    GameManager.startGame();
                }
                else if(args[0].equalsIgnoreCase("setting"))
                {
                    SettingManager.giveSettingStick(p);
                }
            }
        }

        return false;
    }
}
