package zzxcraft.artifactFight.Artifact.ChestPlate;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactChestPlateFather;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

public class speed_chestplate_plus_plus extends ArtifactChestPlateFather {
    public speed_chestplate_plus_plus(Player player) {
        super(player);
        ItemStack itemStack=ItemStack.of(Material.LEATHER_CHESTPLATE);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("强化旅行胸甲"));
        itemMeta.lore(List.of(Component.text("极速 III", TextColor.color(168,168,168))));
        itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addAttributeModifier(Attribute.MOVEMENT_SPEED,new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),0.015, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        itemMeta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        itemStack.setItemMeta(itemMeta);
        this.setItemStack(itemStack);
    }

    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {

    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
