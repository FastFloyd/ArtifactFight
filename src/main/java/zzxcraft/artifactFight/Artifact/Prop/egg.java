package zzxcraft.artifactFight.Artifact.Prop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactPropFather;
import zzxcraft.artifactFight.PlayerArtifactMap;

import java.util.Objects;

public class egg extends ArtifactPropFather {
    public egg(Player player, Integer slot) {
        super(player, slot,16,20,Material.EGG);
    }

    @Override
    public void onUse(PlayerInteractEvent event) {
        super.onUse(event);
    }

    @Override
    public void onLaunch(ProjectileLaunchEvent event) {
        PlayerArtifactMap.ProjectileMap.put(event.getEntity().getUniqueId(),new zzxcraft.artifactFight.Artifact.Projectile.egg(this.getPlayer(),event.getEntity()));
    }

    @Override
    public void run() {
        super.run();
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
