package zzxcraft.artifactFight.Inventory.BuyInventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Type.ArtifactElytraType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactHelmetType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuyElytraInventory implements InventoryHolder {
    Inventory inventory;
    Player player;
    ArtifactElytraType artifactElytraType;
    InventoryHolder super_inventory;
    private HashMap<Integer,ArtifactElytraType> elytraTypeHashMap=new HashMap<>();
    public BuyElytraInventory(Player player, ArtifactElytraType artifactElytraType,InventoryHolder super_inventory){
        this.super_inventory=super_inventory;
        this.inventory= ArtifactFight.getMainClass().getServer().createInventory(this,54);
        this.player=player;
        this.artifactElytraType=artifactElytraType;
        for(int i=0;i<artifactElytraType.getChildren().size();i++){
            this.addElytraType(i,(ArtifactElytraType) artifactElytraType.getChildren().toArray()[i]);
        }
        this.inventory.setItem(49, ItemStack.of(Material.BLACK_WOOL));
    }
    public InventoryHolder getSuperInventory(){return super_inventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
    private void addElytraType(int slot,ArtifactElytraType artifactElytraType){
        this.elytraTypeHashMap.put(slot,artifactElytraType);
        ItemStack itemStack=new ItemStack(artifactElytraType.getItemStack());
        ItemMeta itemMeta=itemStack.getItemMeta();
        List<Component> list=itemMeta.lore();
        if(list==null){
            list=new ArrayList<>();
        }
        list.add(Component.text("Piece: $"+(artifactElytraType.getPrice()==0?"free":String.valueOf(artifactElytraType.getPrice()))));
        itemMeta.lore(list);
        itemStack.setItemMeta(itemMeta);
        this.getInventory().setItem(slot,itemStack);
    }
    public ArtifactElytraType getElytraType(int slot){
        return this.elytraTypeHashMap.get(slot);
    }
}
