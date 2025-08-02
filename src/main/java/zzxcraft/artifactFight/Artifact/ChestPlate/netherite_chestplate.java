package zzxcraft.artifactFight.Artifact.ChestPlate;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactChestPlateFather;
import zzxcraft.artifactFight.Artifact.Type.ArtifactChestPlateType;

import java.util.Objects;

public class netherite_chestplate extends ArtifactChestPlateFather {
    public netherite_chestplate(Player player) {
        super(player);
        this.setItemStack(ItemStack.of(Material.NETHERITE_CHESTPLATE));
    }

    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {

    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
