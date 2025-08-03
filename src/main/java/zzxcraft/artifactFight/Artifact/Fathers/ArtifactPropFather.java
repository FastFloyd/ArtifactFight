package zzxcraft.artifactFight.Artifact.Fathers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Vector;

public abstract class ArtifactPropFather extends ArtifactFather {
    protected int count;
    protected int i;
    protected int max_count;
    protected int tick;
    protected Material material;
    public ArtifactPropFather(Player player,Integer slot,Integer max_count,Integer tick,Material material) {
        super(player,slot);
        this.max_count=max_count;
        this.tick=tick;
        this.count=max_count;
        this.material=material;
        this.setItemStack(ItemStack.of(this.material,this.count));
        i=0;
    }
    public void onUse(PlayerInteractEvent event){
        if(this.count==0) event.setCancelled(true);
        else this.count--;
    }
    public void run(){
        if(this.count==this.max_count) i=0;
        this.i++;
        if(this.i==this.tick){
            this.count++;
            this.i=0;
        }
        if(this.count<=0){
            this.setItemStack(ItemStack.of(Material.BARRIER,1));
            this.count=0;
        }
        else{
            this.setItemStack(ItemStack.of(this.material,this.count));
        }
    }
}
