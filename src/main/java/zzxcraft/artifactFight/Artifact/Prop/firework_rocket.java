package zzxcraft.artifactFight.Artifact.Prop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactPropFather;

import java.util.ArrayList;
import java.util.Objects;

public class firework_rocket extends ArtifactPropFather {
    private int count;
    private int i;
    public firework_rocket(Player player,Integer slot) {
        super(player,slot);
        this.count=16;
        this.setItemStack(ItemStack.of(Material.FIREWORK_ROCKET,16));
        i=0;
    }
    @Override
    public void onUse(PlayerInteractEvent event) {
        if(this.count==0) event.setCancelled(true);
        this.count--;
    }

    @Override
    public void run() {
        if(this.count==16) i=0;
        i++;
        if(i==100){
            this.count++;
            i=0;
        }
        if(this.count<=0){
            this.setItemStack(ItemStack.of(Material.BARRIER));
            this.count=0;
        }
        else{
            this.setItemStack(ItemStack.of(Material.FIREWORK_ROCKET,count));
        }

        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
