package zzxcraft.artifactFight.Artifact.MainWeapon;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactHelmetFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactMainWeaponFather;

import java.util.Map;
import java.util.Objects;

public class dragon_sword extends ArtifactMainWeaponFather {
    public dragon_sword(Player player, Integer slot) {
        super(player, slot);
        ItemStack itemStack=ItemStack.of(Material.DIAMOND_SWORD);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("神龙剑"));
        itemStack.setItemMeta(itemMeta);
        itemStack.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments(Map.of(Enchantment.BREACH,3),true));
        this.setItemStack(itemStack);
    }

    @Override
    public void OnFight(EntityDamageByEntityEvent event) {
        Player player= (Player) event.getEntity();
        event.setDamage(event.getDamage()+player.getAttribute(Attribute.ARMOR).getValue()*0.1+player.getAttribute(Attribute.ARMOR_TOUGHNESS).getValue()*0.2);
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
