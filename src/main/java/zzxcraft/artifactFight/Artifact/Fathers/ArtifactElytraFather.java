package zzxcraft.artifactFight.Artifact.Fathers;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.Vector;

public abstract class ArtifactElytraFather extends ArtifactFather{
    public ArtifactElytraFather(Player player) {
        super(player,102);
    }
    public abstract void OnGlide(PlayerMoveEvent event);
    public abstract void OnFighted(EntityDamageByEntityEvent event);
}
