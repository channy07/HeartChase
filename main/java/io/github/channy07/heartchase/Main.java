package io.github.channy07.heartchase;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;

public final class Main extends JavaPlugin
{
    public static String label = ChatColor.GOLD + "[" + ChatColor.WHITE + "HC" + ChatColor.GOLD + "] " + ChatColor.RESET;

    public static FileConfiguration config = Bukkit.getServer().spigot().getConfig();

    @Override
    public void onEnable()
    {
        System.out.println("----------------------------------------------");
        System.out.println("  P r o j e c t  |  H E A R T   C H A S E ");
        System.out.println(" bug report : channy070123@gmail.com ");
        System.out.println("----------------------------------------------");

        this.getCommand("HC").setExecutor(new Commands());
        this.getCommand("HC").setTabCompleter(new CommandsTap());

        getServer().getPluginManager().registerEvents(new Events(), this);
        getServer().getPluginManager().registerEvents(new SettingManager(), this);

        config = this.getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable()
    {
        saveConfig();

        System.out.println("----------------------------------------------");
        System.out.println("  P r o j e c t  |  H E A R T   C H A S E ");
        System.out.println(" bug report : channy070123@gmail.com");
        System.out.println("----------------------------------------------");

        if(BossBarManager.getBar() != null)
        {
            BossBarManager.getBar().removeAll();
        }
    }
}
