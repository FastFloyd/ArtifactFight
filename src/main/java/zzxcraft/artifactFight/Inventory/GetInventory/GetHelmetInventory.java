package zzxcraft.artifactFight.Inventory.GetInventory;

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
import zzxcraft.artifactFight.Inventory.BuyInventory.BuyElytraInventory;
import zzxcraft.artifactFight.Inventory.BuyInventory.BuyHelmetInventory;

public class GetHelmetInventory implements InventoryHolder {
    Inventory inventory;
    Player player;
    ArtifactHelmetType artifactHelmetType;
    InventoryHolder super_inventory;
    public GetHelmetInventory(Player player,ArtifactHelmetType artifactHelmetType,InventoryHolder super_inventory){
        this.player=player;
        this.artifactHelmetType=artifactHelmetType;
        this.super_inventory=super_inventory;
        this.inventory=ArtifactFight.getMainClass().getServer().createInventory(this,9);
        this.inventory.setItem(4,this.artifactHelmetType.getItemStack());
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
    public ArtifactHelmetType getArtifactHelmetType(){
        return this.artifactHelmetType;
    }
    private static ItemStack NameItemStack(ItemStack itemStack,String name){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(name));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
