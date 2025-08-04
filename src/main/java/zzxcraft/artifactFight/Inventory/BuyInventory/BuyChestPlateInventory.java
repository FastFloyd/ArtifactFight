package zzxcraft.artifactFight.Inventory.BuyInventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Type.ArtifactChestPlateType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactHelmetType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuyChestPlateInventory implements InventoryHolder {
    Inventory inventory;
    Player player;
    ArtifactChestPlateType artifactChestPlateType;
    InventoryHolder super_inventory;
    private HashMap<Integer,ArtifactChestPlateType> chestPlateTypeHashMap=new HashMap<>();
    public BuyChestPlateInventory(Player player, ArtifactChestPlateType artifactChestPlateType,InventoryHolder super_inventory){
        this.super_inventory=super_inventory;
        this.inventory= ArtifactFight.getMainClass().getServer().createInventory(this,54);
        this.player=player;
        this.artifactChestPlateType=artifactChestPlateType;
        for(int i=0;i<artifactChestPlateType.getChildren().size();i++){
            this.addChestPlateType(i,(ArtifactChestPlateType) artifactChestPlateType.getChildren().toArray()[i]);
        }
        this.inventory.setItem(49, ItemStack.of(Material.BLACK_WOOL));
    }
    public InventoryHolder getSuperInventory(){return super_inventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
    private void addChestPlateType(int slot,ArtifactChestPlateType artifactChestPlateType){
        this.chestPlateTypeHashMap.put(slot,artifactChestPlateType);
        ItemStack itemStack=new ItemStack(artifactChestPlateType.getItemStack());
        ItemMeta itemMeta=itemStack.getItemMeta();
        List<Component> list=itemMeta.lore();
        if(list==null){
            list=new ArrayList<>();
        }
        list.add(Component.text("Piece: $"+(artifactChestPlateType.getPrice()==0?"free":String.valueOf(artifactChestPlateType.getPrice()))));
        itemMeta.lore(list);
        itemStack.setItemMeta(itemMeta);
        this.getInventory().setItem(slot,itemStack);
    }
    public ArtifactChestPlateType getChestPlateType(int slot){
        return this.chestPlateTypeHashMap.get(slot);
    }

}
