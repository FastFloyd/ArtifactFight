package zzxcraft.artifactFight.Artifact.Elytra;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactElytraFather;

import java.util.Objects;

public class super_speed_elytra extends ArtifactElytraFather {
    public super_speed_elytra(Player player) {
        super(player);
        ItemStack itemStack=ItemStack.of(Material.ELYTRA);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("闪电"));
        itemStack.setItemMeta(itemMeta);
        this.setItemStack(itemStack);
    }

    @Override
    public void OnGlide(PlayerMoveEvent event) {

        this.getPlayer().setVelocity(this.getPlayer().getVelocity().multiply(2.5));
    }

    @Override
    public void OnFighted(EntityDamageByEntityEvent event) {

    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
