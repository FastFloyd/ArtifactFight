package zzxcraft.artifactFight.Artifact.Fathers;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.Vector;

public abstract class ArtifactElytraFather extends ArtifactFather{
    public ArtifactElytraFather(Player player) {
        super(player);
    }
    public abstract void OnGlide(PlayerMoveEvent event);
}
