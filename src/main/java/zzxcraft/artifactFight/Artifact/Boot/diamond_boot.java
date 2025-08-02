package zzxcraft.artifactFight.Artifact.Boot;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactBootFather;

import java.util.Objects;

public class diamond_boot extends ArtifactBootFather {
    public diamond_boot(Player player) {
        super(player);
        this.setItemStack(ItemStack.of(Material.DIAMOND_BOOTS));
    }
    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {

    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
