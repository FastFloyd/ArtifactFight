package zzxcraft.artifactFight.Inventory.GetInventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Type.ArtifactBowType;
import zzxcraft.artifactFight.ArtifactFight;
import zzxcraft.artifactFight.Inventory.BuyInventory.BuyBowInventory;

public class GetBowInventory implements InventoryHolder {
    Inventory inventory;
    Player player;
    ArtifactBowType artifactBowType;
    InventoryHolder super_inventory;
    public GetBowInventory(Player player,ArtifactBowType artifactBowType,InventoryHolder super_inventory){
        this.player=player;
        this.artifactBowType=artifactBowType;
        this.super_inventory=super_inventory;
        this.inventory= ArtifactFight.getMainClass().getServer().createInventory(this,9);
        this.inventory.setItem(4,this.artifactBowType.getItemStack());
        this.inventory.setItem(0, NameItemStack(ItemStack.of(Material.RED_WOOL),"取消"));
        this.inventory.setItem(8,NameItemStack(ItemStack.of(Material.GREEN_WOOL),"确定"));
    }
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
    public InventoryHolder getSuper_inventory(){
        return this.super_inventory;
    }
    public ArtifactBowType getArtifactBowType(){
        return this.artifactBowType;
    }
    private static ItemStack NameItemStack(ItemStack itemStack,String name){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(name));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
