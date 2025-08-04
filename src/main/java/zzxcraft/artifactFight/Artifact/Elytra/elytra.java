package zzxcraft.artifactFight.Artifact.Elytra;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactElytraFather;

import java.util.ArrayList;
import java.util.Objects;

public class elytra extends ArtifactElytraFather {
    public elytra(Player player) {
        super(player);
        this.setItemStack(ItemStack.of(Material.ELYTRA));
    }
    @Override
    public void OnGlide(PlayerMoveEvent event) {

    }

    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {

    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
