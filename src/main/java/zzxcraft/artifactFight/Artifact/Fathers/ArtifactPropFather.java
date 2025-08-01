package zzxcraft.artifactFight.Artifact.Fathers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.Vector;

public abstract class ArtifactPropFather extends ArtifactFather {
    public ArtifactPropFather(Player player,Integer slot) {
        super(player,slot);
    }
    public abstract void onUse(PlayerInteractEvent event);
}
