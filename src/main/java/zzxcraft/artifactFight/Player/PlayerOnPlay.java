package zzxcraft.artifactFight.Player;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import zzxcraft.artifactFight.ArtifactFight;

public class PlayerOnPlay extends BukkitRunnable {
    private final static JavaPlugin javaPlugin = ArtifactFight.getMainClass();
    private final FileConfiguration config =ArtifactFight.getMainClass().getConfig();
    public PlayerOnPlay(){

    }
    @Override
    public void run() {
        for(Player player : javaPlugin.getServer().getOnlinePlayers()){
            for(String string : player.getScoreboardTags()){
                if(string.equals("onPlay")){
                    Do(player);
                    break;
                }
            }
        }
    }
    public void start(){
        this.runTaskTimer(javaPlugin,0,1);
    }
    private void Do(Player player){
        player.getInventory().setItem(8,ItemStack.of(Material.ARROW,64));
        player.getInventory().setItem(7,ItemStack.of(Material.BREAD,64));
    }
}
