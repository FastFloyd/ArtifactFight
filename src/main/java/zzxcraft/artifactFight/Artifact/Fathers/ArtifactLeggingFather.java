package zzxcraft.artifactFight.Artifact.Fathers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.Vector;

public abstract class ArtifactLeggingFather extends ArtifactFather {
    public ArtifactLeggingFather(Player player) {
        super(player,37);
    }

    public abstract void OnFighted(EntityDamageByEntityEvent event);
}
