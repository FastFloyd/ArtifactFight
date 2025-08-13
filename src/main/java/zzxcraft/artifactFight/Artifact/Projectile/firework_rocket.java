package zzxcraft.artifactFight.Artifact.Projectile;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileHitEvent;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactProjectileFather;

public class firework_rocket extends ArtifactProjectileFather {
    public firework_rocket(Player player, Projectile projectile) {
        super(player, projectile);
    }

    @Override
    public void run(){
        super.run();
    }

    @Override
    public void onHit(ProjectileHitEvent event) {
        this.getProjectile().getWorld().createExplosion(this.getProjectile().getX(),this.getProjectile().getY(),this.getProjectile().getZ(),1.0f,false,false);
        event.setCancelled(true);
        this.finish();
    }
}
