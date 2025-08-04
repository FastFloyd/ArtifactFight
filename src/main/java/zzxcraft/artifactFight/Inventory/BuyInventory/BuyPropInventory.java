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
import zzxcraft.artifactFight.Artifact.Type.ArtifactPropType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuyPropInventory implements InventoryHolder {
    Inventory inventory;
    Player player;
    ArtifactPropType artifactPropType;
    InventoryHolder super_inventory;
    private HashMap<Integer,ArtifactPropType> propTypeHashMap=new HashMap<>();
    public BuyPropInventory(Player player, ArtifactPropType artifactPropType,InventoryHolder super_inventory){
        this.super_inventory=super_inventory;
        this.inventory= ArtifactFight.getMainClass().getServer().createInventory(this,54);
        this.player=player;
        this.artifactPropType=artifactPropType;
        for(int i=0;i<artifactPropType.getChildren().size();i++){
            this.addPropType(i,(ArtifactPropType) artifactPropType.getChildren().toArray()[i]);
        }
        this.inventory.setItem(49, ItemStack.of(Material.BLACK_WOOL));
    }
    public InventoryHolder getSuperInventory(){return super_inventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
    private void addPropType(int slot,ArtifactPropType artifactPropType){
        this.propTypeHashMap.put(slot,artifactPropType);
        ItemStack itemStack=new ItemStack(artifactPropType.getItemStack());
        ItemMeta itemMeta=itemStack.getItemMeta();
        List<Component> list=itemMeta.lore();
        if(list==null){
            list=new ArrayList<>();
        }
        list.add(Component.text("Piece: $"+(artifactPropType.getPrice()==0?"free":String.valueOf(artifactPropType.getPrice()))));
        itemMeta.lore(list);
        itemStack.setItemMeta(itemMeta);
        this.getInventory().setItem(slot,itemStack);
    }
    public ArtifactPropType getPropType(int slot){
        return this.propTypeHashMap.get(slot);
    }
}
