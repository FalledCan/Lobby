package masa3mc.masa3lobby.Other;

import masa3mc.masa3lobby.Masa3Lobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;

public class setItems {

    private static ItemStack creative = null;
    private static ItemStack survival = null;
    private static ItemStack pvp = null;
    private static ItemStack athletic = null;

    private static ItemStack Cod = null;
    private static ItemStack Salmon = null;
    private static ItemStack Pufferfish = null;
    private static ItemStack Tropicalfish = null;
    private static ItemStack Other = null;

    public static String serverselect_string = ChatColor.GOLD + "-ServerSelect-";
    public static String Fish = ChatColor.AQUA + "-FishingRod-";

    public static void setInv(Player player){
        player.getInventory().clear();

        ItemStack item1 = new ItemStack(Material.CLOCK);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(serverselect_string);
        item1.setItemMeta(meta1);

        ItemStack item2 = new ItemStack(Material.FISHING_ROD);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(Fish);
        meta2.setUnbreakable(true);
        meta2.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item2.setItemMeta(meta2);

        player.getInventory().setItem(1,item1);
        player.getInventory().setItem(7,item2);

    }

    public static void openFish(Player player) {

        File f = new File(Masa3Lobby.instance.getDataFolder().getAbsolutePath(), "/players/" + player.getUniqueId() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);

        Inventory inv = Bukkit.createInventory(null, 9, Fish);
        Cod = new ItemStack(Material.COD);
        ItemMeta cod_meta = Cod.getItemMeta();
        cod_meta.setDisplayName(ChatColor.AQUA + "COD");
        ArrayList<String> cod_lore = new ArrayList<String>();
        cod_lore.add(ChatColor.AQUA + "釣った数:" + c.getInt("Fish.Cod"));
        cod_meta.setLore(cod_lore);
        Cod.setItemMeta(cod_meta);

        Salmon = new ItemStack(Material.SALMON);
        ItemMeta salmon_meta = Salmon.getItemMeta();
        salmon_meta.setDisplayName(ChatColor.RED + "SALMON");
        ArrayList<String> salmon_lore = new ArrayList<String>();
        salmon_lore.add(ChatColor.RED + "釣った数:" + c.getInt("Fish.Salmon"));
        salmon_meta.setLore(salmon_lore);
        Salmon.setItemMeta(salmon_meta);



        Pufferfish = new ItemStack(Material.PUFFERFISH);
        ItemMeta puffer_meta = Pufferfish.getItemMeta();
        puffer_meta.setDisplayName(ChatColor.YELLOW + "PUFFERFISH");
        ArrayList<String> puffer_lore = new ArrayList<String>();
        puffer_lore.add(ChatColor.YELLOW + "釣った数:" + c.getInt("Fish.Pufferfish"));
        puffer_meta.setLore(puffer_lore);
        Pufferfish.setItemMeta(puffer_meta);

        Tropicalfish = new ItemStack(Material.TROPICAL_FISH);
        ItemMeta tropical_meta = Tropicalfish.getItemMeta();
        tropical_meta.setDisplayName(ChatColor.GOLD + "TROPICAL_FISH");
        ArrayList<String> tropical_lore = new ArrayList<String>();
        tropical_lore.add(ChatColor.GOLD + "釣った数:" + c.getInt("Fish.Tropical-fish"));
        tropical_meta.setLore(tropical_lore);
        Tropicalfish.setItemMeta(tropical_meta);

        Other = new ItemStack(Material.BOOK);
        ItemMeta other_meta = Other.getItemMeta();
        other_meta.setDisplayName(ChatColor.GREEN + "OTHER");
        ArrayList<String> other_lore = new ArrayList<String>();
        other_lore.add(ChatColor.GREEN + "釣った数:" + c.getInt("Fish.Other"));
        other_meta.setLore(other_lore);
        Other.setItemMeta(other_meta);

        inv.setItem(0, Cod);
        inv.setItem(1, Salmon);
        inv.setItem(2, Pufferfish);
        inv.setItem(3, Tropicalfish);
        inv.setItem(4, Other);
        player.openInventory(inv);
    }

    public static void openMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, serverselect_string);
        creative = new ItemStack(Material.OAK_LOG);
        ItemMeta creative_meta = creative.getItemMeta();
        creative_meta.setDisplayName(ChatColor.YELLOW + "Creative");
        ArrayList<String> creative_lore = new ArrayList<String>();
        creative_lore.add(ChatColor.RED + "ver 1.16.*");
        creative_meta.setLore(creative_lore);
        creative.setItemMeta(creative_meta);

        survival = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta survival_meta = survival.getItemMeta();
        survival_meta.setDisplayName(ChatColor.GREEN + "Survival");
        ArrayList<String> survival_lore = new ArrayList<String>();
        survival_lore.add(ChatColor.RED + "ver 1.16.3");
        survival_meta.setLore(survival_lore);
        survival.setItemMeta(survival_meta);

        athletic = new ItemStack(Material.FEATHER);
        ItemMeta athletic_meta = athletic.getItemMeta();
        athletic_meta.setDisplayName(ChatColor.AQUA + "Athletic");
        ArrayList<String> athletic_lore = new ArrayList<String>();
        athletic_lore.add(ChatColor.RED + "ver 1.12.2");
        athletic_meta.setLore(athletic_lore);
        athletic.setItemMeta(athletic_meta);

        pvp = new ItemStack(Material.IRON_SWORD);
        ItemMeta pvp_meta = pvp.getItemMeta();
        pvp_meta.setDisplayName(ChatColor.DARK_RED + "PvP");
        ArrayList<String> pvp_lore = new ArrayList<String>();
        pvp_lore.add(ChatColor.RED + "ver 1.8~1.16.3");
        pvp_lore.add("§f1.8.8で入ることを推奨します。");
        pvp_meta.setLore(pvp_lore);
        pvp.setItemMeta(pvp_meta);


        inv.setItem(1, creative);
        inv.setItem(3, survival);
        inv.setItem(5, athletic);
        inv.setItem(7, pvp);
        player.openInventory(inv);
    }
}