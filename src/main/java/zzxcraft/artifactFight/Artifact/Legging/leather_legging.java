package zzxcraft.artifactFight.Artifact.Legging;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactLeggingFather;
import zzxcraft.artifactFight.Artifact.Type.ArtifactLeggingType;

import java.util.Objects;

public class leather_legging extends ArtifactLeggingFather {
    public leather_legging(Player player) {
        super(player);
        this.setItemStack(ItemStack.of(Material.LEATHER_LEGGINGS));
    }

    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {

    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
