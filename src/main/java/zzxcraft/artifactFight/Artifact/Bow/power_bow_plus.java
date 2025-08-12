package zzxcraft.artifactFight.Artifact.Bow;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactBowFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactProjectileFather;
import zzxcraft.artifactFight.Artifact.Projectile.arrow;
import zzxcraft.artifactFight.Artifact.Projectile.power_arrow_plus;
import zzxcraft.artifactFight.PlayerArtifactMap;

import java.util.List;
import java.util.Objects;

public class power_bow_plus extends ArtifactBowFather {
    public power_bow_plus(Player player,Integer slot) {
        super(player,slot);
        ItemStack itemStack=ItemStack.of(Material.BOW);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("力量III弓箭"));
        itemMeta.lore(List.of(Component.text("力量 III", TextColor.color(168,168,168))));
        itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(itemMeta);
        this.setItemStack(itemStack);
    }


    @Override
    public void onLaunch(ProjectileLaunchEvent event) {
        PlayerArtifactMap.ProjectileMap.put(event.getEntity().getUniqueId(),new power_arrow_plus(this.getPlayer(),event.getEntity()));
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
