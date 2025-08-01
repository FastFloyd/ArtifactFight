package zzxcraft.artifactFight.Artifact.Fathers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.Vector;

public abstract class ArtifactBootFather extends ArtifactFather {
    public ArtifactBootFather(Player player) {
        super(player,100);
    }

    public abstract void OnFighted(EntityDamageByEntityEvent event);
}
