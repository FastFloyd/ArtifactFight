package zzxcraft.artifactFight.Inventory.BuyInventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Type.ArtifactHelmetType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactShieldType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.HashMap;

public class BuyShieldInventory implements InventoryHolder {
    Inventory inventory;
    Player player;
    ArtifactShieldType artifactShieldType;
    InventoryHolder super_inventory;
    private HashMap<Integer,ArtifactShieldType> shieldTypeHashMap=new HashMap<>();
    public BuyShieldInventory(Player player, ArtifactShieldType artifactShieldType,InventoryHolder super_inventory){
        this.super_inventory=super_inventory;
        this.inventory= ArtifactFight.getMainClass().getServer().createInventory(this,54);
        this.player=player;
        this.artifactShieldType=artifactShieldType;
        for(int i=0;i<artifactShieldType.getChildren().size();i++){
            this.addShieldType(i,(ArtifactShieldType) artifactShieldType.getChildren().toArray()[i]);
        }
        this.inventory.setItem(49, ItemStack.of(Material.BLACK_WOOL));
    }
    public InventoryHolder getSuperInventory(){return super_inventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
    private void addShieldType(int slot,ArtifactShieldType artifactShieldType){
        this.shieldTypeHashMap.put(slot,artifactShieldType);
        this.getInventory().setItem(slot,artifactShieldType.getItemStack());
    }
    public ArtifactShieldType getShieldType(int slot){
        return this.shieldTypeHashMap.get(slot);
    }
}
