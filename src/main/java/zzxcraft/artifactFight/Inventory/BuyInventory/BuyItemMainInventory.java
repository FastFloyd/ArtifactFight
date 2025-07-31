package zzxcraft.artifactFight.Inventory.BuyInventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Fathers.*;
import zzxcraft.artifactFight.ArtifactFight;

public class BuyItemMainInventory implements InventoryHolder {
    Inventory inventory;
    Player player;

    public BuyItemMainInventory(Player player){
        this.player=player;
        this.inventory= ArtifactFight.getMainClass().getServer().createInventory(this,9);
        inventory.setItem(0,ItemStack.of(Material.LEATHER_HELMET));
        inventory.setItem(1,ItemStack.of(Material.LEATHER_CHESTPLATE));
        inventory.setItem(2,ItemStack.of(Material.LEATHER_LEGGINGS));
        inventory.setItem(3,ItemStack.of(Material.LEATHER_BOOTS));
        inventory.setItem(4,ItemStack.of(Material.WOODEN_SWORD));
        inventory.setItem(5,ItemStack.of(Material.BOW));
        inventory.setItem(6,ItemStack.of(Material.SLIME_BALL));
        inventory.setItem(7,ItemStack.of(Material.ELYTRA));
        inventory.setItem(8,ItemStack.of(Material.SHIELD));
    }
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
