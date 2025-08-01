package zzxcraft.artifactFight.Artifact.Legging;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactLeggingFather;

import java.util.ArrayList;
import java.util.Objects;

public class iron_legging extends ArtifactLeggingFather {
    public iron_legging(Player player) {
        super(player);
        this.setItemStack(ItemStack.of(Material.IRON_LEGGINGS));
    }
    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {
        if (this.getItemStack().getData(DataComponentTypes.DAMAGE) != null) {
            this.getItemStack().setData(DataComponentTypes.DAMAGE,this.getItemStack().getData(DataComponentTypes.DAMAGE).intValue()-1);
        }
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getLeggings(),this.getItemStack())) this.getPlayer().getInventory().setLeggings(this.getItemStack());
    }
}
