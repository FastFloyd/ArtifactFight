package zzxcraft.artifactFight.Inventory.BuyInventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Type.ArtifactElytraType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactHelmetType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.HashMap;

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
        this.getInventory().setItem(slot,artifactElytraType.getItemStack());
    }
    public ArtifactElytraType getElytraType(int slot){
        return this.elytraTypeHashMap.get(slot);
    }
}
