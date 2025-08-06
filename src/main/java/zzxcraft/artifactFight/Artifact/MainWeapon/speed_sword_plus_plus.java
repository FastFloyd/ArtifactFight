package zzxcraft.artifactFight.Artifact.MainWeapon;

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
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactMainWeaponFather;
import zzxcraft.artifactFight.ArtifactFight;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class speed_sword_plus_plus extends ArtifactMainWeaponFather {
    public speed_sword_plus_plus(Player player, Integer slot) {
        super(player, slot);
        ItemStack itemStack=ItemStack.of(Material.WOODEN_SWORD);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("强化轻盈之剑"));
        itemMeta.lore(List.of(Component.text("轻盈 III", TextColor.color(168,168,168))));
        itemMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),4.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        itemMeta.addAttributeModifier(Attribute.ATTACK_SPEED,new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),-0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(itemMeta);
        this.setItemStack(itemStack);
    }

    @Override
    public void OnFight(EntityDamageByEntityEvent event) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
