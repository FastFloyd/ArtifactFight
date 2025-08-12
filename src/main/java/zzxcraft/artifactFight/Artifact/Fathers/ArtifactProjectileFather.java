package zzxcraft.artifactFight.Artifact.Fathers;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import zzxcraft.artifactFight.ArtifactFight;
import zzxcraft.artifactFight.PlayerArtifactMap;

import java.util.UUID;

public abstract class ArtifactProjectileFather extends BukkitRunnable {
    private Player player;
    private UUID uuid;
    public ArtifactProjectileFather(Player player,Projectile projectile){
        this.player=player;
        this.uuid=projectile.getUniqueId();
        this.runTaskTimer(ArtifactFight.getMainClass(),0,1);
    }
    public Player getPlayer(){
        return this.player;
    }
    public Projectile getProjectile(){
        return (Projectile) ArtifactFight.getMainClass().getServer().getEntity(this.uuid);
    }
    public void run() {
        Entity projectile=  ArtifactFight.getMainClass().getServer().getEntity(this.uuid);
        if(projectile==null){
            this.finish();
            return;
        }
        if(!(projectile instanceof Projectile)){
            this.finish();
            return;
        }
        if(projectile.isDead()){
            this.finish();
            return;
        }
        if(projectile.isOnGround()){
            projectile.remove();
            this.finish();
            return;
        }
    }
    public abstract void onHit(ProjectileHitEvent event);
    public void finish(){
        PlayerArtifactMap.ProjectileMap.remove(this.uuid);
        this.cancel();
    }
}
