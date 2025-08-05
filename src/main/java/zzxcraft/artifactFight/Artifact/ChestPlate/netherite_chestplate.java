package zzxcraft.artifactFight.Artifact.ChestPlate;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactChestPlateFather;
import zzxcraft.artifactFight.Artifact.Type.ArtifactChestPlateType;

import java.util.List;
import java.util.Objects;

public class netherite_chestplate extends ArtifactChestPlateFather {
    public netherite_chestplate(Player player) {
        super(player);
        ItemStack itemStack=ItemStack.of(Material.NETHERITE_CHESTPLATE);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("下界合金胸甲"));
        itemMeta.lore(List.of(Component.text("抗火 IV", TextColor.color(168,168,168))));
        itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
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
