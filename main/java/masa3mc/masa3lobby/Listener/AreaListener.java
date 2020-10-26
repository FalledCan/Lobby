package masa3mc.masa3lobby.Listener;

import masa3mc.masa3lobby.Masa3Lobby;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AreaListener implements Listener {
    private final Masa3Lobby instance = Masa3Lobby.instance;

    @EventHandler
    public void onArea(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        if(((instance.getConfig().getInt("Config.Lobby.X.max") < loc.getX() || instance.getConfig().getInt("Config.Lobby.X.min") > loc.getX())
                || (instance.getConfig().getInt("Config.Lobby.Y.max") < loc.getY() || instance.getConfig().getInt("Config.Lobby.Y.min") > loc.getY())
                || (instance.getConfig().getInt("Config.Lobby.Z.max") < loc.getZ() || instance.getConfig().getInt("Config.Lobby.Z.min") > loc.getZ()))){
            if(!player.getGameMode().equals(GameMode.CREATIVE) && !player.getGameMode().equals(GameMode.SPECTATOR)) {
                player.teleport(player.getWorld().getSpawnLocation());
                player.sendMessage(ChatColor.RED + "area外です!!");
            }
        }
    }
}
