package zzxcraft.artifactFight.Listener;


import io.papermc.paper.event.entity.EntityLoadCrossbowEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactPropFather;
import zzxcraft.artifactFight.ArtifactFight;
import zzxcraft.artifactFight.PlayerArtifactMap;

import java.util.Objects;

public class ArtifactItemListener implements Listener {
    @EventHandler
    public void PlayerFightedPlayer(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player){
            if(((Player) event.getEntity()).isBlocking() && PlayerArtifactMap.ShieldPlayerMap.get(event.getEntity().getUniqueId())!=null){
                PlayerArtifactMap.ShieldPlayerMap.get(event.getEntity().getUniqueId()).OnFighted(event);
            }
            if(PlayerArtifactMap.HelmetPlayerMap.get(event.getEntity().getUniqueId())!=null) PlayerArtifactMap.HelmetPlayerMap.get(event.getEntity().getUniqueId()).OnFighted(event);
            if(PlayerArtifactMap.ChestPlatePlayerMap.get(event.getEntity().getUniqueId())!=null) PlayerArtifactMap.ChestPlatePlayerMap.get(event.getEntity().getUniqueId()).OnFighted(event);
            if(PlayerArtifactMap.LeggingPlayerMap.get(event.getEntity().getUniqueId())!=null) PlayerArtifactMap.LeggingPlayerMap.get(event.getEntity().getUniqueId()).OnFighted(event);
            if(PlayerArtifactMap.BootPlayerMap.get(event.getEntity().getUniqueId())!=null) PlayerArtifactMap.BootPlayerMap.get(event.getEntity().getUniqueId()).OnFighted(event);
            if(PlayerArtifactMap.WeaponPlayerMap.get(event.getDamager().getUniqueId())!=null && PlayerArtifactMap.WeaponPlayerMap.get(event.getDamager().getUniqueId()).getSlot()==((Player) event.getDamager()).getInventory().getHeldItemSlot()){
                PlayerArtifactMap.WeaponPlayerMap.get(event.getDamager().getUniqueId()).onFight(event);
            }
        }
    }
    @EventHandler
    public void PlayerBowHit(ProjectileHitEvent event){
        Entity hitEntity=ArtifactFight.getMainClass().getServer().getEntity(Objects.requireNonNull(event.getEntity().getOwnerUniqueId()));
        Entity hitedEntity=event.getHitEntity();
        if(hitEntity instanceof Player && hitedEntity instanceof Player){
            if(PlayerArtifactMap.BowPlayerMap.get(hitEntity.getUniqueId())!=null) PlayerArtifactMap.BowPlayerMap.get(hitEntity.getUniqueId()).onHit(event);
        }
    }
    @EventHandler
    public void PlayerBowLaunch(ProjectileLaunchEvent event){
        Entity launchEntity=ArtifactFight.getMainClass().getServer().getEntity(Objects.requireNonNull(event.getEntity().getOwnerUniqueId()));
        if(launchEntity instanceof Player){
           if(PlayerArtifactMap.BowPlayerMap.get(launchEntity.getUniqueId())!=null) PlayerArtifactMap.BowPlayerMap.get(launchEntity.getUniqueId()).onLaunch(event);
        }
    }
    @EventHandler
    public void PlayerLoadCrossBow(EntityLoadCrossbowEvent event){
        if(event.getEntity() instanceof Player){
            if(PlayerArtifactMap.BowPlayerMap.get(event.getEntity().getUniqueId())!=null) PlayerArtifactMap.BowPlayerMap.get(event.getEntity().getUniqueId()).onLoad(event);
        }
    }
    @EventHandler
    public void PlayerGlide(PlayerMoveEvent event){
        if(event.getPlayer().isFlying() && event.getPlayer().isGliding()){
            if(PlayerArtifactMap.ElytraPlayerMap.get(event.getPlayer().getUniqueId())!=null) PlayerArtifactMap.ElytraPlayerMap.get(event.getPlayer().getUniqueId()).OnGlide(event);
        }
    }
    @EventHandler
    public void PlayPropUse(PlayerInteractEvent event){
        ArtifactPropFather prop1=PlayerArtifactMap.Prop1PlayerMap.get(event.getPlayer().getUniqueId());
        ArtifactPropFather prop2=PlayerArtifactMap.Prop2PlayerMap.get(event.getPlayer().getUniqueId());
        ArtifactPropFather prop3=PlayerArtifactMap.Prop3PlayerMap.get(event.getPlayer().getUniqueId());
        if(prop1!=null){
            if(prop1.getSlot()==event.getPlayer().getInventory().getHeldItemSlot()) prop1.onUse(event);
            if(prop2.getSlot()==event.getPlayer().getInventory().getHeldItemSlot()) prop1.onUse(event);
            if(prop3.getSlot()==event.getPlayer().getInventory().getHeldItemSlot()) prop1.onUse(event);
        }
    }
}
