package zzxcraft.artifactFight.Artifact.MainWeapon;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactMainWeaponFather;

import java.util.Objects;

public class iron_sword extends ArtifactMainWeaponFather {
    public iron_sword(Player player,Integer slot) {
        super(player,slot);
        this.setItemStack(ItemStack.of(Material.IRON_SWORD));
    }

    @Override
    public void OnFight(EntityDamageByEntityEvent event) {
        if (this.getItemStack().getData(DataComponentTypes.DAMAGE) != null) {
            this.getItemStack().setData(DataComponentTypes.DAMAGE,this.getItemStack().getData(DataComponentTypes.DAMAGE).intValue()-1);
        }
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
