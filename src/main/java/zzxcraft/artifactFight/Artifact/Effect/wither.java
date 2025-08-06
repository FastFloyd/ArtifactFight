package zzxcraft.artifactFight.Artifact.Effect;

import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactEffectFather;

public class wither extends ArtifactEffectFather {
    public wither(Player player, Integer duration, Integer amplifier,Player causePlayer) {
        super(player, duration, amplifier,causePlayer,1);
    }
    @Override
    public void run(){
        super.run();
        this.getPlayer().damage(this.getAmplifier(), (this.getCausePlayer()==null ? DamageSource.builder(DamageType.WITHER).build():DamageSource.builder(DamageType.WITHER).withCausingEntity(this.getCausePlayer()).withDirectEntity(this.getPlayer()).build()));
    }
    @Override
    public void finish(){
        super.finish();
    }
}
