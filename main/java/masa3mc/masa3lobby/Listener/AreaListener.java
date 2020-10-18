package masa3mc.masa3lobby.Listener;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AreaListener implements Listener {
    @EventHandler
    public void onArea(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        if(((186 < loc.getX() || -14 > loc.getX())
                || (101 < loc.getY() || 0 > loc.getY())
                || (-263 < loc.getZ() || -464 > loc.getZ()))){
            if(!player.getGameMode().equals(GameMode.CREATIVE) && !player.getGameMode().equals(GameMode.SPECTATOR)) {
                player.teleport(player.getWorld().getSpawnLocation());
                player.sendMessage(ChatColor.RED + "area外です!!");
            }
        }
    }
}
