package masa3mc.masa3lobby.Other;

import masa3mc.masa3lobby.Masa3Lobby;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Daily {
    private final Masa3Lobby instance = Masa3Lobby.instance;

    public void daily(){

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {

                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                if(df.format(date).equals(instance.getConfig().get("Config.Daily"))){
                    File f2 = new File(Masa3Lobby.instance.getDataFolder().getAbsolutePath(), "Daily.yml" );
                    f2.delete();
                }
            }
        };
        task.runTaskTimer(Masa3Lobby.getPlugin(),0L,20L);

    }
}
