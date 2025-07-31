package zzxcraft.artifactFight.Artifact.Fathers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Vector;

public abstract class ArtifactHelmetFather extends ArtifactFather {
    public ArtifactHelmetFather(Player player) {
        super(player);
    }

    public abstract void OnFighted(EntityDamageByEntityEvent event);
}
