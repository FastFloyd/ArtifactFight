package zzxcraft.artifactFight.Artifact.Fathers;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.Vector;

public abstract class ArtifactShieldFather extends ArtifactFather{
    public ArtifactShieldFather(Player player,Integer slot) {
        super(player,slot);
    }
    public abstract void OnFighted(EntityDamageByEntityEvent event);
}
