package zzxcraft.artifactFight.Artifact.Fathers;

import io.papermc.paper.event.entity.EntityLoadCrossbowEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.ArrayList;
import java.util.Vector;

public abstract class ArtifactBowFather extends ArtifactFather {
    public ArtifactBowFather(Player player) {
        super(player);
    }

    public abstract void onHit(ProjectileHitEvent event);
    public abstract void onLaunch(ProjectileLaunchEvent event);
    public abstract void onLoad(EntityLoadCrossbowEvent event);
}
