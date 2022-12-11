package io.github.channy07.heartchase;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UI
{
    private static ItemStack createItem(Material m, String name, int c)
    {
        ItemStack item = new ItemStack(m, c);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);


        return item;
    }

    public static Inventory getSettingUI()
    {
        Inventory settingUI = Bukkit.createInventory(null, 9, "SETTING");
        settingUI.setItem(0, createItem(Material.QUARTZ_BLOCK, "로비", 1));
        settingUI.setItem(1, createItem(Material.GRASS_BLOCK, "게임 스폰", 1));

        return settingUI;
    }

    public static void setStartInv(Player p)
    {
        ItemStack heart = createItem(Material.RED_STAINED_GLASS, "심장 블럭", 1);
        p.getInventory().clear();
        p.getInventory().setItem(0, heart);
    }
}
