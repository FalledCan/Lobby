package masa3mc.masa3lobby.Listener;

import masa3mc.masa3lobby.Masa3Lobby;
import masa3mc.masa3lobby.Other.setItems;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setExp(0);
        event.setJoinMessage(null);
        player.teleport(player.getWorld().getSpawnLocation());
        setItems.setInv(player);
        player.sendTitle("§7§l-=§6Masa3MCNetwork§7§l=-", "§bGen2", 20, 100, 20);
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            player1.getWorld().playSound(player1.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
        }
        if(Masa3Lobby.config.getBoolean("Config.Message.T/F")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',Masa3Lobby.config.getString("Config.Message.message")));
        }
        File f = new File(Masa3Lobby.instance.getDataFolder().getAbsolutePath(), "/players/" + player.getUniqueId() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        c.set("name", player.getName());
        InetSocketAddress IPAdressPlayer = player.getAddress();
        String sfullip = IPAdressPlayer.toString();
        String[] fullip;
        String[] ipandport;
        fullip = sfullip.split("/");
        String sIpandPort = fullip[1];
        ipandport = sIpandPort.split(":");
        String sIp = ipandport[0];
        c.set("IP", sIp);
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd E HH:mm:ss");
        c.set("Log." + df.format(d),"Join");
        try {
            c.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
        }
        Player player = event.getPlayer();
        File f = new File(Masa3Lobby.instance.getDataFolder().getAbsolutePath(), "/players/" + player.getUniqueId() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd E HH:mm:ss");
        c.set("Log." + df.format(d),"Quit");
        try {
            c.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 2);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        } else {
            return;
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        } else {
            return;
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!player.getGameMode().equals(GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            event.getItemDrop().remove();
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerArmorStandManipulateEvent(PlayerArmorStandManipulateEvent event){
        Player player = event.getPlayer();
        if(!player.getGameMode().equals(GameMode.CREATIVE)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFish(PlayerFishEvent event){
        Player player = event.getPlayer();
            if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "mysterydust add " + player.getName() + " 1");
                File f = new File(Masa3Lobby.instance.getDataFolder().getAbsolutePath(), "/players/" + player.getUniqueId() + ".yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                if(c.get("Fish") == null) {
                    c.set("Fish.Cod", 0);
                    c.set("Fish.Salmon", 0);
                    c.set("Fish.Pufferfish", 0);
                    c.set("Fish.Tropical-fish", 0);
                    c.set("Fish.Other", 0);
                    try {
                        c.save(f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Item item = (Item)event.getCaught();

                    if (item.getItemStack().getType().equals(Material.COD)) {
                        int fn = c.getInt("Fish.Cod") + 1;
                        c.set("Fish.Cod", fn);
                    } else if (item.getItemStack().getType().equals(Material.SALMON)) {
                        int fn = c.getInt("Fish.Salmon") + 1;
                        c.set("Fish.Salmon", fn);
                    } else if (item.getItemStack().getType().equals(Material.PUFFERFISH)) {
                        int fn = c.getInt("Fish.Pufferfish") + 1;
                        c.set("Fish.Pufferfish", fn);
                    } else if (item.getItemStack().getType().equals(Material.TROPICAL_FISH)) {
                        int fn = c.getInt("Fish.Tropical-fish") + 1;
                        c.set("Fish.Tropical-fish", fn);
                    } else {
                        int fn = c.getInt("Fish.Other") + 1;
                        c.set("Fish.Other", fn);
                    }

                try {
                    c.save(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    @EventHandler
    public void onClickServerSelect(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(setItems.serverselect_string)) {
                setItems.openMenu(player);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(setItems.Fish)) {
                if(player.isSneaking()) {

                    File f = new File(Masa3Lobby.instance.getDataFolder().getAbsolutePath(), "/players/" + player.getUniqueId() + ".yml");
                    FileConfiguration c = YamlConfiguration.loadConfiguration(f);

                    if(c.get("Fish") == null){
                        return;
                    }

                    setItems.openFish(player);
                }
            }
        }
    }

    @EventHandler
    public void onServerSelect(InventoryClickEvent event){
        Inventory inv = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equals(setItems.serverselect_string)){
            event.setCancelled(true);
            int raw = event.getRawSlot();
            try {
                switch (raw) {
                    case 1:
                        Masa3Lobby.bungeecord().Connect(player, "Creative");
                        player.closeInventory();
                        break;
                    case 3:
                        Masa3Lobby.bungeecord().Connect(player, "Survival");
                        player.closeInventory();
                        break;
                    case 5:
                        Masa3Lobby.bungeecord().Connect(player, "Athletic");
                        player.closeInventory();
                        break;
                    case 7:
                        Masa3Lobby.bungeecord().Connect(player, "PvP");
                        player.closeInventory();
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
            }
        }
    }

}