package io.github.channy07.heartchase;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener
{
    @EventHandler
    public void playerQuit(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();

        if(GameManager.gamePlayer.contains(p))
        {
            GameManager.quitGame(p);
        }

        if(SettingManager.setting.containsKey(p))
        {
            SettingManager.stopsetting(p);
        }
    }

    @EventHandler
    public void playerPlaceBlock(BlockPlaceEvent e)
    {
        if(GameManager.isOnGame)
        {
            e.setCancelled(true);
        }

        if(e.getBlock().getType() == Material.RED_STAINED_GLASS)
        {
            Player p = e.getPlayer();
        }
    }
}
