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
import zzxcraft.artifactFight.Artifact.Fathers.*;
import zzxcraft.artifactFight.Artifact.Type.ArtifactMainWeaponType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactShieldType;
import zzxcraft.artifactFight.ArtifactFight;
import zzxcraft.artifactFight.PlayerArtifactMap;

import java.util.Objects;

public class ArtifactItemListener implements Listener {
    @EventHandler
    public void PlayerFightedPlayer(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player){
            if(((Player) event.getEntity()).isBlocking() && PlayerArtifactMap.DeputyWeaponPlayerMap.get(event.getEntity().getUniqueId())!=null && PlayerArtifactMap.DeputyWeaponPlayerMap.get(event.getEntity().getUniqueId()) instanceof ArtifactShieldFather){
                ((ArtifactShieldFather) PlayerArtifactMap.DeputyWeaponPlayerMap.get(event.getEntity().getUniqueId())).OnFighted(event);
            }
            if(((Player) event.getEntity()).isBlocking() && PlayerArtifactMap.MainWeaponPlayerMap.get(event.getEntity().getUniqueId())!=null && PlayerArtifactMap.MainWeaponPlayerMap.get(event.getEntity().getUniqueId()) instanceof ArtifactShieldFather){
                ((ArtifactShieldFather) PlayerArtifactMap.MainWeaponPlayerMap.get(event.getEntity().getUniqueId())).OnFighted(event);
            }
            if(PlayerArtifactMap.HelmetPlayerMap.get(event.getEntity().getUniqueId())!=null) PlayerArtifactMap.HelmetPlayerMap.get(event.getEntity().getUniqueId()).OnFighted(event);
            if(PlayerArtifactMap.ChestPlatePlayerMap.get(event.getEntity().getUniqueId())!=null){
                if(PlayerArtifactMap.ChestPlatePlayerMap.get(event.getEntity().getUniqueId()) instanceof ArtifactChestPlateFather){
                    ((ArtifactChestPlateFather) PlayerArtifactMap.ChestPlatePlayerMap.get(event.getEntity().getUniqueId())).OnFighted(event);
                }
                else if(PlayerArtifactMap.ChestPlatePlayerMap.get(event.getEntity().getUniqueId()) instanceof ArtifactElytraFather){
                    ((ArtifactElytraFather) PlayerArtifactMap.ChestPlatePlayerMap.get(event.getEntity().getUniqueId())).OnFighted(event);
                }
            }
            if(PlayerArtifactMap.LeggingPlayerMap.get(event.getEntity().getUniqueId())!=null) PlayerArtifactMap.LeggingPlayerMap.get(event.getEntity().getUniqueId()).OnFighted(event);
            if(PlayerArtifactMap.BootPlayerMap.get(event.getEntity().getUniqueId())!=null) PlayerArtifactMap.BootPlayerMap.get(event.getEntity().getUniqueId()).OnFighted(event);
            if(PlayerArtifactMap.MainWeaponPlayerMap.get(event.getDamager().getUniqueId())!=null && PlayerArtifactMap.MainWeaponPlayerMap.get(event.getDamager().getUniqueId()).getSlot()==((Player) event.getDamager()).getInventory().getHeldItemSlot()){
                ((ArtifactMainWeaponFather) PlayerArtifactMap.MainWeaponPlayerMap.get(event.getEntity().getUniqueId())).OnFight(event);
            }
            if(PlayerArtifactMap.DeputyWeaponPlayerMap.get(event.getDamager().getUniqueId())!=null && PlayerArtifactMap.DeputyWeaponPlayerMap.get(event.getDamager().getUniqueId()).getSlot()==((Player) event.getDamager()).getInventory().getHeldItemSlot()){
                ((ArtifactMainWeaponFather) PlayerArtifactMap.DeputyWeaponPlayerMap.get(event.getEntity().getUniqueId())).OnFight(event);
            }
        }
    }
    @EventHandler
    public void PlayerBowHit(ProjectileHitEvent event){
        Entity hitEntity=ArtifactFight.getMainClass().getServer().getEntity(Objects.requireNonNull(event.getEntity().getOwnerUniqueId()));
        Entity hitedEntity=event.getHitEntity();
        if(hitEntity instanceof Player && hitedEntity instanceof Player){
            if(PlayerArtifactMap.DeputyWeaponPlayerMap.get(hitEntity.getUniqueId())!=null && PlayerArtifactMap.DeputyWeaponPlayerMap.get(hitEntity.getUniqueId()) instanceof ArtifactBowFather && ((ArtifactBowFather) PlayerArtifactMap.DeputyWeaponPlayerMap.get(hitEntity.getUniqueId())).getSlot()==((Player) hitEntity).getInventory().getHeldItemSlot()) ((ArtifactBowFather)PlayerArtifactMap.DeputyWeaponPlayerMap.get(hitEntity.getUniqueId())).onHit(event);
        }
        if(hitEntity instanceof Player && hitedEntity instanceof Player){
            if(PlayerArtifactMap.MainWeaponPlayerMap.get(hitEntity.getUniqueId())!=null && PlayerArtifactMap.MainWeaponPlayerMap.get(hitEntity.getUniqueId()) instanceof ArtifactBowFather && ((ArtifactBowFather) PlayerArtifactMap.MainWeaponPlayerMap.get(hitEntity.getUniqueId())).getSlot()==((Player) hitEntity).getInventory().getHeldItemSlot()) ((ArtifactBowFather)PlayerArtifactMap.MainWeaponPlayerMap.get(hitEntity.getUniqueId())).onHit(event);
        }
    }
    @EventHandler
    public void PlayerBowLaunch(ProjectileLaunchEvent event){
        Entity launchEntity=ArtifactFight.getMainClass().getServer().getEntity(Objects.requireNonNull(event.getEntity().getOwnerUniqueId()));
        if(launchEntity instanceof Player){
           if(PlayerArtifactMap.MainWeaponPlayerMap.get(launchEntity.getUniqueId())!=null && PlayerArtifactMap.MainWeaponPlayerMap.get(launchEntity.getUniqueId()) instanceof ArtifactBowFather && (PlayerArtifactMap.MainWeaponPlayerMap.get(launchEntity.getUniqueId())).getSlot()==((Player) launchEntity).getInventory().getHeldItemSlot()) ((ArtifactBowFather) PlayerArtifactMap.MainWeaponPlayerMap.get(launchEntity.getUniqueId())).onLaunch(event);
           if(PlayerArtifactMap.DeputyWeaponPlayerMap.get(launchEntity.getUniqueId())!=null && PlayerArtifactMap.DeputyWeaponPlayerMap.get(launchEntity.getUniqueId()) instanceof ArtifactBowFather && (PlayerArtifactMap.DeputyWeaponPlayerMap.get(launchEntity.getUniqueId())).getSlot()==((Player) launchEntity).getInventory().getHeldItemSlot()) ((ArtifactBowFather) PlayerArtifactMap.DeputyWeaponPlayerMap.get(launchEntity.getUniqueId())).onLaunch(event);

        }
    }
    @EventHandler
    public void PlayerGlide(PlayerMoveEvent event){
        if(event.getPlayer().isFlying() && event.getPlayer().isGliding()){
            if(PlayerArtifactMap.ChestPlatePlayerMap.get(event.getPlayer().getUniqueId())!=null && PlayerArtifactMap.ChestPlatePlayerMap.get(event.getPlayer().getUniqueId()) instanceof ArtifactElytraFather) ((ArtifactElytraFather) PlayerArtifactMap.ChestPlatePlayerMap.get(event.getPlayer().getUniqueId())).OnGlide(event);
        }
    }
    @EventHandler
    public void PlayPropUse(PlayerInteractEvent event){
        ArtifactPropFather prop1=PlayerArtifactMap.Prop1PlayerMap.get(event.getPlayer().getUniqueId());
        ArtifactPropFather prop2=PlayerArtifactMap.Prop2PlayerMap.get(event.getPlayer().getUniqueId());
        ArtifactPropFather prop3=PlayerArtifactMap.Prop3PlayerMap.get(event.getPlayer().getUniqueId());
        if(prop1!=null && prop1.getSlot()==event.getPlayer().getInventory().getHeldItemSlot()) prop1.onUse(event);
        if(prop2!=null && prop2.getSlot()==event.getPlayer().getInventory().getHeldItemSlot()) prop2.onUse(event);
        if(prop3!=null && prop3.getSlot()==event.getPlayer().getInventory().getHeldItemSlot()) prop3.onUse(event);
    }
}
