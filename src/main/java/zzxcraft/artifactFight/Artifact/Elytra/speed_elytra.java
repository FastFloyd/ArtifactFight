package zzxcraft.artifactFight.Artifact.Elytra;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactElytraFather;

import java.util.ArrayList;
import java.util.Objects;

public class speed_elytra extends ArtifactElytraFather {
    public speed_elytra(Player player) {
        super(player);
        this.setItemStack(ItemStack.of(Material.ELYTRA));
    }
    @Override
    public void OnGlide(PlayerMoveEvent event) {
        if (this.getItemStack().getData(DataComponentTypes.DAMAGE) != null) {
            this.getItemStack().setData(DataComponentTypes.DAMAGE,this.getItemStack().getData(DataComponentTypes.DAMAGE).intValue()-1);
        }
        this.getPlayer().setVelocity(this.getPlayer().getVelocity().multiply(1.5));
    }

    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {

    }


    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
