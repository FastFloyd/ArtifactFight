package zzxcraft.artifactFight.Artifact.Legging;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactLeggingFather;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class super_netherite_legging extends ArtifactLeggingFather {
    public super_netherite_legging(Player player) {
        super(player);
        ItemStack itemStack=ItemStack.of(Material.NETHERITE_LEGGINGS);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("不摧护腿"));

        itemMeta.lore(List.of(Component.text("不摧 IV", TextColor.color(168,168,168))));
        itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(itemMeta);
        this.setItemStack(itemStack);
    }

    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {
        event.setDamage(Math.min(event.getDamage(),10.0));
        event.setDamage(Math.max(0,event.getDamage()-1));
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
