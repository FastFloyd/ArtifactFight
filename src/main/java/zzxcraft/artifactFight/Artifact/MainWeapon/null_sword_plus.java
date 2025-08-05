package zzxcraft.artifactFight.Artifact.MainWeapon;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactMainWeaponFather;

import java.util.List;
import java.util.Objects;

public class null_sword_plus extends ArtifactMainWeaponFather {
    public null_sword_plus(Player player, Integer slot) {
        super(player, slot);
        ItemStack itemStack=ItemStack.of(Material.IRON_SWORD);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("强化湮灭之剑"));
        itemMeta.lore(List.of(Component.text("湮灭 III", TextColor.color(168,168,168))));
        itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(itemMeta);
        this.setItemStack(itemStack);
    }

    @Override
    public void OnFight(EntityDamageByEntityEvent event) {
        event.setDamage(this.getPlayer().getAttribute(Attribute.MAX_HEALTH).getBaseValue()*0.3);
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
