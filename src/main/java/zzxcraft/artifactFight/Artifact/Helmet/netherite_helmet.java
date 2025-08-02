package zzxcraft.artifactFight.Artifact.Helmet;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactHelmetFather;

import java.util.Objects;

public class netherite_helmet extends ArtifactHelmetFather {
    public netherite_helmet(Player player) {
        super(player);
        this.setItemStack(ItemStack.of(Material.NETHERITE_HELMET));
    }

    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {

    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
