package zzxcraft.artifactFight.Artifact.Prop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactPropFather;

import java.util.ArrayList;
import java.util.Objects;

public class snowball extends ArtifactPropFather {
    private int count;
    private int i;
    public snowball(Player player,Integer slot) {
        super(player,slot,16,20,Material.SNOWBALL);
    }
    @Override
    public void onUse(PlayerInteractEvent event) {
        super.onUse(event);
    }


    @Override
    public void run() {
        super.run();
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
