package zzxcraft.artifactFight.Listener;


import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import zzxcraft.artifactFight.Artifact.Fathers.*;
import zzxcraft.artifactFight.ArtifactFight;
import zzxcraft.artifactFight.PlayerArtifactMap;

import java.util.HashMap;
import java.util.Objects;

public class ArtifactItemListener implements Listener {
    private final JavaPlugin javaPlugin=ArtifactFight.getMainClass();
    @EventHandler
    public void PlayerFightedPlayer(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player){
            HashMap<Integer, ArtifactFather> hashMap=PlayerArtifactMap.ArtifactMap.get(event.getEntity().getUniqueId());
            HashMap<Integer, ArtifactFather> hashMap1=PlayerArtifactMap.ArtifactMap.get(event.getDamager().getUniqueId());
            if(hashMap==null){
                event.setCancelled(true);
                return;
            }
            if(((Player) event.getEntity()).isBlocking() && hashMap.get(((Player) event.getEntity()).getInventory().getHeldItemSlot())!=null && hashMap.get(((Player) event.getEntity()).getInventory().getHeldItemSlot()) instanceof ArtifactShieldFather){
                ((ArtifactShieldFather) hashMap.get(((Player) event.getEntity()).getInventory().getHeldItemSlot())).OnFighted(event);
            }
            else if(((Player) event.getEntity()).isBlocking() && hashMap.get(40)!=null && hashMap.get(40) instanceof ArtifactShieldFather){
                ((ArtifactShieldFather) hashMap.get(40)).OnFighted(event);
            }
            else{
                if(hashMap.get(39)!=null) ((ArtifactHelmetFather)hashMap.get(39)).OnFighted(event);
                if(hashMap.get(38)!=null){
                    if(hashMap.get(38) instanceof ArtifactChestPlateFather){
                        ((ArtifactChestPlateFather)hashMap.get(38)).OnFighted(event);
                    }
                    else{
                        ((ArtifactElytraFather)hashMap.get(38)).OnFighted(event);
                    }
                }
                if(hashMap.get(37)!=null) ((ArtifactLeggingFather)hashMap.get(37)).OnFighted(event);
                if(hashMap.get(36)!=null) ((ArtifactBootFather)hashMap.get(36)).OnFighted(event);
                if(hashMap1.get(((Player) event.getDamager()).getInventory().getHeldItemSlot())!=null && hashMap1.get(((Player) event.getDamager()).getInventory().getHeldItemSlot()) instanceof ArtifactMainWeaponFather){
                    ((ArtifactMainWeaponFather) hashMap1.get(((Player) event.getDamager()).getInventory().getHeldItemSlot())).OnFight(event);
                }
            }

            PlayerArtifactMap.ArtifactMap.remove(event.getEntity().getUniqueId());
            PlayerArtifactMap.ArtifactMap.remove(event.getDamager().getUniqueId());
            PlayerArtifactMap.ArtifactMap.put(event.getEntity().getUniqueId(),hashMap);
            PlayerArtifactMap.ArtifactMap.put(event.getDamager().getUniqueId(),hashMap1);
        }
    }
    @EventHandler
    public void PlayerBowHit(ProjectileHitEvent event){
        if(event.getEntity() instanceof Arrow){
            Entity hitEntity=ArtifactFight.getMainClass().getServer().getEntity(Objects.requireNonNull(event.getEntity().getOwnerUniqueId()));
            Entity hitedEntity=event.getHitEntity();
            if(hitEntity==null){
                event.setCancelled(true);
                return;
            }
            if(hitEntity instanceof Player && hitedEntity instanceof Player){
                HashMap<Integer,ArtifactFather> hashMap=PlayerArtifactMap.ArtifactMap.get(hitEntity.getUniqueId());
                if(hashMap==null){
                    event.setCancelled(true);
                    return;
                }
                ((ArtifactBowFather)hashMap.get(event.getEntity().getPersistentDataContainer().get(new NamespacedKey(javaPlugin,"LaunchSlot"),PersistentDataType.INTEGER))).onHit(event);
                PlayerArtifactMap.ArtifactMap.remove(hitEntity.getUniqueId());
                PlayerArtifactMap.ArtifactMap.put(hitEntity.getUniqueId(),hashMap);
            }
        }

    }
    @EventHandler
    public void PlayerBowLaunch(ProjectileLaunchEvent event){
        Entity launchEntity=ArtifactFight.getMainClass().getServer().getEntity(Objects.requireNonNull(event.getEntity().getOwnerUniqueId()));
        Entity launchedEntity=event.getEntity();
        if(launchedEntity instanceof Arrow){
            if(launchEntity instanceof Player){
                PersistentDataContainer persistentDataContainer=launchedEntity.getPersistentDataContainer();
                HashMap<Integer, ArtifactFather> hashMap=PlayerArtifactMap.ArtifactMap.get(launchEntity.getUniqueId());
                if(hashMap==null){
                    event.setCancelled(true);
                    return;
                }
                if(hashMap.get(((Player) launchEntity).getInventory().getHeldItemSlot())!=null){
                    ((ArtifactBowFather)hashMap.get(((Player) launchEntity).getInventory().getHeldItemSlot())).onLaunch(event);
                }
                persistentDataContainer.set(new NamespacedKey(javaPlugin,"LaunchSlot"), PersistentDataType.INTEGER,((Player) launchEntity).getInventory().getHeldItemSlot());
                PlayerArtifactMap.ArtifactMap.remove(launchEntity.getUniqueId());
                PlayerArtifactMap.ArtifactMap.put(launchEntity.getUniqueId(),hashMap);
            }
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
