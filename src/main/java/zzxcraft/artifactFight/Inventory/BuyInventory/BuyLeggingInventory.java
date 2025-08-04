package zzxcraft.artifactFight.Inventory.BuyInventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Type.ArtifactHelmetType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactLeggingType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuyLeggingInventory implements InventoryHolder {
    Inventory inventory;
    Player player;
    ArtifactLeggingType artifactLeggingType;
    InventoryHolder super_inventory;
    private HashMap<Integer,ArtifactLeggingType> leggingTypeHashMap=new HashMap<>();
    public BuyLeggingInventory(Player player, ArtifactLeggingType artifactLeggingType,InventoryHolder super_inventory){
        this.super_inventory=super_inventory;
        this.inventory= ArtifactFight.getMainClass().getServer().createInventory(this,54);
        this.player=player;
        this.artifactLeggingType=artifactLeggingType;
        for(int i=0;i<artifactLeggingType.getChildren().size();i++){
            this.addLeggingType(i, (ArtifactLeggingType) artifactLeggingType.getChildren().toArray()[i]);
        }
        this.inventory.setItem(49, ItemStack.of(Material.BLACK_WOOL));
    }
    public InventoryHolder getSuperInventory(){return super_inventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
    private void addLeggingType(int slot,ArtifactLeggingType artifactLeggingType){
        this.leggingTypeHashMap.put(slot,artifactLeggingType);
        ItemStack itemStack=artifactLeggingType.getItemStack();
        ItemMeta itemMeta=itemStack.getItemMeta();
        List<Component> list=itemMeta.lore();
        if(list==null){
            list=new ArrayList<>();
        }
        list.add(Component.text("Piece: $"+(artifactLeggingType.getPrice()==0?"free":String.valueOf(artifactLeggingType.getPrice()))));
        itemMeta.lore(list);
        itemStack.setItemMeta(itemMeta);
        this.getInventory().setItem(slot,itemStack);
    }
    public ArtifactLeggingType getLeggingType(int slot){
        return this.leggingTypeHashMap.get(slot);
    }
}
