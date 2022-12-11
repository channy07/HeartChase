package io.github.channy07.heartchase;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class GameManager
{
    public static ArrayList<Player> gamePlayer = new ArrayList<Player>();
    public static boolean isOnGame = false;


    //GAME SETTING
    public static int heartPlaceTime = 120*20;

    public static void joinGame(Player p)   //게임 참가
    {
        if(gamePlayer.contains(p))
        {
            p.sendMessage(Main.label + ChatColor.RED + "당신은 이미 게임에 참가했습니다");
            return;
        }

        if(isOnGame)
        {
            p.sendMessage(Main.label + ChatColor.RED + "게임이 이미 진행중입니다");
            return;
        }

        gamePlayer.add(p);
        Bukkit.broadcastMessage(Main.label + p.getName() + "님이 게임에 참가하였습니다 (현재 플레이어 : " + gamePlayer.size() + "명)");
    }

    public static void quitGame(Player p)   //게임 탈퇴
    {
        if(!gamePlayer.contains(p))
        {
            p.sendMessage(Main.label + ChatColor.RED + "당신은 아직 게임에 참가하지 않았습니다.");
            return;
        }

        gamePlayer.remove(p);
        Bukkit.broadcastMessage(Main.label + p.getName() + "님이 게임에 탈퇴하였습니다 (현재 플레이어 : " + gamePlayer.size() + "명)");
    }

    public static void startGame()  //게임 시작
    {
        isOnGame = true;

        new BukkitRunnable()
        {
            int i = 5;

            @Override
            public void run()
            {
                if(i == 0)
                {
                    for(Player player : gamePlayer)
                    {
                        player.sendTitle(ChatColor.GREEN + "GAME START", ChatColor.RED + "심장을 설치하세요", 0, 3 * 20, 1 * 20);
                        player.teleport(SettingManager.getSpawnLoc());
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, heartPlaceTime, 7), true);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, heartPlaceTime, 1), false);
                        UI.setStartInv(player);
                    }

                    BossBarManager.showHeartPlaceBar();

                    this.cancel();
                    return;
                }

                for(Player player : gamePlayer)
                {
                    player.sendTitle(ChatColor.GREEN + "" + i, "게임이 곧 시작됩니다", 0, 2*20, 0);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, 3);
                }

                i--;
            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("HeartChase"), 0L, 20L);
    }
}
