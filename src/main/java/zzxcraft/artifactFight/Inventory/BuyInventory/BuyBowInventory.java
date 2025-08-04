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
import zzxcraft.artifactFight.Artifact.Type.ArtifactBowType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactHelmetType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

public class BuyBowInventory implements InventoryHolder {
    Inventory inventory;
    Player player;
    ArtifactBowType artifactBowType;
    InventoryHolder super_inventory;
    private HashMap<Integer,ArtifactBowType> bowTypeHashMap=new HashMap<>();
    public BuyBowInventory(Player player, ArtifactBowType artifactBowType,InventoryHolder super_inventory){
        this.super_inventory=super_inventory;
        this.inventory= ArtifactFight.getMainClass().getServer().createInventory(this,54);
        this.player=player;
        this.artifactBowType=artifactBowType;
        for(int i=0;i<artifactBowType.getChildren().size();i++){
            this.addBowType(i, (ArtifactBowType) artifactBowType.getChildren().toArray()[i]);
        }
        this.inventory.setItem(49, ItemStack.of(Material.BLACK_WOOL));
    }
    public InventoryHolder getSuperInventory(){return super_inventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
    private void addBowType(int slot, ArtifactBowType artifactBowType){
        this.bowTypeHashMap.put(slot,artifactBowType);
        ItemStack itemStack=new ItemStack(artifactBowType.getItemStack());
        ItemMeta itemMeta=itemStack.getItemMeta();
        List<Component> list=itemMeta.lore();
        if(list==null){
            list=new ArrayList<>();
        }
        list.add(Component.text("Piece: $"+(artifactBowType.getPrice()==0?"free":String.valueOf(artifactBowType.getPrice()))));
        itemMeta.lore(list);
        itemStack.setItemMeta(itemMeta);
        this.getInventory().setItem(slot,itemStack);
    }
    public ArtifactBowType getBowType(int slot){
        return this.bowTypeHashMap.get(slot);
    }
}
