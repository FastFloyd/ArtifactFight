package zzxcraft.artifactFight.Artifact.Shield;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactShieldFather;

import java.util.ArrayList;
import java.util.Objects;

public class thorn_shield extends ArtifactShieldFather {
    public thorn_shield(Player player,Integer slot) {
        super(player,slot);
        this.setItemStack(ItemStack.of(Material.SHIELD));
    }
    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {

        ((Player) event.getDamager()).damage(2);
    }


    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
