package zzxcraft.artifactFight.Artifact.Shield;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactShieldFather;

import java.util.Map;
import java.util.Objects;

public class super_thorn_shield extends ArtifactShieldFather {
    public super_thorn_shield(Player player,Integer slot) {
        super(player,slot);
        ItemStack itemStack=ItemStack.of(Material.SHIELD);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("强化荆棘盾牌"));
        itemStack.setItemMeta(itemMeta);
        itemStack.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments(Map.of(Enchantment.THORNS,2),true));
        this.setItemStack(itemStack);
    }

    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {
        ((Player) event.getDamager()).damage(5.0, DamageSource.builder(DamageType.THORNS).build());
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
