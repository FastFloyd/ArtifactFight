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
import zzxcraft.artifactFight.Artifact.Type.ArtifactChestPlateType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactElytraType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactHelmetType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.HashMap;
import java.util.Set;

public class ChooseChestPlateInventory implements InventoryHolder {
    private final Set<Inventory> inventorys;
    private final static JavaPlugin javaPlugin = ArtifactFight.getMainClass();
    private final FileConfiguration config =ArtifactFight.getMainClass().getConfig();
    private final Player player;
    private final ChooseItemInventory superInventory;
    private HashMap<Integer,ArtifactChestPlateType> chestPlateTypeHashMap=new HashMap<>();
    private HashMap<Integer, ArtifactElytraType> elytraTypeHashMap=new HashMap<>();
    public ChooseChestPlateInventory(Player player,ChooseItemInventory chooseItemInventory){
        this.superInventory=chooseItemInventory;
        this.player=player;
        this.inventorys = Set.of(javaPlugin.getServer().createInventory(this,54));
        this.getInventory().setItem(49,NameItemStack(ItemStack.of(Material.BLACK_WOOL)));
        PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
        int I=0;
        for(int i=1;i<=4;i++){
            Boolean c=persistentDataContainer.get(new NamespacedKey(javaPlugin,"bought_chestplate"+i), PersistentDataType.BOOLEAN);
            if(c==null) continue;
            addChestPlate(I, ArtifactChestPlateType.getChestplate(i));
            I++;
        }
        for(int i=1;i<=3;i++){
            Boolean c=persistentDataContainer.get(new NamespacedKey(javaPlugin,"bought_elytra"+i), PersistentDataType.BOOLEAN);
            if(c==null) continue;;
            addChestPlate(I, ArtifactElytraType.getElytra(i));
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
    private void addChestPlate(int slot, ArtifactChestPlateType artifactChestPlateType){
        this.chestPlateTypeHashMap.put(slot,artifactChestPlateType);
        this.getInventory().setItem(slot,artifactChestPlateType.getItemStack());
    }
    private void addChestPlate(int slot,ArtifactElytraType artifactElytraType){
        this.elytraTypeHashMap.put(slot,artifactElytraType);
        this.getInventory().setItem(slot,artifactElytraType.getItemStack());
    }
    public Object getChestPlateType(int slot){
        if(slot<this.chestPlateTypeHashMap.size()){
            return this.chestPlateTypeHashMap.get(slot);
        }
        else{
            return this.elytraTypeHashMap.get(slot);
        }
    }
}
