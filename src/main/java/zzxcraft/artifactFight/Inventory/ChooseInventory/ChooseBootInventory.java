package zzxcraft.artifactFight.Inventory.ChooseInventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Type.ArtifactBootType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactHelmetType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.HashMap;
import java.util.Set;

public class ChooseBootInventory implements InventoryHolder {
    private final Inventory inventory;
    private final static JavaPlugin javaPlugin = ArtifactFight.getMainClass();
    private final FileConfiguration config =ArtifactFight.getMainClass().getConfig();
    private final Player player;
    private final ChooseItemInventory superInventory;
    private HashMap<Integer,ArtifactBootType> bootTypeHashMap=new HashMap<>();
    public ChooseBootInventory(Player player, ChooseItemInventory chooseItemInventory){
        this.superInventory=chooseItemInventory;
        this.player=player;
        this.inventory=javaPlugin.getServer().createInventory(this,54);
        this.getInventory().setItem(49,NameItemStack(ItemStack.of(Material.BLACK_WOOL)));
        PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
        for(int i=1,I=0;i<=ArtifactBootType.getBootSize();i++){
            Boolean c=persistentDataContainer.get(new NamespacedKey(javaPlugin,"bought_boot"+i), PersistentDataType.BOOLEAN);
            if(c==null) continue;
            addBootType(I, ArtifactBootType.getBoot(i));
            I++;
        }
    }
    public ChooseItemInventory getSuperInventory(){return this.superInventory;}
    @Override
    public @NotNull Inventory getInventory()  {
        return this.inventory;
    }
    private static ItemStack NameItemStack(ItemStack itemStack){
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("返回"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private void addBootType(int slot,ArtifactBootType artifactBootType){
        this.getInventory().setItem(slot,artifactBootType.getItemStack());
        this.bootTypeHashMap.put(slot,artifactBootType);
    }
    public ArtifactBootType getBootType(int slot){
        return this.bootTypeHashMap.get(slot);
    }
}
