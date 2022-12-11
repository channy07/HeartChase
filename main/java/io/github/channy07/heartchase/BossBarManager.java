package io.github.channy07.heartchase;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static io.github.channy07.heartchase.GameManager.heartPlaceTime;

public class BossBarManager
{
    private final Main plugin;
    private static BossBar bar;



    public BossBarManager(Main plugin)
    {
        this.plugin = plugin;
    }

    public static void addPlayer(Player p)
    {
        bar.addPlayer(p);
    }

    public static BossBar getBar()
    {
        return bar;
    }

    public static void createBar(String title, BarColor color, BarStyle style)
    {
        bar = Bukkit.createBossBar(title, color, style);
        bar.setVisible(true);
    }

    public static void showHeartPlaceBar()
    {
        createBar(ChatColor.BOLD + "" + ChatColor.GREEN + "심장 설치 (120)", BarColor.RED, BarStyle.SOLID);
        getBar().setProgress(1.0);

        for(Player player : GameManager.gamePlayer)
        {
            getBar().addPlayer(player);
        }

        new BukkitRunnable()
        {
            double progress = 1.000;
            double time = 1/heartPlaceTime;

            @Override
            public void run()
            {
                bar.setProgress(progress);
                bar.setTitle(ChatColor.BOLD + "" + ChatColor.RED + "심장 설치 (" + ((int) (getBar().getProgress() * (heartPlaceTime/20))) + ")");

                Bukkit.broadcastMessage("time : " + time);
                progress -= time;

                if(progress <= 0)
                {
                    getBar().removeAll();
                    this.cancel();
                }
            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("HeartChase"), 0L, 1L);
    }
}
