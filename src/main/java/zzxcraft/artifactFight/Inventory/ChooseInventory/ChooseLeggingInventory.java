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
import zzxcraft.artifactFight.Artifact.Type.ArtifactHelmetType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactLeggingType;
import zzxcraft.artifactFight.ArtifactFight;
import zzxcraft.artifactFight.Inventory.ChooseInventory.ChooseItemInventory;

import java.util.HashMap;
import java.util.Set;

public class ChooseLeggingInventory implements InventoryHolder {
    private final Set<Inventory> inventorys;
    private final static JavaPlugin javaPlugin = ArtifactFight.getMainClass();
    private final FileConfiguration config =ArtifactFight.getMainClass().getConfig();
    private final Player player;
    private final ChooseItemInventory superInventory;
    private HashMap<Integer,ArtifactLeggingType> leggingTypeHashMap=new HashMap<>();
    public ChooseLeggingInventory(Player player,ChooseItemInventory chooseItemInventory){
        this.superInventory=chooseItemInventory;
        this.player=player;
        this.inventorys = Set.of(javaPlugin.getServer().createInventory(this,54));
        this.getInventory().setItem(49,NameItemStack(ItemStack.of(Material.BLACK_WOOL)));
        PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
        for(int i=1,I=0;i<=4;i++){
            Boolean c=persistentDataContainer.get(new NamespacedKey(javaPlugin,"bought_legging"+i), PersistentDataType.BOOLEAN);
            if(c==null) continue;
            addLeggingType(I, ArtifactLeggingType.getLegging(i));
            I++;
        }
    }
    public ChooseItemInventory getSuperInventory(){return this.superInventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return (Inventory) inventorys.toArray()[0];
    }
    public Inventory getInventory(int slot){
        return (Inventory) inventorys.toArray()[slot];
    }
    private static ItemStack NameItemStack(ItemStack itemStack){
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("返回"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private void addLeggingType(int slot, ArtifactLeggingType artifactLeggingType){
        this.leggingTypeHashMap.put(slot,artifactLeggingType);
        this.getInventory().setItem(slot,artifactLeggingType.getItemStack());
    }
    public ArtifactLeggingType getLeggingType(int slot){
        return this.leggingTypeHashMap.get(slot);
    }
}
