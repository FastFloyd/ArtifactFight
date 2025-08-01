package zzxcraft.artifactFight.Artifact.Bow;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.event.entity.EntityLoadCrossbowEvent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactBowFather;

import java.util.Objects;

public class super_power_bow extends ArtifactBowFather {
    public super_power_bow(Player player,Integer slot) {
        super(player,slot);
        this.setItemStack(ItemStack.of(Material.BOW));
        this.getItemStack().addEnchantment(Enchantment.POWER,5);
    }

    @Override
    public void onHit(ProjectileHitEvent event) {

    }

    @Override
    public void onLaunch(ProjectileLaunchEvent event) {
        if (this.getItemStack().getData(DataComponentTypes.DAMAGE) != null) {
            this.getItemStack().setData(DataComponentTypes.DAMAGE,this.getItemStack().getData(DataComponentTypes.DAMAGE).intValue()-1);
        }
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
