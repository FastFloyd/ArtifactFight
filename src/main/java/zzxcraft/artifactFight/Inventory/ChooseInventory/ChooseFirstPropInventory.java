package zzxcraft.artifactFight.Inventory.ChooseInventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Type.ArtifactPropType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.HashMap;
import java.util.Set;

public class ChooseFirstPropInventory implements InventoryHolder {
    private final Inventory inventory;
    private final static JavaPlugin javaPlugin = ArtifactFight.getMainClass();
    private final FileConfiguration config =ArtifactFight.getMainClass().getConfig();
    private final Player player;
    private final ChooseItemInventory superInventory;
    private HashMap<Integer, ArtifactPropType> propTypeHashMap=new HashMap<>();
    public ChooseFirstPropInventory(Player player,ChooseItemInventory chooseItemInventory){
        this.superInventory=chooseItemInventory;
        this.player=player;
        this.inventory=javaPlugin.getServer().createInventory(this,54);
        this.getInventory().setItem(49,NameItemStack(ItemStack.of(Material.BLACK_WOOL)));
        PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
        for(int i=1,I=0;i<=ArtifactPropType.getPropSize();i++){
            Boolean c=persistentDataContainer.get(new NamespacedKey(javaPlugin,"bought_prop"+i), PersistentDataType.BOOLEAN);
            if(c==null) continue;
            addPropType(I,ArtifactPropType.getProp(i));
            I++;
        }
    }
    public ChooseItemInventory getSuperInventory(){return this.superInventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
    private static ItemStack NameItemStack(ItemStack itemStack){
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("返回"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private void addPropType(int slot,ArtifactPropType artifactPropType){
        this.propTypeHashMap.put(slot,artifactPropType);
        this.getInventory().setItem(slot,artifactPropType.getItemStack());
    }
    public ArtifactPropType getPropType(int slot){
        return propTypeHashMap.get(slot);
    }
}
