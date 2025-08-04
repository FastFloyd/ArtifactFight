package zzxcraft.artifactFight.Artifact.MainWeapon;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactMainWeaponFather;

import java.util.Map;
import java.util.Objects;

public class super_fire_sword extends ArtifactMainWeaponFather {
    public super_fire_sword(Player player, Integer slot) {
        super(player, slot);
        this.setItemStack(ItemStack.of(Material.NETHERITE_SWORD));
        this.getItemStack().setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments(Map.of(Enchantment.FIRE_ASPECT,5),true));
    }

    @Override
    public void OnFight(EntityDamageByEntityEvent event) {

    }

    @Override
    public void run() {

        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
