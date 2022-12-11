package io.github.channy07.heartchase;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class SettingManager implements Listener
{
    public static HashMap<Player, String> setting = new HashMap<>();    //어떤것을 설정중인지
    public static HashMap<Player, Boolean> enableSetting = new HashMap<>(); //설정이 가능한 구역을 바라보고 있는지

    public static void giveSettingStick(Player p)
    {
        ItemStack Stick = new ItemStack(Material.STICK);
        ItemMeta StickMeta = Stick.getItemMeta();
        StickMeta.setDisplayName("setting");
        Stick.setItemMeta(StickMeta);

        p.getInventory().addItem(Stick);
    }



    public static void startSetting(Player p, String s)
    {
        if(setting.containsKey(p))
        {
            setting.replace(p, s);
        }
        else
        {
            setting.put(p, s);
        }

        p.sendMessage(Main.label + "좌클릭 - " + ChatColor.GREEN + "설정" + ChatColor.WHITE + " / 우클릭 - " + ChatColor.RED + "취소");
        showSettingBlocks(p);
    }

    public static void stopsetting(Player p)
    {
        if(setting.containsKey(p))
        {
            setting.remove(p);
        }
        else
        {
            return;
        }
    }

    public static void showSettingBlocks(Player p)
    {
        new BukkitRunnable()
        {
            ArrayList<FallingBlock> fallingblock = new ArrayList<>();
            Location settingLoc;

            @Override
            public void run()
            {
                if(!setting.containsKey(p))
                {
                    if(fallingblock.size() != 0)
                    {
                        for (FallingBlock fb : fallingblock)
                        {
                            fb.remove();
                        }

                        fallingblock.clear();
                    }

                    this.cancel();
                    return;
                }

                if(p.getItemInHand() != null)
                {
                    if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("setting"))
                    {
                        Block b = p.getTargetBlockExact(15);

                        if(b == null)
                        {
                            if(fallingblock.size() != 0)
                            {
                                for (FallingBlock fb : fallingblock)
                                {
                                    fb.remove();
                                }

                                fallingblock.clear();
                            }
                        }
                        else if(b.getLocation().clone().add(0, 1, 0).getBlock().getType() == Material.AIR)
                        {
                            if(settingLoc == null)
                            {
                                settingLoc = b.getLocation();

                                if(settingLoc.clone().add(0.5, 1, 0.5).getBlock().getType() == Material.AIR &&
                                        settingLoc.clone().add(-0.5, 1, 0.5).getBlock().getType() == Material.AIR &&
                                        settingLoc.clone().add(0.5, 1, -0.5).getBlock().getType() == Material.AIR &&
                                        settingLoc.clone().add(-0.5, 1, -0.5).getBlock().getType() == Material.AIR)
                                {
                                    FallingBlock b1 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, 0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                    b1.setGravity(false);
                                    fallingblock.add(b1);

                                    FallingBlock b2 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, 0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                    b2.setGravity(false);
                                    fallingblock.add(b2);

                                    FallingBlock b3 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, -0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                    b3.setGravity(false);
                                    fallingblock.add(b3);

                                    FallingBlock b4 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, -0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                    b4.setGravity(false);
                                    fallingblock.add(b4);

                                    enableSetting.put(p, true);
                                }
                                else
                                {
                                    if(settingLoc.clone().add(0.5, 1, 0.5).getBlock().getType() == Material.AIR)
                                    {
                                        FallingBlock b1 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, 0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                        b1.setGravity(false);
                                        fallingblock.add(b1);
                                    }

                                    if(settingLoc.clone().add(-0.5, 1, 0.5).getBlock().getType() == Material.AIR)
                                    {
                                        FallingBlock b2 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, 0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                        b2.setGravity(false);
                                        fallingblock.add(b2);
                                    }

                                    if(settingLoc.clone().add(0.5, 1, -0.5).getBlock().getType() == Material.AIR)
                                    {
                                        FallingBlock b3 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, -0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                        b3.setGravity(false);
                                        fallingblock.add(b3);
                                    }

                                    if(settingLoc.clone().add(-0.5, 1, -0.5).getBlock().getType() == Material.AIR)
                                    {
                                        FallingBlock b4 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, -0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                        b4.setGravity(false);
                                        fallingblock.add(b4);
                                    }

                                    enableSetting.put(p, false);
                                }
                            }
                            else
                            {
                                if(settingLoc != b.getLocation())
                                {
                                    settingLoc = b.getLocation();

                                    for (FallingBlock fb : fallingblock)
                                    {
                                        fb.remove();
                                    }

                                    fallingblock.clear();

                                    if(settingLoc.clone().add(0.5, 1, 0.5).getBlock().getType() == Material.AIR &&
                                            settingLoc.clone().add(-0.5, 1, 0.5).getBlock().getType() == Material.AIR &&
                                            settingLoc.clone().add(0.5, 1, -0.5).getBlock().getType() == Material.AIR &&
                                            settingLoc.clone().add(-0.5, 1, -0.5).getBlock().getType() == Material.AIR)
                                    {
                                        FallingBlock b1 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, 0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                        b1.setGravity(false);
                                        fallingblock.add(b1);

                                        FallingBlock b2 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, 0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                        b2.setGravity(false);
                                        fallingblock.add(b2);

                                        FallingBlock b3 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, -0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                        b3.setGravity(false);
                                        fallingblock.add(b3);

                                        FallingBlock b4 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, -0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                        b4.setGravity(false);
                                        fallingblock.add(b4);

                                        enableSetting.replace(p, true);
                                    }
                                    else
                                    {
                                        if(settingLoc.clone().add(0.5, 1, 0.5).getBlock().getType() == Material.AIR)
                                        {
                                            FallingBlock b1 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, 0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                            b1.setGravity(false);
                                            fallingblock.add(b1);
                                        }

                                        if(settingLoc.clone().add(-0.5, 1, 0.5).getBlock().getType() == Material.AIR)
                                        {
                                            FallingBlock b2 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, 0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                            b2.setGravity(false);
                                            fallingblock.add(b2);
                                        }

                                        if(settingLoc.clone().add(0.5, 1, -0.5).getBlock().getType() == Material.AIR)
                                        {
                                            FallingBlock b3 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, -0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                            b3.setGravity(false);
                                            fallingblock.add(b3);
                                        }

                                        if(settingLoc.clone().add(-0.5, 1, -0.5).getBlock().getType() == Material.AIR)
                                        {
                                            FallingBlock b4 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, -0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                            b4.setGravity(false);
                                            fallingblock.add(b4);
                                        }

                                        enableSetting.replace(p, false);
                                    }
                                }
                            }
                        }
                        else
                        {
                            if(fallingblock.size() != 0)
                            {
                                for (FallingBlock fb : fallingblock)
                                {
                                    fb.remove();
                                }

                                fallingblock.clear();
                            }
                        }
                    }
                    else
                    {
                        for (FallingBlock fb : fallingblock)
                        {
                            fb.remove();
                        }

                        p.sendMessage(Main.label + "설정이 취소되었습니다");
                        setting.remove(p);
                        this.cancel();
                    }
                }

            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("HeartChase"), 0L, 1L);
    }

    @EventHandler
    public void setSetting(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();

        if(e.getHand() != EquipmentSlot.HAND)
        {
            return;
        }

        if(p.getItemInHand() != null)
        {
            ItemStack item = p.getItemInHand();

            if (item.getType() == Material.STICK && item.getItemMeta().getDisplayName().equalsIgnoreCase("setting"))
            {
                e.setCancelled(true);

                if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)
                {
                    if(setting.containsKey(p))
                    {
                        stopsetting(p);
                        p.sendMessage(Main.label + ChatColor.RED + "설정이 취소되었습니다");

                        return;
                    }

                    p.openInventory(UI.getSettingUI());
                }
                else if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR)
                {
                    if(setting.containsKey(p))
                    {
                        if(enableSetting.get(p))
                        {
                            Location loc = p.getTargetBlockExact(15).getLocation().add(0, 1, 0);

                            Main.config.set("setting." + setting.get(p) + ".world", loc.getWorld().getName());
                            Main.config.set("setting." + setting.get(p) + ".x", loc.getX());
                            Main.config.set("setting." + setting.get(p) + ".y", loc.getY());
                            Main.config.set("setting." + setting.get(p) + ".z", loc.getZ());

                            p.sendMessage(Main.label + ChatColor.GREEN + "설정되었습니다");

                            stopsetting(p);
                        }
                        else
                        {
                            p.sendMessage(Main.label + ChatColor.RED + "이곳에 설정할 수 없습니다");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        String title = e.getView().getTitle();
        ItemStack item = e.getCurrentItem();

        if(title.equalsIgnoreCase("SETTING"))
        {
            e.setCancelled(true);

            if(item != null)
            {
                String name = item.getItemMeta().getDisplayName();

                if(name.equalsIgnoreCase("로비"))
                {
                    startSetting(p, "lobby");
                }
                else if(name.equalsIgnoreCase("게임 스폰"))
                {
                    startSetting(p, "spawn");
                }
            }
        }
    }

    public static Location getLobbyLoc()
    {
        World w = Bukkit.getWorld(Main.config.getString("setting.lobby.world"));
        double x = Main.config.getDouble("setting.lobby.x");
        double y = Main.config.getDouble("setting.lobby.y");
        double z = Main.config.getDouble("setting.lobby.z");

        return (new Location(w, x, y, z));
    }

    public static Location getSpawnLoc()
    {
        World w = Bukkit.getWorld(Main.config.getString("setting.spawn.world"));
        double x = Main.config.getDouble("setting.spawn.x");
        double y = Main.config.getDouble("setting.spawn.y");
        double z = Main.config.getDouble("setting.spawn.z");

        return (new Location(w, x, y, z));
    }
}


































