package zzxcraft.artifactFight.Inventory.BuyInventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Type.ArtifactBootType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactChestPlateType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactHelmetType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuyBootInventory implements InventoryHolder {
    Inventory inventory;
    Player player;
    ArtifactBootType artifactBootType;
    InventoryHolder super_inventory;
    private HashMap<Integer, ArtifactBootType> bootTypeHashMap=new HashMap<>();
    public BuyBootInventory(Player player, ArtifactBootType artifactBootType,InventoryHolder super_inventory){
        this.super_inventory=super_inventory;
        this.inventory= ArtifactFight.getMainClass().getServer().createInventory(this,54);
        this.player=player;
        this.artifactBootType=artifactBootType;
        for(int i=0;i<artifactBootType.getChildren().size();i++){
            this.addBootType(i,(ArtifactBootType)artifactBootType.getChildren().toArray()[i]);
        }
        this.inventory.setItem(49, ItemStack.of(Material.BLACK_WOOL));
    }
    public InventoryHolder getSuperInventory(){return super_inventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
    private void addBootType(int slot, ArtifactBootType artifactBootType){
        this.bootTypeHashMap.put(slot,artifactBootType);
        ItemStack itemStack=new ItemStack(artifactBootType.getItemStack());
        ItemMeta itemMeta=itemStack.getItemMeta();
        List<Component> list=itemMeta.lore();
        if(list==null){
            list=new ArrayList<>();
        }
        list.add(Component.text("Piece: $"+(artifactBootType.getPrice()==0?"free":String.valueOf(artifactBootType.getPrice()))));
        itemMeta.lore(list);
        itemStack.setItemMeta(itemMeta);
        this.getInventory().setItem(slot,itemStack);
    }
    public ArtifactBootType getBootType(int slot){
        return this.bootTypeHashMap.get(slot);
    }
}
