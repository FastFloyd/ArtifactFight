package zzxcraft.artifactFight.Artifact.Shield;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactShieldFather;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class thorn_shield extends ArtifactShieldFather {
    public thorn_shield(Player player,Integer slot) {
        super(player,slot);
        ItemStack itemStack=ItemStack.of(Material.SHIELD);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("荆棘盾牌"));

        itemMeta.lore(List.of(Component.text("荆棘 I", TextColor.color(168,168,168))));
        itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        itemStack.setItemMeta(itemMeta);
        this.setItemStack(itemStack);
    }
    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {
        ((Player) event.getDamager()).damage(2.0, DamageSource.builder(DamageType.THORNS).withCausingEntity(event.getEntity()).build());

    }


    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
