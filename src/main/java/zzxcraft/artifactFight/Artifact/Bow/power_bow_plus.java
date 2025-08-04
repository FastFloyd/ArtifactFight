package zzxcraft.artifactFight.Artifact.Bow;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactBowFather;

import java.util.Objects;

public class power_bow_plus extends ArtifactBowFather {
    public power_bow_plus(Player player,Integer slot) {
        super(player,slot);
        ItemStack itemStack=ItemStack.of(Material.BOW);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("力量III弓箭"));
        itemStack.setItemMeta(itemMeta);
        itemStack.addEnchantment(Enchantment.POWER,3);
        this.setItemStack(itemStack);
    }

    @Override
    public void onHit(ProjectileHitEvent event) {

    }

    @Override
    public void onLaunch(ProjectileLaunchEvent event) {

    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
