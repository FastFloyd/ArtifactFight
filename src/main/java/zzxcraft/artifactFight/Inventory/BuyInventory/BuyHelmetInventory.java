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
import zzxcraft.artifactFight.ArtifactFight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        ItemStack itemStack=new ItemStack(artifactHelmetType.getItemStack());
        ItemMeta itemMeta=itemStack.getItemMeta();
        List<Component> list=itemMeta.lore();
        if(list==null){
            list=new ArrayList<>();
        }
        list.add(Component.text("Piece: $"+(artifactHelmetType.getPrice()==0?"free":String.valueOf(artifactHelmetType.getPrice()))));
        itemMeta.lore(list);
        itemStack.setItemMeta(itemMeta);
        this.getInventory().setItem(slot,itemStack);
    }
    public ArtifactHelmetType getHelmetType(int slot){
        return this.helmetTypeHashMap.get(slot);
    }
}
