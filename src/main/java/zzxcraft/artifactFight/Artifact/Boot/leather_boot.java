package zzxcraft.artifactFight.Artifact.Boot;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactBootFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactLeggingFather;
import zzxcraft.artifactFight.Artifact.Type.ArtifactLeggingType;

import java.util.Objects;

public class leather_boot extends ArtifactBootFather {

    public leather_boot(Player player) {
        super(player);
        this.setItemStack(ItemStack.of(Material.LEATHER_BOOTS));
    }

    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {
        if (this.getItemStack().getData(DataComponentTypes.DAMAGE) != null) {
            this.getItemStack().setData(DataComponentTypes.DAMAGE,this.getItemStack().getData(DataComponentTypes.DAMAGE).intValue()-1);
        }
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getBoots(),this.getItemStack())) this.getPlayer().getInventory().setBoots(this.getItemStack());
    }
}
