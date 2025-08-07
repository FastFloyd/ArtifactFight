package zzxcraft.artifactFight.Artifact.Effect;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactEffectFather;
import zzxcraft.artifactFight.Artifact.Type.ArtifactEffectType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.UUID;

public class freeze extends ArtifactEffectFather {
    public freeze(Player player, Integer duration, Integer amplifier, Player causePlayer) {
        super(player, duration, amplifier, causePlayer, ArtifactEffectType.FREEZE.getId());
    }
    @Override
    public void run(){
        super.run();
        this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,1,this.getAmplifier(),false,false));
        this.getPlayer().damage(this.getAmplifier()*0.3, DamageSource.builder(DamageType.FREEZE).withCausingEntity(this.getCausePlayer()).withDirectEntity(this.getPlayer()).build());
    }
    @Override
    public void finish(){
        super.finish();
    }
}
