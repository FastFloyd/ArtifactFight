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
    public snowball(Player player,int slot) {
        super(player);
        this.count=16;
        this.setItemStack(ItemStack.of(Material.SNOWBALL,16));
        this.setSlot(slot);
        i=0;
    }
    @Override
    public void onUse(PlayerInteractEvent event) {
        this.count--;
        this.setItemStack(ItemStack.of(Material.SNOWBALL,count));
    }


    @Override
    public void run() {
        i++;
        if(i==20){
            this.count++;
            i=0;
        }
        this.setItemStack(ItemStack.of(Material.SNOWBALL,count));
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
