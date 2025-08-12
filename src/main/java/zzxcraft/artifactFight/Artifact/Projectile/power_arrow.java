package zzxcraft.artifactFight.Artifact.Projectile;

import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileHitEvent;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactProjectileFather;

public class power_arrow extends ArtifactProjectileFather {
    public power_arrow(Player player, Projectile projectile) {
        super(player, projectile);
    }

    @Override
    public void run(){
        super.run();
    }

    @Override
    public void onHit(ProjectileHitEvent event) {
        ((Player) event.getHitEntity()).damage(5, DamageSource.builder(DamageType.ARROW).withCausingEntity(this.getPlayer()).withDirectEntity(event.getHitEntity()).withDamageLocation(this.getProjectile().getLocation()).build());
        event.setCancelled(true);
        this.finish();
    }
}
