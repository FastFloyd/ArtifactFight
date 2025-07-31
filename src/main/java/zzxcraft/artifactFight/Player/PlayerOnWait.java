package zzxcraft.artifactFight.Player;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.CustomModelData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.DataComponentValue;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import zzxcraft.artifactFight.ArtifactFight;

public class PlayerOnWait extends BukkitRunnable {
    private final static JavaPlugin javaPlugin = ArtifactFight.getMainClass();
    private final FileConfiguration config =ArtifactFight.getMainClass().getConfig();
    public PlayerOnWait(){

    }
    @Override
    public void run() {
        for(Player player : javaPlugin.getServer().getOnlinePlayers()){
            for(String string : player.getScoreboardTags()){
                if(string.equals("onWait")){
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
        for(int i=0;i<36;i++){
            if(i!=0 && i!=1 && i!=4 && i!=8){
                player.getInventory().clear(i);
            }
        }
        player.getInventory().setItem(4,getDisplayItem(Material.COMPASS,"加入游戏"));
        player.getInventory().setItem(0,getDisplayItem(Material.CHEST,"选择装备"));
        player.getInventory().setItem(1,getDisplayItem(Material.ANVIL,"购买武器"));
    }
    private ItemStack getDisplayItem(Material material,String display){
        ItemStack itemStack = ItemStack.of(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(display));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
