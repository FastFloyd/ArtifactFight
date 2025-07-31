package zzxcraft.artifactFight.Inventory.BuyInventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Type.ArtifactHelmetType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.HashMap;

public class BuyHelmetInventory implements InventoryHolder {
    Inventory inventory;
    Player player;
    ArtifactHelmetType artifactHelmetType;
    InventoryHolder super_inventory;
    private HashMap<Integer,ArtifactHelmetType> helmetTypeHashMap=new HashMap<>();
    public BuyHelmetInventory(Player player, ArtifactHelmetType artifactHelmetType,InventoryHolder super_inventory){
        this.super_inventory=super_inventory;
        this.inventory= ArtifactFight.getMainClass().getServer().createInventory(this,54);
        this.player=player;
        this.artifactHelmetType=artifactHelmetType;
        for(int i=0;i<artifactHelmetType.getChildren().size();i++){
            this.addHelmetType(i,(ArtifactHelmetType) artifactHelmetType.getChildren().toArray()[i]);
        }
        this.inventory.setItem(49, ItemStack.of(Material.BLACK_WOOL));
    }
    public InventoryHolder getSuperInventory(){return super_inventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
    private void addHelmetType(int slot,ArtifactHelmetType artifactHelmetType){
        this.helmetTypeHashMap.put(slot,artifactHelmetType);
        this.getInventory().setItem(slot,artifactHelmetType.getItemStack());
    }
    public ArtifactHelmetType getHelmetType(int slot){
        return this.helmetTypeHashMap.get(slot);
    }
}
