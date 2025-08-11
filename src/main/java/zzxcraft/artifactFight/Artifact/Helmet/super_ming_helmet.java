package zzxcraft.artifactFight.Artifact.Helmet;

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
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactHelmetFather;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class super_ming_helmet extends ArtifactHelmetFather {
    public super_ming_helmet(Player player) {
        super(player);
        ItemStack itemStack=ItemStack.of(Material.IRON_HELMET);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("明光"));
        itemMeta.lore(List.of(Component.text("血战 IV", TextColor.color(168,168,168))));
        itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        itemMeta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(),UUID.randomUUID().toString()),3, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.HEAD));
        itemStack.setItemMeta(itemMeta);
        this.setItemStack(itemStack);
    }

    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {
        event.setDamage(Math.max(1,Math.min(this.getPlayer().getHealth()*0.3,event.getDamage())));

    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());

    }
}
