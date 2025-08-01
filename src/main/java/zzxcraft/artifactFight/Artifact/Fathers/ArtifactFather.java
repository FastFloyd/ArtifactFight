package zzxcraft.artifactFight.Artifact.Fathers;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import zzxcraft.artifactFight.ArtifactFight;

public abstract class ArtifactFather extends BukkitRunnable {
    private ItemStack itemStack;
    private int slot;
    private Player player;
    public ArtifactFather(Player player,Integer slot) {
        this.player=player;
        this.slot=slot;
        this.runTaskTimer(ArtifactFight.getMainClass(),0,1);
    }
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    protected void setItemStack(ItemStack itemStack){
        this.itemStack=itemStack;
    }
    public int getSlot(){
        return this.slot;
    }
    protected void setSlot(int slot){
        this.slot=slot;
    }
    public Player getPlayer(){
        return this.player;
    }
    protected void setPlayer(Player player){
        this.player=player;
    }
    public void finish(){
        this.cancel();
    }
}
