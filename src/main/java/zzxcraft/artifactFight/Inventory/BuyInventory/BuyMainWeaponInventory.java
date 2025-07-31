package zzxcraft.artifactFight.Inventory.BuyInventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Type.ArtifactHelmetType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactMainWeaponType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.HashMap;

public class BuyMainWeaponInventory implements InventoryHolder {
    Inventory inventory;
    Player player;
    ArtifactMainWeaponType artifactMainWeaponType;
    InventoryHolder super_inventory;
    private HashMap<Integer,ArtifactMainWeaponType> mainWeaponTypeHashMap=new HashMap<>();
    public BuyMainWeaponInventory(Player player, ArtifactMainWeaponType artifactMainWeaponType,InventoryHolder super_inventory){
        this.super_inventory=super_inventory;
        this.inventory= ArtifactFight.getMainClass().getServer().createInventory(this,54);
        this.player=player;
        this.artifactMainWeaponType=artifactMainWeaponType;
        for(int i=0;i<artifactMainWeaponType.getChildren().size();i++){
            this.addMainWeaponType(i,(ArtifactMainWeaponType) artifactMainWeaponType.getChildren().toArray()[i]);
        }
        this.inventory.setItem(49, ItemStack.of(Material.BLACK_WOOL));
    }
    public InventoryHolder getSuperInventory(){return super_inventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
    private void addMainWeaponType(int slot,ArtifactMainWeaponType artifactMainWeaponType){
        this.mainWeaponTypeHashMap.put(slot,artifactMainWeaponType);
        this.getInventory().setItem(slot,artifactMainWeaponType.getItemStack());
    }
    public ArtifactMainWeaponType getMainWeaponType(int slot){
        return this.mainWeaponTypeHashMap.get(slot);
    }
}
