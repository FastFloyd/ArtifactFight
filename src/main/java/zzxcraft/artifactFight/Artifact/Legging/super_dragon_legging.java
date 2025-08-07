package zzxcraft.artifactFight.Artifact.Legging;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.damage.DamageType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactLeggingFather;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class super_dragon_legging extends ArtifactLeggingFather {
    public super_dragon_legging(Player player) {
        super(player);
        ItemStack itemStack=ItemStack.of(Material.DIAMOND_LEGGINGS);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("神龙护腿"));
        itemMeta.lore(List.of(Component.text("神龙 IV", TextColor.color(168,168,168))));
        itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),2.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
        itemMeta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(),UUID.randomUUID().toString()),3, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.LEGS));
        itemMeta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS,new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(),UUID.randomUUID().toString()),2, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.LEGS));
        itemStack.setItemMeta(itemMeta);
        this.setItemStack(itemStack);
    }

    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {
        if(!event.getDamageSource().getDamageType().equals(DamageType.PLAYER_ATTACK)){
            event.setDamage(Math.min(10.0,event.getDamage()));
            event.setDamage(event.getDamage()*0.7);
        }
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
