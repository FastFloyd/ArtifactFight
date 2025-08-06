package zzxcraft.artifactFight.Artifact.Fathers;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import zzxcraft.artifactFight.ArtifactFight;
import zzxcraft.artifactFight.PlayerArtifactMap;

public abstract class ArtifactEffectFather extends BukkitRunnable {
    private Player player;
    private Integer duration;
    private Integer amplifier;
    private Player causePlayer;
    private Integer id;
    public ArtifactEffectFather(Player player,Integer duration,Integer amplifier,Player causePlayer,Integer id){
        this.player=player;
        this.duration=duration;
        this.amplifier=amplifier;
        this.causePlayer=causePlayer;
        this.id=id;
        this.runTaskTimer(ArtifactFight.getMainClass(),0,1);
    }
    public void run(){
        this.duration--;
        if(this.duration==0){
            this.finish();
        }
    }
    public void finish(){
        PlayerArtifactMap.EffectMap.get(this.player.getUniqueId()).remove(this.id);
        this.cancel();
    }
    public Player getPlayer(){
        return this.player;
    }
    public Integer getAmplifier() {
        return this.amplifier;
    }
    public Integer getDuration(){
        return this.duration;
    }
    public Player getCausePlayer(){
        return this.causePlayer;
    }
    public Integer getId(){
        return this.id;
    }
    public void setDuration(Integer duration){
        this.duration=duration;
    }
    public void setAmplifier(Integer amplifier){
        this.amplifier=amplifier;
    }
    public void setCausePlayer(Player player){
        this.causePlayer=player;
    }
}
