package zzxcraft.artifactFight.Artifact.Boot;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactBootFather;

import java.util.*;

public class iron_boot extends ArtifactBootFather {
    public iron_boot(Player player) {
        super(player);
        this.setItemStack(ItemStack.of(Material.IRON_BOOTS));
    }
    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {

    }


    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
