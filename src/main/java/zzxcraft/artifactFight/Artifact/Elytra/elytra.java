package zzxcraft.artifactFight.Artifact.Elytra;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactElytraFather;

import java.util.ArrayList;
import java.util.Objects;

public class elytra extends ArtifactElytraFather {
    public elytra(Player player) {
        super(player);
        this.setItemStack(ItemStack.of(Material.ELYTRA));
        this.setSlot(102);
    }
    @Override
    public void OnGlide(PlayerMoveEvent event) {
        if (this.getItemStack().getData(DataComponentTypes.DAMAGE) != null) {
            this.getItemStack().setData(DataComponentTypes.DAMAGE,this.getItemStack().getData(DataComponentTypes.DAMAGE).intValue()-1);
        }
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
